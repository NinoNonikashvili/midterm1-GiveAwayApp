package com.example.midterm1.fragments

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm1.adapter.ImagesAdapter
import com.example.midterm1.databinding.FragmentSearchItemBinding
import com.example.midterm1.models.LoadedItem
import com.google.firebase.database.*


class SearchItemFragment : BaseFragmentWithTheme<FragmentSearchItemBinding>(FragmentSearchItemBinding::inflate) {
    private lateinit var databaseReference:DatabaseReference
    private lateinit var itemsRecycler: RecyclerView
    val list = mutableListOf<LoadedItem>()

override fun start() {

}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseReference = FirebaseDatabase.getInstance().reference.child("items")
        getItemData()

        itemsRecycler = binding.imagesRecycler
        itemsRecycler.layoutManager = LinearLayoutManager(context)
        itemsRecycler.adapter = ImagesAdapter(list)



    }

private fun getItemData(){
    databaseReference?.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val container = snapshot.children

            for(i in container){
                list.add(LoadedItem(
                    Uri.parse(i.child("imageUri").value.toString()),
                    i.child("Uid").value.toString(),
                    i.child("description").value.toString()
                ))
            } }


        override fun onCancelled(error: DatabaseError) {}


    })
}


}