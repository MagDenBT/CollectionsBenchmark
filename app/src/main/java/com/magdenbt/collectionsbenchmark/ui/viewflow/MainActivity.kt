package com.magdenbt.collectionsbenchmark.ui.viewflow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.compose.viewModel
import com.magdenbt.collectionsbenchmark.InitApp
import com.magdenbt.collectionsbenchmark.di.qualifiers.MainActivityQ
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModel
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModelFactory
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    @Inject
    lateinit var dialogCollectionSize: dagger.Lazy<DialogCollectionSize>

    @MainActivityQ
    @Inject
    lateinit var sharedCollSizeVM: SharedCollSizeViewModel

    companion object {
        const val COLL_SIZE_REQ_KEY = "collestionSize"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as InitApp).appComponent
            .mainComponent()
            .create(this)
            .inject(this)
        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel: StatViewModel =
            ViewModelProvider(this, StatViewModelFactory(application)).get()

        setContent { PagerScreen(viewModel = viewModel, collectionSize = 1000) }
        supportFragmentManager.setFragmentResultListener(COLL_SIZE_REQ_KEY, this) { _, result ->
            sharedCollSizeVM.collectionSize = result.getInt(
                COLL_SIZE_REQ_KEY,
            )
        }

        if (sharedCollSizeVM.collectionSize == 0) {
            dialogCollectionSize.get().isCancelable = false
            dialogCollectionSize.get().show(supportFragmentManager, "SomeTag")
        }
//        binding.pager.adapter = viewPagerAdapter
//        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
//            when (position) {
//                0 -> tab.text = getText(R.string.tab_collections)
//                1 -> tab.text = getText(R.string.tab_maps)
//            }
//        }.attach()
    }

//    private fun prepareScreens() {
//        val statRepository = StatRepository(this)
//        val screens = mutableListOf<@Composable () -> Unit>()
//
//        for (collectionType in CollectionsType.values()) {
//
//            val viewModel = ViewModelProvider(this, StatViewModelFactory(StatRepository(this))).get(
//                StatViewModel::class.java,
//            )
//        }
//    }
}
