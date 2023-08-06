package com.nx.nxfcd.data

import com.google.firebase.firestore.FirebaseFirestore
import com.nx.nxfcd.domain.model.Failure
import com.nx.nxfcd.domain.model.Success
import com.nx.nxfcd.core.Constants.COLLECTION_NAME
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class AppRepositoryImpl {

    private val firestore = FirebaseFirestore.getInstance()

    fun getDataFromFirestore() = callbackFlow {
        val collection = firestore.collection(COLLECTION_NAME)
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null) {
                Success(value)
            } else {
                Failure(error)
            }
            this.trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}