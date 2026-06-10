package com.example.tugasbesar.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tugasbesar.R
import com.example.tugasbesar.data.PreferencesHelper0115
import com.example.tugasbesar.databinding.FragmentProfileBinding
import com.example.tugasbesar.ui.main.MainActivity0115
import com.example.tugasbesar.ui.welcome.WelcomeActivity0115
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment0115 : Fragment() {

    private var binding0115: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater0115: LayoutInflater,
        container0115: ViewGroup?,
        savedInstanceState0115: Bundle?
    ): View {
        binding0115 = FragmentProfileBinding.inflate(inflater0115, container0115, false)
        return binding0115!!.root
    }

    override fun onViewCreated(view0115: View, savedInstanceState0115: Bundle?) {
        super.onViewCreated(view0115, savedInstanceState0115)

        loadProfileData0115()

        binding0115?.btnSaveProfile0115?.setOnClickListener {
            saveActiveProfileName0115()
        }

        binding0115?.btnAddProfile0115?.setOnClickListener {
            val intent0115 = Intent(requireContext(), WelcomeActivity0115::class.java)
            intent0115.putExtra("is_adding_profile", true)
            startActivity(intent0115)
        }
        
        binding0115?.btnDeleteActiveProfile0115?.setOnClickListener {
            val activeName0115 = PreferencesHelper0115.getUsername0115(requireContext()) ?: ""
            showDeleteConfirmDialog0115(activeName0115)
        }
    }

    private fun loadProfileData0115() {
        val username0115 = PreferencesHelper0115.getUsername0115(requireContext()) ?: ""
        
        // Setup header and edit form
        binding0115?.textProfileName0115?.text = username0115
        binding0115?.editProfileName0115?.setText(username0115)

        // Set initial letter in avatar
        val initial0115 = if (username0115.isNotEmpty()) {
            username0115.first().uppercaseChar().toString()
        } else {
            "?"
        }
        binding0115?.textProfileInitial0115?.text = initial0115
        
        // Setup dropdown
        val allProfiles0115 = PreferencesHelper0115.getAllProfiles0115(requireContext())
        val adapter0115 = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            allProfiles0115
        )
        binding0115?.dropdownProfiles0115?.apply {
            setAdapter(adapter0115)
            setText(username0115, false) // false to prevent triggering filter
            setOnItemClickListener { parent, _, position, _ ->
                val selectedName0115 = parent.getItemAtPosition(position) as String
                if (selectedName0115 != username0115) {
                    switchToProfile0115(selectedName0115)
                }
            }
        }
    }

    private fun saveActiveProfileName0115() {
        val newName0115 = binding0115?.editProfileName0115?.text.toString().trim()
        val oldName0115 = PreferencesHelper0115.getUsername0115(requireContext()) ?: ""
        
        if (newName0115.isEmpty()) {
            binding0115?.inputLayoutProfileName0115?.error = getString(R.string.name_required)
            return
        }
        
        // Remove old profile and create new one if renamed
        if (newName0115 != oldName0115) {
            PreferencesHelper0115.removeProfile0115(requireContext(), oldName0115)
        }
        
        binding0115?.inputLayoutProfileName0115?.error = null
        PreferencesHelper0115.saveUsername0115(requireContext(), newName0115)
        
        loadProfileData0115()
        Toast.makeText(requireContext(), getString(R.string.profile_saved), Toast.LENGTH_SHORT).show()
        
        // Restart activity to refresh ViewModels with new name
        if (newName0115 != oldName0115) {
            restartMainActivity0115()
        }
    }

    private fun switchToProfile0115(profileName0115: String) {
        PreferencesHelper0115.switchToProfile0115(requireContext(), profileName0115)
        restartMainActivity0115()
    }

    private fun showDeleteConfirmDialog0115(profileName0115: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete_profile_title))
            .setMessage(getString(R.string.delete_profile_confirm))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                deleteProfile0115(profileName0115)
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun deleteProfile0115(profileName0115: String) {
        PreferencesHelper0115.removeProfile0115(requireContext(), profileName0115)
        
        // If we deleted the active profile, go back to Welcome screen
        if (PreferencesHelper0115.getUsername0115(requireContext()) == null) {
            val intent0115 = Intent(requireContext(), WelcomeActivity0115::class.java)
            intent0115.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent0115)
        } else {
            // Otherwise just reload data
            loadProfileData0115()
        }
    }

    private fun restartMainActivity0115() {
        val intent0115 = Intent(requireContext(), MainActivity0115::class.java)
        intent0115.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent0115)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding0115 = null
    }
}
