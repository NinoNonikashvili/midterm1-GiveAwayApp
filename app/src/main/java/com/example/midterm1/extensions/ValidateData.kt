package com.example.midterm1.extensions

import android.util.Patterns
import com.example.midterm1.models.editTextsWithLayouts


fun List<editTextsWithLayouts>.checkIfEmpty(){
    for(i in this){
        i.TIL?.error = null
        if(i.ET?.text.toString().isEmpty()) {
            i.TIL?.error = "please, fill out the field"
            i.ET?.requestFocus()
            return
        }
    }

}
fun List<editTextsWithLayouts>.checkIfEmailisCorrect(){
    val email = this[1].ET?.text.toString()
    if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        this[1].TIL?.error = "please, write valid email"
        this[1].ET?.requestFocus()
        return
    }
}
