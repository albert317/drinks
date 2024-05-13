package com.albert.infinitespirit.features.drink.data.datasource

import com.albert.infinitespirit.common.domain.Failure
import com.albert.infinitespirit.common.domain.Result
import com.albert.infinitespirit.features.drink.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.drink.domain.Drink
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DrinkDataSourceImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    DrinkDataSource {

    override suspend fun getDrink(id: String): Flow<Result<Drink?>> {
        return callbackFlow {
            val document =
                fireStore.collection("Drink").document(id).addSnapshotListener { value, error ->
                    if (error != null) {
                        trySend(Result.Error(Failure.CustomError(error.message ?: "Unknown Error")))
                    }
                    val drink = value?.toObject(Drink::class.java)
                    if (drink != null) {
                        trySend(Result.Success(drink))
                    } else {
                        trySend(Result.Error(Failure.CustomError("Drink not found")))
                    }
                }
            awaitClose {
                document.remove()
            }
        }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getDrinks(): Flow<Result<List<Drink>>> = callbackFlow {
        val listenerRegistration =
            fireStore.collection("Drink").addSnapshotListener { value, error ->
                if (error != null) {
                    trySend(Result.Error(Failure.CustomError(error.message ?: "Unknown Error")))
                } else {
                    val drinks = value?.documents?.mapNotNull { document ->
                        document.toObject(Drink::class.java)?.copy(id = document.id)
                    }
                    if (drinks != null) {
                        trySend(Result.Success(drinks))
                    } else {
                        trySend(Result.Error(Failure.CustomError("Drinks not found")))
                    }
                }
            }

        awaitClose {
            listenerRegistration.remove()
        }
    }
}