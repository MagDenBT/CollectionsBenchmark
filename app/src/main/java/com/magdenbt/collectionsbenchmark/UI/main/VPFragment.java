package com.magdenbt.collectionsbenchmark.UI.main;

import android.app.AlertDialog;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.StatModel;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatViewModel;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatDiffCallback;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatVMFactory;
import com.magdenbt.collectionsbenchmark.UI.Stat.StatAdapter;
import com.magdenbt.collectionsbenchmark.databinding.FragmentBodyBinding;

public class VPFragment extends Fragment{

    private FragmentBodyBinding binding;
    private StatViewModel rvModel;
    private final String COLL_TYPE_KEY = "collectionsType";

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
        binding.bStartStop.setOnClickListener(getStartButtonListener());
    }

    private void setRView() {
        CollectionsType collectionsType = (CollectionsType) getArguments().getSerializable(COLL_TYPE_KEY);
        int columnAmount = CollectionsType.LIST == collectionsType ? 3 : 2;

        binding.rView.setLayoutManager(new GridLayoutManager(getContext(), columnAmount));
        StatAdapter statisticAdapter = new StatAdapter(new StatDiffCallback());
        rvModel = new ViewModelProvider(this, new StatVMFactory(getActivity().getApplication(), collectionsType)).get(StatViewModel.class);
        for (MutableLiveData<StatModel> mutableLiveDatum : rvModel.getMutableLiveData()) {
            mutableLiveDatum.observe(getViewLifecycleOwner(), model -> {
                statisticAdapter.notifyItemChanged(rvModel.getMutableLiveData().indexOf(mutableLiveDatum));
            });
        }
        statisticAdapter.submitList(rvModel.getMutableLiveData());
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
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                int collectionSize = new ViewModelProvider(getActivity()).get(SharedCollSizeVM.class).getCollectionSize();
                rvModel.startBenchmark(collectionSize, Integer.parseInt(amountData));
            } catch (NumberFormatException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getText(R.string.alert_need_number));
                builder.show();
            } catch (Exception e) {
              Toast.makeText(v.getContext(), "ТЕХНИЧЕСКАЯ ОШИБКА. АААА!", Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}