package org.afdhal_fa.githubuserapp.adapters


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.afdhal_fa.githubuserapp.models.VPager
import org.afdhal_fa.githubuserapp.utils.Constants

class VPagerAdapter(private val fragments: List<VPager>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    var userName: String = "username"

    override fun getItem(position: Int): Fragment {
        val mBundel = Bundle()
        mBundel.putString(Constants.VPAGER_DATA, getData())
        fragments[position].fragment.arguments = mBundel
        return fragments[position].fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].title
    }

    fun setData(username: String) {
        userName = username
    }

    fun getData(): String = userName
}
