package com.uz.mvvm.viewModel

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uz.mvvm.model.Note
import com.uz.mvvm.retrofit.RetrofitInstance
import com.uz.mvvm.retrofit.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Eldor Turgunov on 24.08.2022.
 * Mvvm
 * eldorturgunov777@gmail.com
 */
class MainViewModel : ViewModel() {

    val allNotes = MutableLiveData<ArrayList<Note>>()
    val deletedNote = MutableLiveData<Note>()

    fun getNotes(){
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<ArrayList<Note>> = serviceApi.getNotes()
        call.enqueue(object : Callback<ArrayList<Note>> {
            override fun onResponse(
                call: Call<ArrayList<Note>>,
                response: Response<ArrayList<Note>>
            ) {
                allNotes.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Note>>, t: Throwable) {
                allNotes.value = null
            }

        })
    }

    fun apiPostDelete(note: Note){
        val serviceApi: ServiceApi =
            RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.deletePost(note.id)
        call.enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                deletedNote.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                deletedNote.value = null
            }
        })
    }
}