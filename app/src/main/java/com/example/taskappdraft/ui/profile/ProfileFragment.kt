package com.example.taskappdraft.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.taskappdraft.databinding.FragmentProfileBinding
import com.example.taskappdraft.ui.OnBoard.utils.loadImage
import com.example.taskappdraft.ui.task.data.local.Pref

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    private val launch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val photo = it.data?.data
                pref.saveUserImg(photo.toString())
                binding.imgProfile.loadImage(photo.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.etProfile.setText(pref.getUserName())
        binding.imgProfile.loadImage(pref.getUserImg())
        binding.etProfile.addTextChangedListener {
            pref.saveUserName(binding.etProfile.text.toString())

        }
        binding.imgProfile.setOnClickListener() {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launch.launch(intent)
        }
    }
}