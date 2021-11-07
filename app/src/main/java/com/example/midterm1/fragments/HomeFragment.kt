package com.example.midterm1.fragments

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.midterm1.R
import com.example.midterm1.databinding.FragmentHomeBinding
import com.example.midterm1.extensions.checkIfEmailisCorrect
import com.example.midterm1.extensions.checkIfEmpty
import com.example.midterm1.models.editTextsWithLayouts
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate){
    private lateinit var auth: FirebaseAuth
    override fun start() {
        auth = FirebaseAuth.getInstance()
        addListenerOnRegisterBtn()
        addListenerOnLoginBtn()

    }
    private fun addListenerOnRegisterBtn(){
        binding.TVRegister.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }
    }
    private fun addListenerOnLoginBtn(){
        binding.MBLogin.setOnClickListener{
            validateAndSignIn()
        }
    }
private fun validateAndSignIn(){
    val loginFields = listOf(
        editTextsWithLayouts(binding.ETPassword, binding.textInputLayout),
        editTextsWithLayouts(binding.ETEmail, binding.textInputLayout2)

    )
    //check if fields are empty
    loginFields.checkIfEmpty()
    //check if email is formatted correctly
    loginFields.checkIfEmailisCorrect()
    auth.signInWithEmailAndPassword(binding.ETEmail.text.toString(), binding.ETPassword.text.toString())
        .addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Toast.makeText(context, "user signed in successfully", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_homeFragment_to_userPage)
            }
            else
                Toast.makeText(context, "sign in failed", Toast.LENGTH_LONG).show()

        }

}

}