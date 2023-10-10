package com.example.taskappdraft.ui.OnBoard.verify

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.taskappdraft.R
import com.example.taskappdraft.databinding.FragmentVerifyNumberBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class VerifyNumberFragment : Fragment() {

    private lateinit var binding: FragmentVerifyNumberBinding
    private var phoneNumber = ""
    private lateinit var auth: FirebaseAuth


    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("ololo", "onVerificationCompleted:$credential")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w(ContentValues.TAG, "onVerificationFailed", e)
        }

        override fun onCodeSent(
            verificationId: String,
            p1: PhoneAuthProvider.ForceResendingToken
        ) {
            findNavController().navigate(
                R.id.acceptCodeFragment,
                bundleOf("verId" to verificationId)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerifyNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.btnVerify.setOnClickListener {
            verifyNumber()
        }
    }

    private fun verifyNumber() {
        phoneNumber = binding.edtNumber.text.toString()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}

