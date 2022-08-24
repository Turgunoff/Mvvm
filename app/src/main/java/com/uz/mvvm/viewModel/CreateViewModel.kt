package com.uz.mvvm.viewModel

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uz.mvvm.activity.MainActivity
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
class CreateViewModel : ViewModel() {

    val allInsertNote = MutableLiveData<Note>()
    val allUpdateNote = MutableLiveData<Note>()
    val allGetNote = MutableLiveData<Note>()


    fun insertNote(note: Note) {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.createPost(note)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>, response: Response<Note?>) {
                if (response.isSuccessful) {
                    allInsertNote.value = response.body()
                }
            }

            override fun onFailure(call: Call<Note?>, t: Throwable) {
                allInsertNote.value = null
            }
        })
    }

    fun updateNote(idExtra: Int, note: Note) {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.updatePost(idExtra, note)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>, response: Response<Note?>) {
                allUpdateNote.value = response.body()
            }

            override fun onFailure(call: Call<Note?>, t: Throwable) {
                allUpdateNote.value = null
            }
        })
    }

    fun getPostApi(idExtra: Int) {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.getNote(idExtra)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>, response: Response<Note?>) {
                allGetNote.value = response.body()
            }

            override fun onFailure(call: Call<Note?>, t: Throwable) {
                allGetNote.value = null
            }
        })
    }
}