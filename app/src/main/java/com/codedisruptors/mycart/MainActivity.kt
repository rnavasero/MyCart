package com.codedisruptors.mycart

    import android.app.PendingIntent.getActivity
    import android.app.ProgressDialog
    import android.content.Intent
    import android.os.Bundle
    import android.support.v7.widget.LinearLayoutManager
    import android.view.View
    import kotlinx.android.synthetic.main.activity_main.*
    import kotlinx.android.synthetic.main.cartview.*
    import android.content.DialogInterface
    import android.support.v7.app.AlertDialog
    import android.support.v7.app.AppCompatActivity
    import android.widget.Toast
    import com.android.volley.Response
    import com.android.volley.toolbox.StringRequest
    import com.android.volley.toolbox.Volley
    import kotlinx.android.synthetic.main.fragment_cart.*
    import org.json.JSONException
    import org.json.JSONObject
    import java.nio.charset.Charset


    class MainActivity : AppCompatActivity() {
        private val manager = supportFragmentManager
        private var cartList : MutableList<Product> = mutableListOf()
        var productTotalQty = 0
        var productQty:Int = 0
        var productList: MutableList<Product> = mutableListOf()


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val token = Session(this).getToken()
            retrieveProduct(token)

            cart_act.setOnClickListener{
                val transaction = manager.beginTransaction()
                val fragment = CartFragment()
                transaction.replace(R.id.FL_cartact,fragment)
                transaction.addToBackStack(null)
                transaction.commit()

            }

        }


        override fun onBackPressed() {
            val token = Session(this).getToken()
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setIcon(R.drawable.cart)
            builder.setTitle("My Cart")
            builder.setMessage("Are you sure you want to logout?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                val header: MutableMap<String, String> = HashMap()
                header.put("x-access-token", token)
                val queue = Volley.newRequestQueue(this)
                val loginRequest = object : StringRequest(Method.POST, "http://api-express-staging.codedisruptors.com:7010/auth/logout"
                        , Response.Listener { response ->
                    Session(this).setUserActive(false)
                    val toHome = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(toHome)
                    finish()
                }, Response.ErrorListener { error ->
                    //Do something
                    if (error.networkResponse != null) {
                        val err = String(error.networkResponse.data, Charset.forName("UTF-8"))
                        Toast.makeText(this, err,
                                Toast.LENGTH_LONG).show()
                    }

                }) {
                    //Send parameters
                    override fun getHeaders(): MutableMap<String, String> {
                        return header
                    }
                }
                queue.add(loginRequest)

            })
            builder.setNegativeButton("Not Now", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
            val dialog = builder.create()
            dialog.show()
        }


        fun retrieveProduct(token:String){

            val pd = ProgressDialog(this)
            pd.apply {
                setMessage("Please wait...")
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                show()
            }

            val header:MutableMap<String,String> = HashMap()
            header.put("x-access-token", token)

            val queue = Volley.newRequestQueue(this)
            val productRequest = object : StringRequest(Method.GET, "http://api-express-staging.codedisruptors.com:7010/product",
                    Response.Listener { response ->

                        try {
                            val jsonResponse 	    =  JSONObject(response)
                            val jsonData         	=  jsonResponse.getJSONObject("data")
                            val jsonArray        	=  jsonData.getJSONArray("items")
                            var x = 0
                            while ( x < jsonArray.length()){
                                val objectProduct = jsonArray.getJSONObject(x)
                                val product = Product()
                                product.apply {
                                    id = objectProduct.getInt("id")
                                    pName = objectProduct.getString("name")
                                    pDesc = objectProduct.getString("description")
                                    pPrice = objectProduct.getDouble("price")
                                    pimageURL = objectProduct.getString("imageUrl")
                                    pQty = 0
                                    productList?.add(product)
                                    ++x
                                }
                            }

                            rv_Product.layoutManager = LinearLayoutManager(this)
                            if(productList.isNotEmpty())
                                 rv_Product.adapter = ProductAdapter(this, productList)

                        }catch (e:JSONException){
                            e.printStackTrace()
                        }
                        pd.dismiss()

            }, Response.ErrorListener { error ->
                //Do something
                val err = String(error.networkResponse.data, Charset.forName("UTF-8"))
                pd.dismiss()
                if(error.networkResponse !=null) {
                    Toast.makeText(this, err,
                            Toast.LENGTH_SHORT).show()
                }

            }){
                override fun getHeaders(): MutableMap<String, String> {
                    return header
                }
            }

            queue.add(productRequest)

        }

        fun setCartcount(){
            if(productQty<=0){
                tv_cart_count.visibility=View.GONE
            }
            else {
                tv_cart_count.visibility=View.VISIBLE
            }
        }

        fun onClickedCartItems(){
            rv_cart_Product.layoutManager = LinearLayoutManager(this)
            rv_cart_Product.adapter = ProductAdapter(this,productList!!)
            val transaction = manager.beginTransaction()
            val fragment = CartFragment()
            transaction.replace(R.id.FL_cartact,fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        fun isCartFragment():Boolean{
            return true
        }



    }
