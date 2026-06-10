package com.example.tugasbesar.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.R

class ProfileAdapter0115(
    private var profiles0115: List<String>,
    private var activeProfile0115: String?,
    private val onProfileClick0115: (String) -> Unit,
    private val onDeleteClick0115: (String) -> Unit
) : RecyclerView.Adapter<ProfileAdapter0115.ProfileViewHolder0115>() {

    inner class ProfileViewHolder0115(itemView0115: View) : RecyclerView.ViewHolder(itemView0115) {
        val textInitial0115: TextView = itemView0115.findViewById(R.id.text_item_profile_initial_0115)
        val textName0115: TextView = itemView0115.findViewById(R.id.text_item_profile_name_0115)
        val textStatus0115: TextView = itemView0115.findViewById(R.id.text_item_profile_status_0115)
        val btnDelete0115: ImageButton = itemView0115.findViewById(R.id.btn_delete_profile_item_0115)
        val card0115: View = itemView0115.findViewById(R.id.card_profile_item_0115)
    }

    override fun onCreateViewHolder(parent0115: ViewGroup, viewType0115: Int): ProfileViewHolder0115 {
        val view0115 = LayoutInflater.from(parent0115.context)
            .inflate(R.layout.item_profile, parent0115, false)
        return ProfileViewHolder0115(view0115)
    }

    override fun onBindViewHolder(holder0115: ProfileViewHolder0115, position0115: Int) {
        val profileName0115 = profiles0115[position0115]
        val isActive0115 = profileName0115 == activeProfile0115

        holder0115.textName0115.text = profileName0115
        holder0115.textInitial0115.text = profileName0115.first().uppercaseChar().toString()

        if (isActive0115) {
            holder0115.textStatus0115.visibility = View.VISIBLE
            holder0115.textStatus0115.text = holder0115.itemView.context.getString(R.string.profile_active_label)
        } else {
            holder0115.textStatus0115.visibility = View.GONE
        }

        holder0115.card0115.setOnClickListener {
            onProfileClick0115(profileName0115)
        }

        holder0115.btnDelete0115.setOnClickListener {
            onDeleteClick0115(profileName0115)
        }
    }

    override fun getItemCount(): Int = profiles0115.size

    fun updateData0115(newProfiles0115: List<String>, newActiveProfile0115: String?) {
        profiles0115 = newProfiles0115
        activeProfile0115 = newActiveProfile0115
        notifyDataSetChanged()
    }
}
