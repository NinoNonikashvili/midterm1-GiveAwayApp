package com.example.midterm1.fragments


import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.midterm1.databinding.FragmentUserPageBinding
import com.example.midterm1.models.UserProperties
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.midterm1.R




class UserPage : BaseFragmentWithTheme<FragmentUserPageBinding>(FragmentUserPageBinding::inflate) {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private  lateinit var userProperties: UserProperties
    override fun start() {
        databaseReference = FirebaseDatabase.getInstance().reference.child("users")
        auth = FirebaseAuth.getInstance()
        getUserProperties()
        addAdapterToDropDown()
        addListenerOnSearchBtn()
        addListenerOnUploadBtn()
    }


private fun getUserProperties(){
    //get user properties from firebase database and store in variable
    Toast.makeText(context, "${auth.currentUser?.uid}", Toast.LENGTH_LONG).show()

    databaseReference.addValueEventListener(object:ValueEventListener{

        override fun onDataChange(snapshot: DataSnapshot) {
            userProperties =UserProperties(snapshot.child("${auth.currentUser?.uid}").child("username").value.toString(),
                snapshot.child("${auth.currentUser?.uid}").child("email").value.toString(),
                snapshot.child("${auth.currentUser?.uid}").child("address").value.toString(),
                snapshot.child("${auth.currentUser?.uid}").child("phoneNumber").value.toString())
            return setUserProperties(userProperties)

        }

        override fun onCancelled(error: DatabaseError) {}

    })

}
    private fun setUserProperties(userProperties: UserProperties) {


    }
    private fun addAdapterToDropDown(){

        val items = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, items)
        binding.dropdownCategories.setAdapter(arrayAdapter)

    }
    private fun addListenerOnSearchBtn(){
        binding.search.setOnClickListener{
            val selectedcategory = binding.dropdownCategories.text.toString()
            if(selectedcategory=="toys")
                Toast.makeText(context, "its toy", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_userPage_to_searchItemFragment)

//            if(selectedcategory=="jewelry")
//                Toast.makeText(context, "its jewelry", Toast.LENGTH_LONG).show()
//            if(selectedcategory=="clothing")
//                Toast.makeText(context, "its clothing", Toast.LENGTH_LONG).show()
//            else
//                Toast.makeText(context, "its null", Toast.LENGTH_LONG).show()

        }
    }
    private fun addListenerOnUploadBtn(){
        binding.upload.setOnClickListener{
            val selectedcategory = binding.dropdownCategories.text.toString()
            findNavController().navigate(R.id.action_userPage_to_uploadFragment)
//            if(selectedcategory=="toys")
//                Toast.makeText(context, "its toy", Toast.LENGTH_LONG).show()
//            if(selectedcategory=="jewelry")
//                Toast.makeText(context, "its jewelry", Toast.LENGTH_LONG).show()
//            if(selectedcategory=="clothing")
//                Toast.makeText(context, "its clothing", Toast.LENGTH_LONG).show()
//            else
//                Toast.makeText(context, "its null", Toast.LENGTH_LONG).show()

        }

    }


}

