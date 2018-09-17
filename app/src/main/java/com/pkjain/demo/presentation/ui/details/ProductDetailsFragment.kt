package com.pkjain.demo.presentation.ui.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pkjain.demo.R


/**
 *
 */
class ProductDetailsFragment : Fragment() {
    private var title: String? = null
    private var page: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        page = getArguments()?.getInt("someInt", 0)?: 0
        title = getArguments()?.getString("someTitle")?: "Name Title"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_root, container, false)

        return view;
    }

    companion object {

        // newInstance constructor for creating fragment with arguments
        fun newInstance(page: Int, title: String): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            fragment.setArguments(args)
            return fragment
        }
    }
}