package com.albert.infinitespirit.features.drink.data.datasource

import com.albert.infinitespirit.features.drink.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.drink.domain.Drink
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DrinkDataSourceImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    DrinkDataSource {

    override suspend fun getDrink(id: String): Drink? {
        val snapshot = fireStore.collection("Drink").document(id).get().await()
        return snapshot.toObject(Drink::class.java)
    }

    override suspend fun getDrinks(): List<Drink> {
        val snapshot = fireStore.collection("Drink").get().await()
        return snapshot.toObjects(Drink::class.java)
    }
}