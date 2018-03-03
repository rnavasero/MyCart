package com.codedisruptors.mycart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_product.view.*

class ProductAdapter(private var mContext:Context): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ProductAdapter.ViewHolder {

        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_product,parent,false))
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder?.bindItemByPosition(position)

    }


    override fun getItemCount(): Int {
        return 20
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private var qty = 0
        private var mActivity:MainActivity = mContext as MainActivity

        fun bindItemByPosition(position: Int){
            itemView.btn_add.setOnClickListener{
                qty+=1
                setCount(qty)
                mActivity.productQty+=1
            }

            itemView.btn_minus.setOnClickListener{
                qty-=1
                if(qty<=0){
                    qty=0
                }
                setCount(qty)
                mActivity.productQty-=1
            }
        }
        private fun setCount(qty:Int){
                itemView.tv_qty.text = qty.toString()
                if(qty<=0){
                    itemView.tv_qty.visibility=View.GONE
                    itemView.btn_minus.visibility=View.GONE
                }
                else {
                    itemView.tv_qty.visibility=View.VISIBLE
                    itemView.btn_minus.visibility=View.VISIBLE
                }
        }



    }
}