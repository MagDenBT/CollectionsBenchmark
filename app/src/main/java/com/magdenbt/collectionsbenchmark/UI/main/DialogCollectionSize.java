package com.magdenbt.collectionsbenchmark.UI.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.magdenbt.collectionsbenchmark.R;
import com.magdenbt.collectionsbenchmark.UI.KeyboardSource;
import com.magdenbt.collectionsbenchmark.databinding.DialogCollectionSizeBinding;

public class DialogCollectionSize extends DialogFragment {
    private KeyboardSource keyboardSource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CollDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DialogCollectionSizeBinding binding = DialogCollectionSizeBinding.inflate(inflater, container, false);
        keyboardSource = new KeyboardSource(getContext());
        binding.inCollectionSize.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE){
                keyboardSource.hideKeyboard(v);
                return true;
            }
            return false;
        });
        binding.bCalculate.setOnClickListener((v) -> {
        try {
            String collSizeData = binding.inCollectionSize.getText().toString().trim();
            if (!collSizeData.isEmpty()) sendMessage(Integer.parseInt(collSizeData));
        } catch (NullPointerException e) {
            binding.inCollectionSize.requestFocus();
            binding.inCollectionSize.setError(v.getContext().getText(R.string.input_dialog_elements_amount_error), getResources().getDrawable(R.drawable.exclamation_mark, getContext().getTheme()));
        }
        });
        return binding.getRoot();
    }

    private void sendMessage(int collectionSize){
        Bundle bundle = new Bundle();
        bundle.putInt(MainActivity.COLL_SIZE_REQ_KEY, collectionSize);
        getParentFragmentManager().setFragmentResult(MainActivity.COLL_SIZE_REQ_KEY, bundle);
        dismiss();
    }

}
