package com.magdenbt.collectionsbenchmark.ui.mainactivity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.magdenbt.collectionsbenchmark.InitApp
import com.magdenbt.collectionsbenchmark.ui.sizerequestdialog.SizeRequestDialog
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import com.magdenbt.collectionsbenchmark.viewmodelflow.SharedCollSizeViewModel
import com.magdenbt.collectionsbenchmark.viewmodelflow.StatViewModel
import com.magdenbt.collectionsbenchmark.viewmodelflow.StatViewModelFactory
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    @Inject
    lateinit var sizeRequestDialog: dagger.Lazy<SizeRequestDialog>

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
            sizeRequestDialog.get().apply {
                isCancelable = false
                show(supportFragmentManager, "SomeTag")
            }
        }
        setContent {
            AppTheme {
                MainPager(viewModel = viewModel, collectionSize = sharedCollSizeVM.collectionSize)
            }
        }
    }
}
