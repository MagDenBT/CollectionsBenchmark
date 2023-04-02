package com.magdenbt.collectionsbenchmark.ui.viewflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.databinding.DialogCollectionSizeBinding
import com.magdenbt.collectionsbenchmark.ui.KeyboardSource
import javax.inject.Inject

class DialogCollectionSize @Inject constructor(): DialogFragment() {

    @Inject lateinit var keyboardSource: KeyboardSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CollDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogCollectionSizeBinding.inflate(inflater, container, false)
        binding.inCollectionSize.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                keyboardSource.hideKeyboard(v)
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false })
        binding.bCalculate.setOnClickListener { takeData(binding) }
        return binding.root
    }

    private fun takeData(binding : DialogCollectionSizeBinding ) {
        val input = binding.inCollectionSize.text?.toString()?.toIntOrNull()
        if (input != null) {
            parentFragmentManager.setFragmentResult(MainActivity.COLL_SIZE_REQ_KEY,bundleOf(MainActivity.COLL_SIZE_REQ_KEY to input))
            dismiss()
        }else{
            binding.inCollectionSize.requestFocus()
            binding.inCollectionSize.error = getText(R.string.input_dialog_elements_amount_error)
        }
    }
}