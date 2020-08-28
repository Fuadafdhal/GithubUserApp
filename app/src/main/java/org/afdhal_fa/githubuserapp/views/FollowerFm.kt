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
import org.afdhal_fa.githubuserapp.utils.Constants
import org.afdhal_fa.githubuserapp.viewmodels.FollowerViewModel

class FollowerFm : Fragment() {

    private lateinit var viewModel: FollowerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycleView()
        var username: String? =null
        username = arguments?.getString(Constants.VPAGER_DATA)

        viewModel = ViewModelProviders.of(this).get(FollowerViewModel::class.java)
        viewModel.getFollower().observe(viewLifecycleOwner, Observer {
            rv_followers.adapter?.let { adapter ->
                if (adapter is FollowerAdapter){
                    adapter.setFollowes(it)
                }
            }
        })
        if (username!=null){
            viewModel.fetchFollower(getString(R.string.api_key),username)
        }else{
            println(" Username Null")
        }
    }
    private fun setupRecycleView() {
        rv_followers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FollowerAdapter(mutableListOf())
        }
    }

}
