// package com.magdenbt.collectionsbenchmark.ui.viewflow
//
// import androidx.fragment.app.Fragment
// import androidx.fragment.app.FragmentActivity
// import androidx.viewpager2.adapter.FragmentStateAdapter
// import com.magdenbt.collectionsbenchmark.CollectionsType
// import javax.inject.Inject
//
// class ViewPagerAdapter @Inject constructor(fragmentActivity:FragmentActivity): FragmentStateAdapter(fragmentActivity) {
//
//    override fun getItemCount() = 2
//
//    override fun createFragment(position: Int): Fragment {
//        return when(position){
//            0 -> ViewPagerFragment(CollectionsType.LIST)
//            else -> ViewPagerFragment(CollectionsType.MAP)
//        }
//    }
// }
