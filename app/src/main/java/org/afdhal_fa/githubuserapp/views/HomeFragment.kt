package org.afdhal_fa.githubuserapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import org.afdhal_fa.githubuserapp.R
import org.afdhal_fa.githubuserapp.adapters.SearchAdapter
import org.afdhal_fa.githubuserapp.utils.toast
import org.afdhal_fa.githubuserapp.viewmodels.HomeViewModel


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyler()

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getSearch().observe(viewLifecycleOwner, Observer {
            rv_searchBar.adapter?.let { adapter ->
                if (adapter is SearchAdapter) {
                    adapter.setSearch(it)
                }
            }
        })

        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.fetchSearch(getString(R.string.api_key), query)
                    return true
                } else {
                    context?.toast("Masukan kata kunci")
                    return true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }


    private fun setupRecyler() {
        rv_searchBar.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SearchAdapter(mutableListOf())
        }
    }

}
