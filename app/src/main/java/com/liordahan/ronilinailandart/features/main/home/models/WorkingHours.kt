package com.liordahan.ronilinailandart.features.main.home.models

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkingHours(
    @DocumentId val id: String? = null,
    val day: String? = null,
    val time: String? = null,
    val sortOrder: Int? = null
): Parcelable