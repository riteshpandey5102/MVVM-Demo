package com.example.mvvmdemo.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mvcdemo.adapter.Adapter
import com.example.mvpdemo.model.DataItem
import com.example.mvpdemo.model.MainModel
import com.example.mvvmdemo.R
import com.example.mvvmdemo.viewmodel.MainViewModel
import com.example.mvvmdemo.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainModel: MainModel
    private lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: Adapter
    private lateinit var main_activity_recyclerView: RecyclerView
    private lateinit var main_activity_progress_bar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loadView()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainModel= MainModel()
        mainViewModelFactory= MainViewModelFactory(mainModel)
        mainViewModel= ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)
        mainViewModel.fetchData()

        listenToObservables()
    }

    private fun listenToObservables() {
        showProgressBar()
        mainViewModel.getResultListObservable().observe(this) {
            hideProgressBar()
            updateRecyclerView(it!!)
        }
        mainViewModel.getResultListErrorObservable().observe(this) {
            hideProgressBar()
            showErrorMessage(it!!.message())
        }
    }

    private fun loadView() {
        setContentView(R.layout.activity_main)
        main_activity_recyclerView = findViewById(R.id.main_activity_recyclerView)
        main_activity_progress_bar = findViewById(R.id.main_activity_progress_bar)
        adapter= Adapter()
        main_activity_recyclerView.adapter = adapter
        adapter setItemClickMethod { goToDetailActivity(it) }
        adapter.setItemShowMethod { mainViewModel fetchItemTextFrom it }
    }

    fun showProgressBar() {
        main_activity_progress_bar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        main_activity_progress_bar.visibility = View.GONE
    }

    fun showErrorMessage(errorMsg: String) {
        Toast.makeText(this, "Error retrieving data: $errorMsg", Toast.LENGTH_SHORT).show()
    }

    fun updateRecyclerView(t: List<DataItem>) {
        adapter.updateList(t)
        adapter.notifyDataSetChanged()
    }

    fun goToDetailActivity(item: DataItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.Constants.TITLE, item.title)
        intent.putExtra(DetailActivity.Constants.TYPE, item.type)
        intent.putExtra(DetailActivity.Constants.YEAR, item.year.toString())
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.onStop()
    }

}