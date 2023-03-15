package com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.liordahan.ronilinailandart.databinding.ListItemBaseHorizontalBinding
import com.liordahan.ronilinailandart.databinding.ListItemServiceAndPriceBinding
import com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.models.ServicesAndPricing

class ServiceAndPricingAdapter(
    private val listener: ServiceAndPricingAdapterListener
) :
    ListAdapter<ServicesAndPricing, RecyclerView.ViewHolder>(DiffCallback()) {


    interface ServiceAndPricingAdapterListener {
        fun onServiceClick(serviceId: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ServiceAndPricingHolder(
            ListItemServiceAndPriceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val itemViewHolder = holder as ServiceAndPricingHolder
        itemViewHolder.bind(item, listener)
    }

    private class DiffCallback : DiffUtil.ItemCallback<ServicesAndPricing>() {

        override fun areItemsTheSame(
            oldItem: ServicesAndPricing,
            newItem: ServicesAndPricing
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ServicesAndPricing,
            newItem: ServicesAndPricing
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class ServiceAndPricingHolder(private val binding: ListItemServiceAndPriceBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        servicesAndPricing: ServicesAndPricing,
        listener: ServiceAndPricingAdapter.ServiceAndPricingAdapterListener
    ) {

        binding.apply {

            listItemServiceName.text = servicesAndPricing.service
            listItemServiceMoney.text = servicesAndPricing.price
            listItemServiceTime.text = servicesAndPricing.time

            root.setOnClickListener {
                listener.onServiceClick(servicesAndPricing.id)
            }
        }

    }
}