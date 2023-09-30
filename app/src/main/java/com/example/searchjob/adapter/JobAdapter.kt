package com.example.searchjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchjob.R
import com.example.searchjob.model.Job

class JobAdapter(private val jobs: List<Job>): RecyclerView.Adapter<JobPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_job, parent, false)
        return JobPrototype(view)
    }

    override fun onBindViewHolder(holder: JobPrototype, position: Int) {
        holder.bind(jobs[position])
    }

    override fun getItemCount(): Int {
        return jobs.size
    }
}

class JobPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    private val tvCompany = itemView.findViewById<TextView>(R.id.tvCompany)
    private val ivLogo = itemView.findViewById<ImageView>(R.id.ivLogo)

    fun bind(job: Job) {
        tvCompany.text = job.empresa
        tvDescription.text = job.description
        //tvDescription.text = Html.fromHtml(job.description, 1)
        tvTitle.text = job.job

        Glide.with(itemView).load(job.image).into(ivLogo)
    }
}
