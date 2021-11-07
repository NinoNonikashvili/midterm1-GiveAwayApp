package com.example.midterm1.fragments

import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.midterm1.SharedViewModel
import com.example.midterm1.databinding.FragmentUploadBinding
import com.example.midterm1.models.UploadedItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.time.LocalDate


class UploadFragment : BaseFragmentWithTheme<FragmentUploadBinding>(FragmentUploadBinding::inflate) {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    var uploadedItemDetailsList = mutableListOf<UploadedItem>()

    //choose image from phone gallery
    lateinit var UploadedimageUri: Uri
    private val getContent:ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()){imageUri: Uri?->
            if(imageUri!=null) {
                UploadedimageUri = imageUri
                binding.IVItemImage.load(imageUri)
            }
        }

    override fun start() {
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("items")
        setListenerOnChooseBtn()
        setListenerOnUploadBtn()

    }

    fun setListenerOnChooseBtn(){
        binding.chooseImage.setOnClickListener{
            getContent.launch("image/*")
        }
    }

    fun setListenerOnUploadBtn(){
        binding.uploadBtn.setOnClickListener{
            uploadedItemDetailsList.add(UploadedItem(UploadedimageUri.toString(), auth.uid.toString(), binding.tVDescription.text.toString()))
            databaseReference.child("${LocalDate.now()}").setValue(uploadedItemDetailsList)

        }
    }





}