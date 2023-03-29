package com.magdenbt.collectionsbenchmark.ui.viewflow;

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
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.InitApp;
import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.di.qualifiers.ViewPagerFragmentQ;
import com.magdenbt.collectionsbenchmark.modelflow.StatModel;
import com.magdenbt.collectionsbenchmark.ui.KeyboardSource;
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel;
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatDiffCallback;
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModel;
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatAdapter;
import com.magdenbt.collectionsbenchmark.databinding.FragmentBodyBinding;

import javax.inject.Inject;

import dagger.Lazy;

public class ViewPagerFragment extends Fragment {

    private FragmentBodyBinding binding;
    private StatViewModel rvModel;
    private final String COLL_TYPE_KEY = "collectionsType";
    @Inject
    public KeyboardSource keyboardSource;
    @Inject
    public ViewModelProvider viewModelProvider;
    @ViewPagerFragmentQ
    @Inject
    public Lazy<SharedCollSizeViewModel> sharedCollSizeVM;


    public ViewPagerFragment() {
        super();
    }

    public ViewPagerFragment(CollectionsType collectionsType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(COLL_TYPE_KEY, collectionsType);
        setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBodyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setRView();
        binding.inElementsAmount.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                keyboardSource.hideKeyboard(v);
                return true;
            }
            return false;
        });
        binding.bStartStop.setOnClickListener(getStartButtonListener());
    }

    private void setRView() {
        int columnAmount;
        try {
            columnAmount = getArguments().getSerializable(COLL_TYPE_KEY) == CollectionsType.LIST ? 3 : 2;
        } catch (NullPointerException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to get CollectionsType");
            columnAmount = 3;
        }

        binding.rView.setLayoutManager(new GridLayoutManager(getContext(), columnAmount));
        StatAdapter statAdapter = new StatAdapter(new StatDiffCallback());
        rvModel = viewModelProvider.get(StatViewModel.class);
        for (LiveData<StatModel> statModelLiveData : rvModel.getStatModelsLD()) {
            statModelLiveData.observe(getViewLifecycleOwner(), model -> {
                statAdapter.notifyItemChanged(rvModel.getStatModelsLD().indexOf(statModelLiveData));
            });
        }
        statAdapter.submitList(rvModel.getStatModelsLD());
        binding.rView.setAdapter(statAdapter);
    }

    private View.OnClickListener getStartButtonListener() {
        return v -> {
            String amountData = binding.inElementsAmount.getText().toString();
            if (amountData.trim().isEmpty()) {
                binding.til.requestFocus();
                binding.til.startAnimation(getTilErrorAnimation());
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

    @NonNull
    private Animation getTilErrorAnimation() {
        Interpolator cycleInterpolator = new CycleInterpolator(7);
        Animation shake = new TranslateAnimation(-10, 10, 0, 0);
        shake.setDuration(50);
        shake.setInterpolator(cycleInterpolator);
        return shake;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((InitApp) getActivity().getApplication()).getAppComponent().VPFragmentComponentBuilder().collectionType((CollectionsType) getArguments().getSerializable(COLL_TYPE_KEY)).VPFragment(this).build().inject(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}