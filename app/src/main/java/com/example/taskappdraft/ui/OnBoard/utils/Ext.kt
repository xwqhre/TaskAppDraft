package com.example.taskappdraft.ui.OnBoard.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskappdraft.R

fun Fragment.showToast(msg:String){
    Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).placeholder(R.drawable.raund).into(this)
}