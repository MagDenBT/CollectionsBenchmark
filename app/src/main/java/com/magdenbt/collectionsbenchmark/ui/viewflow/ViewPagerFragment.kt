package com.magdenbt.collectionsbenchmark.ui.viewflow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.GridLayoutManager
import com.magdenbt.collectionsbenchmark.CollectionsType
import com.magdenbt.collectionsbenchmark.InitApp
import com.magdenbt.collectionsbenchmark.databinding.FragmentBodyBinding
import com.magdenbt.collectionsbenchmark.di.qualifiers.ViewPagerFragmentQ
import com.magdenbt.collectionsbenchmark.ui.KeyboardSource
import com.magdenbt.collectionsbenchmark.ui.SharedCollSizeViewModel
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatAdapter
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatDiffCallback
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModel
import dagger.Lazy
import javax.inject.Inject

class ViewPagerFragment constructor() : Fragment() {

    private val COLL_TYPE_KEY = "collectionsType"
    private var binding: FragmentBodyBinding? = null
    private val rvModel: StatViewModel by lazy{
        viewModelProvider.get(StatViewModel::class.java)
    }

    @Inject
    lateinit var keyboardSource: KeyboardSource

    @Inject
    lateinit var viewModelProvider: ViewModelProvider

    @ViewPagerFragmentQ
    @Inject
    lateinit var sharedCollSizeVM: Lazy<SharedCollSizeViewModel>


    constructor(collectionsType: CollectionsType) : this() {
        arguments = bundleOf(COLL_TYPE_KEY to collectionsType)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as InitApp).appComponent
            .VPFragmentComponentBuilder()
            .collectionType(arguments?.getSerializable(COLL_TYPE_KEY) as CollectionsType)
            .VPFragment(this)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBodyBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.inElementsAmount.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                keyboardSource.hideKeyboard(v)
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })
        binding!!.bStartStop.setOnClickListener { v -> startButtonListener(v) }
//        rvModel = viewModelProvider.get(StatViewModel::class.java)
        setRView()
    }

    private fun setRView() {
        val columnAmount =
            if (arguments?.getSerializable(COLL_TYPE_KEY) == CollectionsType.LIST) 3 else 2
        binding!!.rView.layoutManager = GridLayoutManager(context, columnAmount)
        val statAdapter = StatAdapter(StatDiffCallback())
        binding!!.rView.adapter = statAdapter
        for (liveData in rvModel.statModelsLD) {
            liveData.observe(viewLifecycleOwner) {
                statAdapter.notifyItemChanged(
                    rvModel.statModelsLD.indexOf(
                        liveData
                    )
                )
            }
        }
        statAdapter.submitList(rvModel.statModelsLD)
    }

    private fun startButtonListener(view: View) {
        val amount = binding!!.inElementsAmount.text.toString().toIntOrNull()
        if (amount == null) {
            binding!!.inElementsAmount.requestFocus()
            binding!!.inElementsAmount.startAnimation(tilErrorAnimation())
            return
        }
        keyboardSource.hideKeyboard(view)
        rvModel.startBenchmark(sharedCollSizeVM.get().collectionSize, amount)
    }

    private fun tilErrorAnimation(): Animation {
        return TranslateAnimation(-10f, 10f, 0f, 0f).apply {
            duration = 50
            interpolator = CycleInterpolator(7f)
        }
    }
}