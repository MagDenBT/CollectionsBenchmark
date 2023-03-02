package com.magdenbt.collectionsbenchmark;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.magdenbt.collectionsbenchmark.UI.VPAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(new VPAdapter(this));
        TabLayout tab1 = findViewById(R.id.tab);

        new TabLayoutMediator(tab1, viewPager2,false, (tab, position)->{
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