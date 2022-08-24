package com.uz.mvvm.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.uz.mvvm.adapter.RetrofitAdapter
import com.uz.mvvm.databinding.ActivityMainBinding
import com.uz.mvvm.model.Note
import com.uz.mvvm.retrofit.RetrofitInstance
import com.uz.mvvm.retrofit.ServiceApi
import com.uz.mvvm.utils.Utils
import com.uz.mvvm.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    var doubleBackToExitPressedOnce = false
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        createData()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun createData() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        viewModel.getNotes()
        viewModel.allNotes.observe(this) {
            refreshAdapter(it)
        }
    }

    private fun refreshAdapter(note: ArrayList<Note>) {
        val adapter = RetrofitAdapter(this, note, this@MainActivity)
        binding.recyclerView.adapter = adapter
    }
}