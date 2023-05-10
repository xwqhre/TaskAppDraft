package com.example.taskappdraft.ui.OnBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskappdraft.databinding.FragmentOnBoardingBinding
import com.example.taskappdraft.ui.OnBoard.adapter.onBoardingAdapter
import com.example.taskappdraft.model.onBoardModel
import com.example.taskappdraft.data.local.Pref

class onBoardingFragment : Fragment() {

    private lateinit var  binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())

        val adapter = onBoardingAdapter(this::onClick)

        binding.viewPager.adapter= adapter
        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver);
        binding.viewPager.adapter = adapter

    }

    private fun onClick (onBoardModel: onBoardModel){
    findNavController().navigateUp()
    pref.saveUserSeen()
    }
}