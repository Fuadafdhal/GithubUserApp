package org.afdhal_fa.githubuserapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_user.view.*
import org.afdhal_fa.githubuserapp.R
import org.afdhal_fa.githubuserapp.models.Search
import org.afdhal_fa.githubuserapp.utils.Constants
import org.afdhal_fa.githubuserapp.utils.toast

class SearchAdapter(private var dataSearch: MutableList<Search>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    val mBundel: Bundle? = Bundle()

    fun setSearch(result: List<Search>) {
        dataSearch.clear()
        dataSearch.addAll(result)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        )
    }

    override fun getItemCount(): Int = dataSearch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSearch[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(search: Search) {
            with(itemView) {
                Glide.with(itemView)
                    .load(search.avatarUrl)
                    .apply(RequestOptions().override(65, 65))
                    .into(img_profile)

                txt_username.text = search.userName
                setOnClickListener {
                    mBundel?.putString(Constants.INTENT_DATA,search.userName)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment, mBundel)
                }
            }
        }
    }
}
