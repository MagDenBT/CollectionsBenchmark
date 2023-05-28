package com.magdenbt.collectionsbenchmark.ui.viewflow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.magdenbt.collectionsbenchmark.InitApp
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModel
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModelFactory
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    @Inject
    lateinit var dialogCollectionSize: dagger.Lazy<DialogCollectionSize>

    @Inject
    lateinit var sharedCollSizeVM: SharedCollSizeViewModel

    companion object {
        const val COLL_SIZE_REQ_KEY = "collestionSize"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as InitApp).appComponent.mainComponent().create(this).inject(this)
        super.onCreate(savedInstanceState)

        val viewModel: StatViewModel =
            ViewModelProvider(this, StatViewModelFactory(application)).get()

        supportFragmentManager.setFragmentResultListener(COLL_SIZE_REQ_KEY, this) { _, result ->
            sharedCollSizeVM.collectionSize.value = result.getInt(
                COLL_SIZE_REQ_KEY,
            )
        }

        if (sharedCollSizeVM.collectionSize.value == 0) {
            dialogCollectionSize.get().isCancelable = false
            dialogCollectionSize.get().show(supportFragmentManager, "SomeTag")
        }
        setContent {
            AppTheme {
                PagerScreen(viewModel = viewModel, collectionSize = sharedCollSizeVM.collectionSize)
            }
        }
    }
}
