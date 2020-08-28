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

class FollowingAdapter(private var dataFollower: MutableList<Followers>) :
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    fun setFollowings(result: List<Followers>) {
        dataFollower.clear()
        dataFollower.addAll(result)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
    )


    override fun getItemCount(): Int = dataFollower.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.binding(dataFollower[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun binding(following: Followers) {
            with(itemView) {
                with(itemView) {
                    Glide.with(itemView)
                        .load(following.avatarUrl)
                        .placeholder(R.drawable.ic_person_black_24dp)
                        .apply(RequestOptions())
                        .override(65, 65)
                        .into(img_profile)
                    txt_username.text = following.userName

                    setOnClickListener {
                        Toast.makeText(itemView.context, following.userName, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

}
