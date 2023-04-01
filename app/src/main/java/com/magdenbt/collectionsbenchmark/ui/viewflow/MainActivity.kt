package com.magdenbt.collectionsbenchmark.ui.viewflow

import android.os.Bundle

import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator

import com.magdenbt.collectionsbenchmark.InitApp
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.databinding.ActivityMainBinding
import com.magdenbt.collectionsbenchmark.di.qualifiers.MainActivityQ
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel
import javax.inject.Inject


class MainActivity : FragmentActivity() {

    @Inject lateinit var dialogCollectionSize: dagger.Lazy<DialogCollectionSize>
    @Inject lateinit var viewPagerAdapter : ViewPagerAdapter
    @MainActivityQ @Inject lateinit var sharedCollSizeVM: SharedCollSizeViewModel

    companion object{
        const val COLL_SIZE_REQ_KEY = "collestionSize"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as InitApp).appComponent
            .mainComponent()
            .create(this)
            .inject(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.setFragmentResultListener(COLL_SIZE_REQ_KEY,this, {requestKey, result -> sharedCollSizeVM.setCollectionsSize(result.getInt(
            COLL_SIZE_REQ_KEY))})

        if(sharedCollSizeVM.collectionSize == 0){
            dialogCollectionSize.get().isCancelable = false
            dialogCollectionSize.get().show(supportFragmentManager, "SomeTag")
        }
        binding.pager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tab,binding.pager, {tab, position ->
            when(position) {
                0 -> tab.text = getText(R.string.tab_collections)
                1 -> tab.text = getText(R.string.tab_maps)
            }

        }).attach()
    }
}