package com.codedisruptors.mycart

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Product::class)], version = 1)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    companion object {
        var INSTANCE: ProductDataBase? = null

        fun getInstance(context: Context): ProductDataBase {
            if (INSTANCE == null) {
                synchronized(ProductDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ProductDataBase::class.java, "com.codedisruptors.mycart.db").build()
                }
            }
            return INSTANCE as ProductDataBase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
