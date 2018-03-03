package com.codedisruptors.mycart

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_product.*


class MainActivity : AppCompatActivity() {
    private var productDB:ProductDataBase?  = null

    var productQty = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        productDB = ProductDataBase.getInstance(this@MainActivity)


        rv_Product.layoutManager = LinearLayoutManager(this)
        rv_Product.adapter       = ProductAdapter(this)




    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    fun setCartcount(){
        Log.i("MainActivity","Total Count: $productQty")
    }
}
