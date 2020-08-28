package org.afdhal_fa.githubuserapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.prifile_bar.*
import kotlinx.android.synthetic.main.detail_fragment.*
import org.afdhal_fa.githubuserapp.R
import org.afdhal_fa.githubuserapp.adapters.VPagerAdapter
import org.afdhal_fa.githubuserapp.models.VPager
import org.afdhal_fa.githubuserapp.utils.Constants
import org.afdhal_fa.githubuserapp.viewmodels.DetailViewModel
import org.afdhal_fa.githubuserapp.viewmodels.FollowerViewModel
import org.afdhal_fa.githubuserapp.viewmodels.FollowingViewModel


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFollower: FollowerViewModel
    private lateinit var viewModelFolowing: FollowingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = arguments?.getString(Constants.INTENT_DATA)

        savedInstanceState?.putString(Constants.INTENT_DATA, userName)


        val pages = ArrayList<VPager>()
        pages.add(VPager("Followers", FollowerFm()))
        pages.add(VPager("Following", FollowingFm()))

        val vPagerAdapter = VPagerAdapter(pages, childFragmentManager)
        vPagerAdapter.setData(userName.toString())
        viewpager.adapter = vPagerAdapter
        viewpager.overScrollMode = View.OVER_SCROLL_ALWAYS

        tabLayout.setupWithViewPager(viewpager)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModelFollower = ViewModelProviders.of(this).get(FollowerViewModel::class.java)
        viewModelFolowing = ViewModelProviders.of(this).get(FollowingViewModel::class.java)


        viewModel.getDetail().observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it.avatar_url)
                .apply(RequestOptions())
                .override(120, 120)
                .into(img_profile)
            tv_name.text = it.name
            tv_username.text = it.userName
        })

        viewModelFolowing.getFollowing().observe(viewLifecycleOwner, Observer {
            tv_followings.text = String.format("Following : %s", it.size.toString())
        })
        viewModelFollower.getFollower().observe(viewLifecycleOwner, Observer {
            tv_followers.text = String.format("Follower : %s", it.size.toString())
        })


        if (userName != null) {
            viewModel.fetchDetail(getString(R.string.api_key), userName)
            viewModelFollower.fetchFollower(getString(R.string.api_key), userName)
            viewModelFolowing.fetchFollowing(getString(R.string.api_key), userName)
        }
    }
}
