package com.example.proy5.entidad

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class], version = 1)
abstract class CartItemDataBase: RoomDatabase() {

    abstract fun cartItem(): CartItemDao

    companion object {

        @Volatile
        private var INSTANCE: CartItemDataBase? = null

        fun getDataBase(context: Context) : CartItemDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartItemDataBase::class.java,
                    "carrito_compras")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}