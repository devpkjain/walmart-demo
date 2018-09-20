package com.pkjain.demo.presentation.ui.details

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer
import com.pkjain.demo.R
import com.pkjain.demo.presentation.ui.ProductsViewModel


class ProductDetailsActivity : AppCompatActivity() {
    lateinit var adapter: FragmentPagerAdapter
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product_details)
        setupViewPager(findViewById<View>(R.id.viewPager) as ViewPager)
    }

    fun setupViewPager(viewPager: ViewPager) {

        viewPager.apply {
            adapter = FragmentPagerAdapter(supportFragmentManager)
            setOffscreenPageLimit(3);
            setClipToPadding(false);
            setPageMargin(12);
            setPageTransformer(true, ScaleInOutTransformer());

            addOnPageChangeListener(object : OnPageChangeListener {

                // This method will be invoked when a new page becomes selected.
                override fun onPageSelected(position: Int) {
//                    Toast.makeText(this@ProductDetailsActivity,
//                            "Selected page position: $position", Toast.LENGTH_SHORT).show()
                }

                // This method will be invoked when the current page is scrolled
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    // Code goes here
                }

                // Called when the scroll state changes:
                // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
                override fun onPageScrollStateChanged(state: Int) {
                    // Code goes here
                }
            })
            val viewModel = ProductsViewModel.getInstance(this@ProductDetailsActivity)


            (adapter as FragmentPagerAdapter).submitList(viewModel.dataSourceFactory.source.list)

        }


    }
}