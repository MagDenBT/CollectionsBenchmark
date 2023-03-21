package com.magdenbt.collectionsbenchmark.UI;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardSource {
    private final Context context;

    public KeyboardSource(Context context) {
        this.context = context;
    }

    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
