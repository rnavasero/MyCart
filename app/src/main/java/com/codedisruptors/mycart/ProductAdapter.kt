package com.codedisruptors.mycart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import junit.runner.Version.id
import kotlinx.android.synthetic.main.row_product.view.*
import kotlinx.android.synthetic.main.cartview.*
import kotlinx.android.synthetic.main.cartview.view.*

class ProductAdapter(private var mContext:Context, var products:MutableList<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ProductAdapter.ViewHolder {

        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_product,parent,false))

    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bindItemByPosition(position)

    }


    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private var mActivity:MainActivity = mContext as MainActivity


        fun bindItemByPosition(position:Int){
            Picasso.with(itemView.context).load(products[position].pimageURL).into(itemView.productImage)
            itemView.productName.text = products[position].pName
            itemView.productPrice.text = products[position].pPrice.toString()
            itemView.productDescription.text = products[position].pDesc

            val product = products[position]
            updateProduct(product)

            itemView.btn_add.setOnClickListener{
                mActivity.productTotalQty+=1
                mActivity.setCartcount()
                product.pQty += 1
                updateProduct(product)
                settotalCount(mActivity.productTotalQty)
            }

            itemView.btn_minus.setOnClickListener{
                if(product.pQty<=0){
                    product.pQty=0
                }
                mActivity.productTotalQty-=1
                mActivity.setCartcount()
                product.pQty -= 1
                updateProduct(product)
                settotalCount(mActivity.productTotalQty)
            }
        }
        private fun updateProduct(product: Product){
            itemView.tv_qty.text = product.pQty.toString()
            if(product.pQty<=0){
                itemView.tv_qty.visibility=View.GONE
                itemView.btn_minus.visibility=View.GONE
            }
            else {
                itemView.tv_qty.visibility=View.VISIBLE
                itemView.btn_minus.visibility=View.VISIBLE
            }
        }
        private fun settotalCount(x: Int){
            if(x<=0)
            {
                mActivity.FL_cartview.tv_cart_count.setText(mActivity.productTotalQty.toString())
                mActivity.FL_cartview.tv_cart_count.visibility = View.GONE
            }
            else
            {
                mActivity.FL_cartview.tv_cart_count.setText(mActivity.productTotalQty.toString())
                mActivity.FL_cartview.tv_cart_count.visibility = View.VISIBLE
            }
        }

        private fun checkItem(itemQty:Int){
            if(itemQty>0)
            {
            }
            else
            {

            }

        }






    }

}