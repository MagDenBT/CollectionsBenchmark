package com.magdenbt.collectionsbenchmark.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.StatisticAdapter;
import com.magdenbt.collectionsbenchmark.Worker;
import com.google.android.material.textfield.TextInputLayout;

public class CustomFragment extends Fragment{

    private int collectionSize = 0;
    private int elementsAmount = 0;
    private TextView inElementsAmount;
    private TextInputLayout til;

    private final CollectionsType collectionsType;


    public CustomFragment(CollectionsType collectionsType) {
        this.collectionsType = collectionsType;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_body, container, false);

        return view.getRootView();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setRView(view);
        inElementsAmount = view.findViewById(R.id.inElementsAmount);
        Button bStartStop = view.findViewById(R.id.bStartStop);
        til = view.findViewById(R.id.TextInputLayout);
        bStartStop.setOnClickListener(getStartButtonListener());
    }

    private View.OnClickListener getStartButtonListener() {
        return v -> {

            String amountData = inElementsAmount.getText().toString();

            if (amountData.trim().isEmpty()) {
                Interpolator cycleInterpolator = new CycleInterpolator(7);
                Animation shake = new TranslateAnimation(-10, 10, 0, 0);
                shake.setDuration(50);
                shake.setInterpolator(cycleInterpolator);
                til.requestFocus();
                til.startAnimation(shake);
                return;
            }
            try {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                int amount = Integer.parseInt(amountData);
                if (collectionSize == 0) {
                    elementsAmount = amount;
                    showMyDialog();
                    return;
                } else if (elementsAmount != amount) {
                    elementsAmount = amount;
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(getText(R.string.question_change_coll_size));
                    builder.setPositiveButton(getText(R.string.yes),(dialog, which) -> showMyDialog());
                    builder.setNegativeButton(getText(R.string.no),(dialog, which) -> startOperations());
                    builder.show();
                }
                startOperations();
            } catch (NumberFormatException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getText(R.string.alert_need_number));
                builder.show();
            } catch (Exception e) {
              Toast.makeText(v.getContext(), "ТЕХНИЧЕСКАЯ ОШИБКА. АААА!", Toast.LENGTH_LONG).show();
            }
        };
    }

    private void startOperations() {
        Handler mHandler = new Handler(Looper.getMainLooper());
        Worker.getInstance().startOperations(collectionSize, elementsAmount, collectionsType, mHandler);
    }


    private void setRView(@NonNull View view) {
        int columnAmount;
        if (collectionsType == CollectionsType.LIST) {
            columnAmount = 3;
        } else {
            columnAmount = 2;
        }

        RecyclerView recyclerView = view.findViewById(R.id.rView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnAmount));
        recyclerView.setAdapter(new StatisticAdapter(view.getContext(), collectionsType));
    }

    public void showMyDialog() {
        getChildFragmentManager().setFragmentResultListener("reqCollectionSize", getViewLifecycleOwner(), (requestKey, result) -> {
            if (requestKey.equals("reqCollectionSize")) collectionSize = result.getInt("collectionSize");
        });
        DialogCollectionSize dialog = new DialogCollectionSize();
        Bundle bundle = new Bundle();
        bundle.putInt("elementsAmount", elementsAmount);
        bundle.putSerializable("collectionsType", collectionsType);
        dialog.setArguments(bundle);
        dialog.show(getChildFragmentManager(), "someTag");
    }


}