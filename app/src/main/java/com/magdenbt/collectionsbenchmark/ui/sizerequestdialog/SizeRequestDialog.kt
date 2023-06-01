package com.magdenbt.collectionsbenchmark.ui.sizerequestdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.ui.mainactivity.MainActivity
import javax.inject.Inject

class SizeRequestDialog @Inject constructor() : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CollDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return dialogFragmentComposeView(true) { SizeRequestScreen { calculate(it) } }
    }

    private fun calculate(input: Int) {
        parentFragmentManager.setFragmentResult(
            MainActivity.COLL_SIZE_REQ_KEY,
            bundleOf(MainActivity.COLL_SIZE_REQ_KEY to input),
        )
        dismiss()
    }
}
