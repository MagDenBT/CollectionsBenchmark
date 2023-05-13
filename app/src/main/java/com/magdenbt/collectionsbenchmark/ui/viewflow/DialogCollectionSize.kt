package com.magdenbt.collectionsbenchmark.ui.viewflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import javax.inject.Inject

class DialogCollectionSize @Inject constructor() : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CollDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    DialogCollectionSizeScreen { calculate(it) }
                }
            }
        }
    }

    private fun calculate(input: Int) {
        parentFragmentManager.setFragmentResult(
            MainActivity.COLL_SIZE_REQ_KEY,
            bundleOf(MainActivity.COLL_SIZE_REQ_KEY to input),
        )
        dismiss()
    }
}
