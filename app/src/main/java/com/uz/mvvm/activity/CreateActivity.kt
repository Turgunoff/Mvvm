package com.uz.mvvm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.uz.mvvm.R
import com.uz.mvvm.databinding.ActivityCreateBinding
import com.uz.mvvm.databinding.ActivityMainBinding
import com.uz.mvvm.model.Note
import com.uz.mvvm.retrofit.RetrofitInstance
import com.uz.mvvm.retrofit.ServiceApi
import com.uz.mvvm.viewModel.CreateViewModel
import com.uz.mvvm.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateBinding
    var note: Note? = null
    private var idExtra = 0
    lateinit var viewModel: CreateViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
        binding.btPost.setOnClickListener {
            val note = Note(
                binding.titleEditText.text.toString(),
                binding.bodyEditText.text.toString(),
                0,
                0
            )
            if (idExtra == 0) {
                viewModel.insertNote(note)
                viewModel.allInsertNote.observe(this) {
                    it!!
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            } else {
                note.id = (idExtra)
                viewModel.updateNote(idExtra, note)
                viewModel.allUpdateNote.observe(this) {
                    it!!
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
        if (intent.hasExtra("IdExtra")) {
            idExtra = intent.extras!!.getInt("IdExtra")
            binding.btPost.text = "Update"
            viewModel.getPostApi(idExtra)
            viewModel.allGetNote.observe(this) {
                if (note != null) {
                    binding.titleEditText.setText(note!!.title)
                    binding.bodyEditText.setText(note!!.body)
                }
            }
        }

    }
}