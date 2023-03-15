package com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.liordahan.ronilinailandart.firebase.FirebaseHandler

class ServiceAndPricingRepository(
    private val firebaseHandler: FirebaseHandler
) {

    fun getServiceAndPricing(): Task<QuerySnapshot> {
        return firebaseHandler.firebaseStore.collection(firebaseHandler.pricingTable).get()
    }


}