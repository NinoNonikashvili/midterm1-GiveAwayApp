package com.example.midterm1.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.midterm1.MainActivity
import com.example.midterm1.R
import com.example.midterm1.databinding.FragmentSplashScreenBinding



class splashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    private lateinit var handler: Handler

    override fun start() {
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
        }, 500)
    }


}