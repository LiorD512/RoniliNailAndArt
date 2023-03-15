package com.liordahan.ronilinailandart.features.main.home.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.liordahan.ronilinailandart.firebase.FirebaseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeRepository(
    private val firebaseHandler: FirebaseHandler
) {

    fun getWorkingHours(): Task<QuerySnapshot>{
        return firebaseHandler.firebaseStore.collection(firebaseHandler.workingHoursTable).get()
    }

    suspend fun getImages(): List<String> = withContext(Dispatchers.IO){
        val imagesList = mutableListOf<String>()
        val listRef = firebaseHandler.firebaseStorage.reference.child("images")
        val items = listRef.listAll().await().items
        items.forEach {storageRef ->
            imagesList.add(storageRef.downloadUrl.await().toString())
        }
        return@withContext imagesList
    }
}