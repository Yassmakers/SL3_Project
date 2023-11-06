package com.example.sl3verbeterd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ProfileListAdapter : ListAdapter<Profile, ProfileListAdapter.ProfileViewHolder>(PROFILES_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.firstName)
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            profileItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ProfileViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_profiles, parent, false)
                return ProfileViewHolder(view)
            }
        }
    }

    companion object {
        private val PROFILES_COMPARATOR = object : DiffUtil.ItemCallback<Profile>() {
            override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem.firstName == newItem.firstName
            }
        }
    }
}
