package com.example.midterm1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midterm1.models.UploadedItem

class SharedViewModel: ViewModel() {
    var uploadedItemsList = MutableLiveData<MutableList<UploadedItem>>()



}