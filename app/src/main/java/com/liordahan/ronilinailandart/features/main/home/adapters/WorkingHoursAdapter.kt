package com.liordahan.ronilinailandart.features.main.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.liordahan.ronilinailandart.databinding.ListItemBaseHorizontalBinding
import com.liordahan.ronilinailandart.features.main.home.models.WorkingHours

class WorkingHoursAdapter:
    ListAdapter<WorkingHours, RecyclerView.ViewHolder>(DiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WorkingHoursHolder(
            ListItemBaseHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val itemViewHolder = holder as WorkingHoursHolder
        itemViewHolder.bind(item)
    }

    private class DiffCallback : DiffUtil.ItemCallback<WorkingHours>() {

        override fun areItemsTheSame(
            oldItem: WorkingHours,
            newItem: WorkingHours
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WorkingHours,
            newItem: WorkingHours
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class WorkingHoursHolder(private val binding: ListItemBaseHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        workingHours: WorkingHours
    ) {

        binding.apply {

            baseHorizontalTitle.text = workingHours.day
            baseHorizontalSubject.text = workingHours.time
        }

    }
}