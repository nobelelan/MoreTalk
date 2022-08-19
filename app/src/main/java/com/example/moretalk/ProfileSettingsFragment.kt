package com.example.moretalk

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moretalk.databinding.FragmentChatBinding
import com.example.moretalk.databinding.FragmentProfileSettingsBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileSettingsFragment : Fragment() {

    private var _binding: FragmentProfileSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileSettingsBinding.bind(view)

        auth = FirebaseAuth.getInstance()

        binding.buttonLogOut.setOnClickListener {
            auth.signOut()
            if (auth.currentUser == null){
                val intent = Intent(activity, LogInActivity::class.java)
                activity?.finish()
                activity?.startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}