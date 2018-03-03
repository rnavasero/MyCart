package com.codedisruptors.mycart

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cartData")
class Product {
    constructor()
    @PrimaryKey(autoGenerate = true) var dbId: Int = 0
    @ColumnInfo(name = "productId") var id: String = ""
    @ColumnInfo(name = "productName") var name: String = ""
    @ColumnInfo(name = "description")var description: String = ""
    @ColumnInfo(name = "price")var price: Int = 0
    @ColumnInfo(name = "quantity")var qty: Int = 0
}

