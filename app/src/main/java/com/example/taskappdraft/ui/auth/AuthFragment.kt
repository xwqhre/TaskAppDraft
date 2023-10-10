package com.example.taskappdraft.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskappdraft.R
import com.example.taskappdraft.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.btnNumber.setOnClickListener {
            findNavController().navigate(R.id.verifyNumberFragment)
        }
    }
}