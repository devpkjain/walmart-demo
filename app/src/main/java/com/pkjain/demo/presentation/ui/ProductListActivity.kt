package com.pkjain.demo.presentation.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.pkjain.demo.R
import com.pkjain.demo.model.Product
import com.pkjain.demo.presentation.ui.details.ProductDetailsActivity
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.empty_view.*


class ProductListActivity : AppCompatActivity() {
    private lateinit var viewModel: ProductsViewModel
    private lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        viewModel = ProductsViewModel.getInstance(this)

        // Initial setup
        setupViews()
        registerObservables()
    }


    private fun setupViews() {
        // RecyclerView setup
        adapter = ProductListAdapter(
                object : ProductListAdapter.ItemClickListener {
                    override fun onItemClicked(product: Product) {
                        val intent = Intent(main_recyclerView.context, ProductDetailsActivity::class.java)
                        startActivity(intent)
                    }
                }
        )
        main_recyclerView.layoutManager = LinearLayoutManager(this)
        main_recyclerView.adapter = adapter

        viewModel.refresh()
        main_refreshView.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.refresh()
                submitItems()
            }
        })
    }

    private fun registerObservables() {
        // We start by submiting the list to the adapter initally
        submitItems()

        // Toast for API Errors
        viewModel.errorToastEvent.observe(this,
                Observer { Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG) }
        )

        // Clearing the data of the adapter when doing a new search
        viewModel.clearDataEvents.observe(this,
                Observer {
                    viewModel.clearDataSource()
                    submitItems()
                    adapter.notifyDataSetChanged()
                }
        )

        // Showing an empty view on the screen
        viewModel.emptyVisibilityEvents.observe(this,
                Observer { item ->
                    if (item != null) {
                        var visibility = if (item.peek()) View.VISIBLE else View.GONE
                        this.empty_textView.visibility = visibility
                    }
                }
        )

        // Display the recyclerview loading item
        viewModel.recyclerViewLoadingEvents.observe(this,
                Observer { item ->
                    if (item != null) {
                        adapter.loading = item.peek()
                        main_refreshView.isRefreshing = item.peek()
                    }
                })
    }

    // Submits the list (with the pagination) to the adapter
    fun submitItems() {
        viewModel.getItems()!!
                .subscribe(
                        { items -> adapter.submitList(items) },
                        {
                            //Error handling
                        }
                )
    }
}
