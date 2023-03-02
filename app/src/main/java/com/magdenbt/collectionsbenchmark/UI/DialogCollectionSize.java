package com.magdenbt.collectionsbenchmark.UI;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.magdenbt.collectionsbenchmark.CollectionsType;
import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.Worker;
import com.google.android.material.textfield.TextInputEditText;

public class DialogCollectionSize extends DialogFragment {
    private TextInputEditText inCollextionSize;
    private int elementsAmount;
    private CollectionsType collectionsType;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CollDialogTheme);
        elementsAmount = getArguments().getInt("elementsAmount");
        collectionsType = (CollectionsType) getArguments().getSerializable("collectionsType");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_collection_size_layout, container);
        inCollextionSize = view.findViewById(R.id.inCollectionSize);
//        inCollextionSize.setOnClickListener(v -> inCollextionSize.setError(null));
        Button bCalculate = view.findViewById(R.id.bCalculate);
        bCalculate.setOnClickListener(this::onClick);
        return view;
    }
    private void startOperations(int collectionSize) {
        Handler mHandler = new Handler(Looper.getMainLooper());
        Worker.getInstance().startOperations(collectionSize, elementsAmount, collectionsType, mHandler);
    }

    private void sendMessage(int collectionSize){
        Bundle b = new Bundle();
        b.putInt("collectionSize", collectionSize);
        getParentFragmentManager().setFragmentResult("reqCollectionSize", b);
        dismiss();
    }

    private void onClick(View v) {
        try {
            String collSizeData = inCollextionSize.getText().toString().trim();
            if (!collSizeData.isEmpty()) {
                int collectionSize = Integer.parseInt(collSizeData);
                int minCollSize = elementsAmount * 2 + 1;
                if (collectionSize < minCollSize) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(getString(R.string.min_coll_size_should_be) + minCollSize);
                    builder.show();
                    return;
                }
                startOperations(collectionSize);
                sendMessage(collectionSize);
            }
        } catch (NullPointerException e) {
            inCollextionSize.requestFocus();
            inCollextionSize.setError(v.getContext().getText(R.string.input_dialog_elements_amount_error), getResources().getDrawable(R.drawable.exclamation_mark, getContext().getTheme()));

}
    }
}
