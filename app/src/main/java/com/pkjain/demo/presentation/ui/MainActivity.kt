package com.pkjain.demo.presentation.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.pkjain.demo.R
import com.pkjain.demo.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_view.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        // Initial setup
        setupViews()
        registerObservables()
    }



    private fun setupViews() {
        // RecyclerView setup
        adapter = MainAdapter(
                object : MainAdapter.ItemClickListener {
                    override fun onItemClicked(product: Product) {
//                        if (!product.url.isNullOrBlank()) {
//                            val i = Intent(Intent.ACTION_VIEW)
//                            i.data = Uri.parse(product.url)
//                            startActivity(i)
//                        }
                    }
                }
        )
        main_recyclerView.layoutManager = LinearLayoutManager(this)
        main_recyclerView.adapter = adapter

        viewModel.refresh()
        main_refreshView.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
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
                Observer { show ->
                    if(show != null) {
                        var visibility = if (show.peek()) View.VISIBLE else View.GONE
                        this.empty_view_imageView.visibility = visibility
                    }
                }
        )

        // Display the recyclerview loading item
        viewModel.recyclerViewLoadingEvents.observe(this,
                Observer { show ->
                    if(show != null) {
                        adapter.loading = show.peek()
                        main_refreshView.isRefreshing = show.peek()
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

    // method to hide the keyboard on each search
    fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = this.getCurrentFocus()
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}
