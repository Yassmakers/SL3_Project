package com.example.sl3verbeterd
import com.example.sl3verbeterd.Profile
import android.widget.Button
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.ProfileListAdapter.ProfileViewHolder
class ProfileListAdapter(private val clickListener: ProfileClickListener) :
    ListAdapter<Profile, ProfileListAdapter.ProfileViewHolder>(PROFILES_COMPARATOR) {

    interface ProfileClickListener {
        fun onProfileClick(profile: Profile)
        fun onDeleteClick(profile: Profile)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder.create(parent, clickListener)
    }




    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }


    class ProfileViewHolder(itemView: View, private val clickListener: ProfileClickListener) : RecyclerView.ViewHolder(itemView) {
        private val profileItemView: TextView = itemView.findViewById(R.id.textView)
        private val deleteButton: Button = itemView.findViewById(R.id.button_delete)

        fun bind(profile: Profile, clickListener: ProfileClickListener) {
            profileItemView.text = profile.firstName
            itemView.setOnClickListener { clickListener.onProfileClick(profile) }

            // Set click listener for the delete button
            deleteButton.setOnClickListener { clickListener.onDeleteClick(profile) }
        }

        companion object {
            fun create(parent: ViewGroup, clickListener: ProfileClickListener): ProfileViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_profiles, parent, false)

                return ProfileViewHolder(view, clickListener)
            }
        }
    }




    companion object {
        private val PROFILES_COMPARATOR = object : DiffUtil.ItemCallback<Profile>() {
            override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem.firstName == newItem.firstName
            }
        }
    }


}
