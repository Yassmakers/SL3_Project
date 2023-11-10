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
class ProfileListAdapter(private val clickListener: ProfileClickListener,
                         private val layoutRole: String ) :
    ListAdapter<Profile, ProfileListAdapter.ProfileViewHolder>(PROFILES_COMPARATOR) {

    interface ProfileClickListener {

        fun onShowDetailsClick(profileId: Int)
        fun onProfileClick(profile: Profile)
        fun onDeleteClick(profile: Profile)
        fun onAddProfileClick(profile: Profile)
        fun onUpdateProfileClick(profile: Profile)
        fun showProfile(id: Int)
        fun onResetProfileClick(profile: Profile)
        fun onToggleVisibilityClick(profile: Profile)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder.create(parent, clickListener, layoutRole)
    }




    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }


    class ProfileViewHolder(itemView: View, private val clickListener: ProfileClickListener) : RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.name)
        private val jobView: TextView = itemView.findViewById(R.id.job)
        private val locationView: TextView = itemView.findViewById(R.id.location)
        private val educationView: TextView = itemView.findViewById(R.id.education)

        private val deleteButton: Button = itemView.findViewById(R.id.button_delete)
        private val resetButton: Button = itemView.findViewById(R.id.button_reset)
        private val addButton: Button = itemView.findViewById(R.id.button_add)
        private val updateButton: Button = itemView.findViewById(R.id.button_update)
        private val showDetailsButton: Button = itemView.findViewById(R.id.button_show_details)
        private val switchButton: Button = itemView.findViewById(R.id.button_visibility)
        fun bind(profile: Profile, clickListener: ProfileClickListener) {
            nameView.text = "${profile.firstName} ${profile.lastName}"
            jobView.text = profile.job
            locationView.text = profile.location
            educationView.text = profile.education



            itemView.setOnClickListener { clickListener.onProfileClick(profile) }

            // Set click listener for the delete button
            deleteButton.setOnClickListener { clickListener.onDeleteClick(profile) }

            // Set click listener for the switch button
            switchButton.setOnClickListener { clickListener.onToggleVisibilityClick(profile) }

//             Set click listener for the reset button
            resetButton.setOnClickListener { clickListener.onResetProfileClick(profile) }

            // Set click listener for the add button
            addButton.setOnClickListener { clickListener.onAddProfileClick(profile) }

            // Set click listener for the update button
            updateButton.setOnClickListener { clickListener.onUpdateProfileClick(profile) }

            // Set click listener for the show details button
            showDetailsButton.setOnClickListener {
                clickListener.onShowDetailsClick(profile.id) }
        }

        companion object {
            fun create(parent: ViewGroup, clickListener: ProfileClickListener, layoutRole: String): ProfileViewHolder {
                val layoutId = when (layoutRole) {
                    "admin" -> R.layout.recyclerview_admin
                    else -> R.layout.recyclerview_profiles
                }

                val view: View = LayoutInflater.from(parent.context)
                    .inflate(layoutId, parent, false)

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
