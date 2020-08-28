package org.afdhal_fa.githubuserapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_user.view.*
import org.afdhal_fa.githubuserapp.R
import org.afdhal_fa.githubuserapp.models.Followers


class FollowerAdapter(private var dataFollowers: MutableList<Followers>) :
    RecyclerView.Adapter<FollowerAdapter.ViewHolder>() {

    fun setFollowes(result: List<Followers>) {
        dataFollowers.clear()
        dataFollowers.addAll(result)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        )
    }

    override fun getItemCount(): Int = dataFollowers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataFollowers[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(followers: Followers) {
            with(itemView) {
                Glide.with(itemView)
                    .load(followers.avatarUrl)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .apply(RequestOptions())
                    .override(65, 65)
                    .into(img_profile)
                txt_username.text = followers.userName
                setOnClickListener {
                    Toast.makeText(itemView.context, followers.userName, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}