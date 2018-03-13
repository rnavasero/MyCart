package com.codedisruptors.mycart

import android.app.FragmentManager
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by codemagnus on 3/12/18.
 */
class CartAdapter(var mContext: Context,  var fragment:FragmentManager, var cartitems:MutableList<Product>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    val mFragment = fragment
    val mCartItems = cartitems

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CartAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_cart_product,parent,false))
    }

    override fun getItemCount(): Int {
        return mCartItems.size
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder?, position: Int) {
        holder?.bindCartItems(position)
    }


    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {

        fun bindCartItems(position: Int){

        }



    }


}

