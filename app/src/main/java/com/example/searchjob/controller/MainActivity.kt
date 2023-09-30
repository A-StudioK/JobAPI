package com.example.searchjob.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchjob.adapter.JobAdapter
import com.example.searchjob.network.JobService
import com.example.searchjob.R
import com.example.searchjob.model.Job
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var jobs: List<Job>
    lateinit var jobAdapter: JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btSearch = findViewById<Button>(R.id.btSearch)

        btSearch.setOnClickListener {
            searchJob()
        }
    }

    private fun searchJob() {
        val etDescription = findViewById<TextInputEditText>(R.id.etDescription)
        val description = etDescription.text.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://651372d78e505cebc2e9dc74.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(JobService::class.java)

        val request = service.searchJob(description)

        request.enqueue(object: Callback<List<Job>> {
            override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                jobs = response.body()!!
                jobAdapter = JobAdapter(jobs)

                val rvJob = findViewById<RecyclerView>(R.id.rvJob)
                rvJob.adapter = jobAdapter
                rvJob.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }
        })
    }
}