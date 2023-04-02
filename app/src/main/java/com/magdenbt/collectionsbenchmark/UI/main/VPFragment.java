package com.magdenbt.collectionsbenchmark.UI.main;

import android.app.AlertDialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.StatModel;
import com.magdenbt.collectionsbenchmark.UI.KeyboardSource;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatVM;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatDiffCallback;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatVMFactory;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatAdapter;
import com.magdenbt.collectionsbenchmark.databinding.FragmentBodyBinding;

public class VPFragment extends Fragment{

    private FragmentBodyBinding binding;
    private StatVM rvModel;
    private final String COLL_TYPE_KEY = "collectionsType";
    private KeyboardSource keyboardSource;

    public VPFragment() {
        super();
    }

    public VPFragment(CollectionsType collectionsType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(COLL_TYPE_KEY, collectionsType);
        setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBodyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setRView();
        keyboardSource = new KeyboardSource(getContext());
        binding.inElementsAmount.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT){
                keyboardSource.hideKeyboard(v);
                return true;
            }
            return false;
        });
        binding.bStartStop.setOnClickListener(getStartButtonListener());
    }

    private void setRView() {
        CollectionsType collectionsType = (CollectionsType) getArguments().getSerializable(COLL_TYPE_KEY);
        int columnAmount = CollectionsType.LIST == collectionsType ? 3 : 2;

        binding.rView.setLayoutManager(new GridLayoutManager(getContext(), columnAmount));
        StatAdapter statisticAdapter = new StatAdapter(new StatDiffCallback());
        rvModel = new ViewModelProvider(this, new StatVMFactory(getActivity().getApplication(), collectionsType)).get(StatVM.class);
        for (LiveData<StatModel> statModelLiveData : rvModel.getStatModelsLD()) {
            statModelLiveData.observe(getViewLifecycleOwner(), model -> {
                statisticAdapter.notifyItemChanged(rvModel.getStatModelsLD().indexOf(statModelLiveData));
            });
        }
        statisticAdapter.submitList(rvModel.getStatModelsLD());
        binding.rView.setAdapter(statisticAdapter);
    }

    private View.OnClickListener getStartButtonListener() {
        return v -> {
            String amountData = binding.inElementsAmount.getText().toString();
            if (amountData.trim().isEmpty()) {
                Interpolator cycleInterpolator = new CycleInterpolator(7);
                Animation shake = new TranslateAnimation(-10, 10, 0, 0);
                shake.setDuration(50);
                shake.setInterpolator(cycleInterpolator);
                binding.til.requestFocus();
                binding.til.startAnimation(shake);
                return;
            }
            try {
                keyboardSource.hideKeyboard(v);
                int collectionSize = new ViewModelProvider(getActivity()).get(SharedCollSizeVM.class).getCollectionSize();
                rvModel.startBenchmark(collectionSize, Integer.parseInt(amountData));
            } catch (NumberFormatException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getText(R.string.alert_need_number));
                builder.show();
            } catch (Exception e) {
                Log.e(this.getClass().getCanonicalName(), "Benchmark start error - " + e.getMessage());
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}