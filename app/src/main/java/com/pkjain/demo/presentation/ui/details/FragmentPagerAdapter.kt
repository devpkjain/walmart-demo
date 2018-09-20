package com.pkjain.demo.presentation.ui.details

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.pkjain.demo.model.Product


class FragmentPagerAdapter(fragmentManager: FragmentManager) : BaseFragmentPagerAdapter(fragmentManager) {

    private var list: List<Product> = ArrayList()

    fun submitList(products: List<Product>) {
        list = products
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return if (list.size > 0) list.size else 3
    }

    override fun getItem(position: Int): Fragment? = if (position < list.size) ProductDetailsFragment.newInstance(position, list.get(position)) else
        ProductDetailsFragment.newInstance(position, "dum")

    override fun getItemPosition(`object`: Any): Int {
        if (`object` is ProductDetailsFragment) {
            return PagerAdapter.POSITION_UNCHANGED // don't force a reload
        } else {
            return POSITION_NONE;
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    override fun getPageWidth(position: Int): Float {
        return 0.95f
    }

}
