package com.codedisruptors.mycart

import android.arch.persistence.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM cartData")
    fun getAllProducts(): MutableList<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Delete
    fun delete(product:Product)

    @Query("DELETE FROM cartData")
    fun deleteAll()

    @Update
    fun update(product:Product)

}