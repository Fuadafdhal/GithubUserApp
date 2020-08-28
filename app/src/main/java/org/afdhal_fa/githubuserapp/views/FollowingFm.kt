package org.afdhal_fa.githubuserapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_follower.*
import org.afdhal_fa.githubuserapp.R
import org.afdhal_fa.githubuserapp.adapters.FollowerAdapter
import org.afdhal_fa.githubuserapp.adapters.FollowingAdapter
import org.afdhal_fa.githubuserapp.utils.Constants
import org.afdhal_fa.githubuserapp.viewmodels.FollowingViewModel

class FollowingFm : Fragment() {

    private lateinit var viewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyler()
        var userName: String? = null
        if (arguments != null) {
            userName = arguments?.getString(Constants.VPAGER_DATA)
        } else {
            println("Null argument")
        }

        viewModel = ViewModelProviders.of(this).get(FollowingViewModel::class.java)

        if (userName!=null){
            viewModel.fetchFollowing(getString(R.string.api_key),userName)
        }else{
            println(" Username Null")
        }

        viewModel.getFollowing().observe(viewLifecycleOwner, Observer {
            rv_followers.adapter?.let {adapter ->
                if (adapter is FollowingAdapter){
                    adapter.setFollowings(it)
                }
            }
        })

    }

    private fun setupRecyler() {
        rv_followers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FollowingAdapter(mutableListOf())
        }

    }

}