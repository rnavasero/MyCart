package com.codedisruptors.mycart

import android.app.*
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import java.nio.charset.Charset
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {



    private val manager = supportFragmentManager
    var token = ""
    var session:Session?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = Session(this)

        if(session?.isUserActive()!!){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }



        btn_Login.setOnClickListener {

            if (et_user_id.text.isEmpty()) {
                et_user_id.error = "Username required!"
                return@setOnClickListener
            }

            if (et_password.text.isEmpty()) {
                et_password.error = "Password required!"
                return@setOnClickListener

            } else {
                val pd = ProgressDialog(this)
                pd.apply {
                    setMessage("Please wait...")
                    setCancelable(false)
                    setCanceledOnTouchOutside(false)
                    show()
                }
                val params: MutableMap<String, String> = HashMap()
                params.put("username", et_user_id.text.toString())
                params.put("password", et_password.text.toString())
                val queue = Volley.newRequestQueue(this)
                val loginRequest = object : StringRequest(Method.POST, "http://api-express-staging.codedisruptors.com:7010/auth/login"
                        , Response.Listener { response ->
                    Session(this).setUserActive(true)

                    try {
                        val jsonResponse = JSONObject(response)
                        val jsonData = jsonResponse.getJSONObject("data")
                        val jsonArray = jsonData.getJSONArray("items")
                        val jsonUser = jsonArray.getJSONObject(0)
                        token = jsonUser.getString("token")
                        Session(this).setToken(token)


                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    pd.dismiss()
                    Toast.makeText(this, "LOGIN SUCCESSFULL",
                            Toast.LENGTH_LONG).show()
                    val loginIntent = Intent(this, MainActivity::class.java)
                    startActivity(loginIntent)
                    session?.setUserActive(true)
                }, Response.ErrorListener { error ->
                    //Do something
                    pd.dismiss()
                    if (error.networkResponse != null) {
                        val err = String(error.networkResponse.data, Charset.forName("UTF-8"))
                        Toast.makeText(this, err,
                                Toast.LENGTH_LONG).show()
                    }

                }) {
                    //Send parameters
                    override fun getParams(): MutableMap<String, String> {

                        return params
                    }
                }
                queue.add(loginRequest)
            }
        }




        tv_create_account.setOnClickListener {
            onCreateAccountClicked()
        }


    }


    fun onCreateAccountClicked(){
        val transaction = manager.beginTransaction()
        val fragment = RegistrationFragment()
        transaction.replace(R.id.CL_view,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}
