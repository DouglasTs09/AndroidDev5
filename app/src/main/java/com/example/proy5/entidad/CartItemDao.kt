package com.example.proy5.entidad

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {

    @Query("SELECT * FROM cart_items")
    fun obtenerItemsCart(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarCartItem(cartItem: CartItem)

    @Update
    suspend fun actualizarCartItem(cartItem: CartItem)

    @Delete
    suspend fun eliminarCartItem(cartItem: CartItem)

}