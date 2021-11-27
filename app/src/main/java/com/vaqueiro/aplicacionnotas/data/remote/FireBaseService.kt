package com.vaqueiro.aplicacionnotas.data.remote

import com.google.firebase.firestore.FirebaseFirestore

class FireBaseService {
    fun collectionRef(collection:String) = FirebaseFirestore.getInstance().collection(collection)
    fun notesRef() = collectionRef("series")
    fun noteRef() = notesRef().document()
}