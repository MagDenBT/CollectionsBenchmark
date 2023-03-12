package com.magdenbt.collectionsbenchmark;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import com.magdenbt.collectionsbenchmark.UI.DialogCollectionSize;
import com.magdenbt.collectionsbenchmark.UI.SharedCollSizeVM;
import com.magdenbt.collectionsbenchmark.UI.VPAdapter;
import com.google.android.material.tabs.TabLayoutMediator;
import com.magdenbt.collectionsbenchmark.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity {
    public static final String COLL_SIZE_REQ_KEY = "collestionSize";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedCollSizeVM sharedCollSizeVM = new ViewModelProvider(this).get(SharedCollSizeVM.class);

        getSupportFragmentManager().setFragmentResultListener(COLL_SIZE_REQ_KEY,this,(requestKey, result) -> sharedCollSizeVM.setCollectionsSize(result.getInt(COLL_SIZE_REQ_KEY)));
        if (sharedCollSizeVM.getCollectionSize() == 0) {
            DialogCollectionSize dialogCollectionSize = new DialogCollectionSize();
            dialogCollectionSize.setCancelable(false);
            dialogCollectionSize.show(getSupportFragmentManager(), "SomeTag");
        }

        binding.pager.setAdapter(new VPAdapter(this));
        new TabLayoutMediator(binding.tab, binding.pager,false, (tab, position)->{
           switch (position){
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