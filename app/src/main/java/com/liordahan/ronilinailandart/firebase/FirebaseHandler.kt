package com.liordahan.ronilinailandart.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseHandler {

    val firebaseStore = FirebaseFirestore.getInstance()
    val firebaseStorage = FirebaseStorage.getInstance()

    val pricingTable = "Pricing"
    val workingHoursTable = "WorkingHours"

    val imagesDir = "images"

}