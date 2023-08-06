package com.nx.nxfcd.domain.model

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

sealed class Response

data class Success(val querySnapshot: QuerySnapshot?): Response()

data class Failure(val exception: FirebaseFirestoreException?): Response()