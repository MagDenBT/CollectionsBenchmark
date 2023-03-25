package com.magdenbt.collectionsbenchmark.UI.ViewFlow;

import android.app.AlertDialog;

import android.content.Context;
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
import android.view.inputmethod.EditorInfo;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.InitApp;
import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.modelflow.StatModel;
import com.magdenbt.collectionsbenchmark.UI.KeyboardSource;
import com.magdenbt.collectionsbenchmark.UI.SharedCollSizeVM;
import com.magdenbt.collectionsbenchmark.UI.ViewModelFlow.StatVM;
import com.magdenbt.collectionsbenchmark.UI.ViewModelFlow.StatAdapter;
import com.magdenbt.collectionsbenchmark.databinding.FragmentBodyBinding;
import com.magdenbt.collectionsbenchmark.di.Qualifiers.VPFragmentQ;

import javax.inject.Inject;

import dagger.Lazy;

public class VPFragment extends Fragment{

    private FragmentBodyBinding binding;
    private StatVM rvModel;
    private final String COLL_TYPE_KEY = "collectionsType";
    @Inject public KeyboardSource keyboardSource;
    @Inject public StatAdapter statisticAdapter;
    @Inject public ViewModelProvider viewModelProvider;
    @Inject public GridLayoutManager gridLayoutManager;
    @Inject public Animation shake;
    @VPFragmentQ
    @Inject public Lazy<SharedCollSizeVM> sharedCollSizeVM;



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
        binding.rView.setLayoutManager(gridLayoutManager);
        rvModel = viewModelProvider.get(StatVM.class);
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
                binding.til.requestFocus();
                binding.til.startAnimation(shake);
                return;
            }
            try {
                keyboardSource.hideKeyboard(v);
                rvModel.startBenchmark(sharedCollSizeVM.get().getCollectionSize(), Integer.parseInt(amountData));
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((InitApp) getActivity().getApplication()).getAppComponent().VPFragmentComponentBuilder()
                .collectionType((CollectionsType) getArguments().getSerializable(COLL_TYPE_KEY))
                .VPFragment(this)
                .build()
                .inject(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}