package com.albert.infinitespirit.features.onboarding.data.datasource

import com.albert.infinitespirit.features.onboarding.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.onboarding.domain.Drink
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DrinkDataSourceImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    DrinkDataSource {

    override suspend fun getDrink(id: String): Drink? {
        //val snapshot = fireStore.collection("Drink").document(id).get().await()
        //return snapshot.toObject(Drink::class.java)
        return Drink(
            id = "1",
            name = "Cappuccino",
            description = "A cappuccino is an espresso-based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam.",
            origin = "Peru",
            photo = "https://firebasestorage.googleapis.com/v0/b/infinite-spirit.appspot.com/o/cappuccino.jpg?alt=media&token=3b3b3b3b-3b3b-3b3b-3b3b-3b3b3b3b3b3b",
            timeRegister = "2021-09-01T00:00:00.000Z",
            timeUpdate = "2021-09-01T00:00:00.000Z"
        )
    }

    override suspend fun getDrinks(): List<Drink> {
        val snapshot = fireStore.collection("Drink").get().await()
        return snapshot.toObjects(Drink::class.java)
    }
}