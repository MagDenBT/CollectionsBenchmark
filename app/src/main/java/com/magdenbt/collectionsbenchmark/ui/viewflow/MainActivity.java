package com.magdenbt.collectionsbenchmark.ui.viewflow;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.magdenbt.collectionsbenchmark.InitApp;
import com.magdenbt.collectionsbenchmark.R;
import com.google.android.material.tabs.TabLayoutMediator;
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel;
import com.magdenbt.collectionsbenchmark.databinding.ActivityMainBinding;
import com.magdenbt.collectionsbenchmark.di.qualifiers.MainActivityQ;

import javax.inject.Inject;

import dagger.Lazy;

public class MainActivity extends FragmentActivity {
    public static final String COLL_SIZE_REQ_KEY = "collestionSize";
    @Inject
    public Lazy<DialogCollectionSize> dialogCollectionSize;
    @Inject
    public ViewPagerAdapter viewPagerAdapter;

    @MainActivityQ
    @Inject
    public SharedCollSizeViewModel sharedCollSizeVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((InitApp) getApplication()).getAppComponent()
                .mainComponent()
                .create(this)
                .inject(this);
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().setFragmentResultListener(COLL_SIZE_REQ_KEY, this, (requestKey, result) -> sharedCollSizeVM.setCollectionsSize(result.getInt(COLL_SIZE_REQ_KEY)));
        if (sharedCollSizeVM.getCollectionSize() == 0) {
            dialogCollectionSize.get().setCancelable(false);
            dialogCollectionSize.get().show(getSupportFragmentManager(), "SomeTag");
        }

        binding.pager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(binding.tab, binding.pager, false, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(getText(R.string.tab_collections));
                    break;
                case 1:
                    tab.setText(getText(R.string.tab_maps));
                    break;
            }
        }).attach();
    }

}