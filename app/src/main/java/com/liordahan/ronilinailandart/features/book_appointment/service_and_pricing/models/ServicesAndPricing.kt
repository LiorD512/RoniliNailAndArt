package com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.models

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServicesAndPricing(
    @DocumentId val id: String? = null,
    val service: String? = null,
    val price: String? = null,
    val time: String? = null,
    val sortOrder: Int? = null
): Parcelable