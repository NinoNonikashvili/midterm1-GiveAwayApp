package com.example.midterm1.fragments

import android.widget.Toast
import com.example.midterm1.databinding.FragmentRegisterBinding
import com.example.midterm1.extensions.checkIfEmailisCorrect
import com.example.midterm1.extensions.checkIfEmpty
import com.example.midterm1.models.UserProperties
import com.example.midterm1.models.editTextsWithLayouts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun start() {
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("users")
        binding.MBRegistered.setOnClickListener{
            validateAndSignUp()
        }

    }


fun validateAndSignUp(){
    //if any of the fields is empty

    val fields = listOf(
        editTextsWithLayouts(binding.ETUsername, binding.textInputLayout4),
        editTextsWithLayouts(binding.ETEmail, binding.textInputLayout3),
        editTextsWithLayouts(binding.ETAdress, binding.textInputLayout5),
        editTextsWithLayouts(binding.ETPhoneNumber, binding.textInputLayout6),
        editTextsWithLayouts(binding.ETPassword, binding.textInputLayout7),
        editTextsWithLayouts(binding.ETConfirmPassword, binding.textInputLayout8)
    )
    //check if fields are empty
    fields.checkIfEmpty()
    //check if email is not formatted correctly
    fields.checkIfEmailisCorrect()
    //check if passwords match
    val password = binding.ETPassword.text.toString()
    val confirmedPassword = binding.ETConfirmPassword.text.toString()
    if(password!=confirmedPassword){
        fields[5].TIL?.error = "passwords do not match"
        fields[5].ET?.requestFocus()
        return
    }
    //add user
    val email = fields[1].ET?.text.toString()
    val newUser = UserProperties(
        binding.ETUsername.text.toString(),
        binding.ETEmail.text.toString(),
        binding.ETAdress.text.toString(),
        binding.ETPhoneNumber.text.toString()
    )

    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(){task ->
        if(task.isSuccessful) {
            Toast.makeText(context, "user added successfully ${auth.currentUser?.uid}", Toast.LENGTH_LONG).show()
            databaseReference.child("${auth.currentUser?.uid}").setValue(newUser)
        }
        else
            Toast.makeText(context, "registration failed", Toast.LENGTH_LONG).show()

    }
}
}