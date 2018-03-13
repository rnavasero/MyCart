package com.codedisruptors.mycart


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.codedisruptors.mycart.R.id.*
import com.example.mystore.Util
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_registration.*
import java.nio.charset.Charset


/**
 * A simple [Fragment] subclass.
 */
class RegistrationFragment : Fragment() {
    var firstname = ""
    var lastname = ""
    var username = ""
    var password = ""
    val TAG = "RegistrationFragment"




    override fun onCreateView(inflater: LayoutInflater?, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
               return inflater!!.inflate(R.layout.fragment_registration, parent, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener{
            onRegisterButtonClicked()
        }

    }

    fun onRegisterButtonClicked(){
        if(isAccountValid())
        {
            val params:MutableMap<String,String> = HashMap()
            params.put("username", et_username.text.toString())
            params.put("password", et_creg_password.text.toString())
            params.put("firstName", et_firstname.text.toString())
            params.put("lastName", et_lastname.text.toString())

            val pd = ProgressDialog(context)

            pd.apply {
                setMessage("Please wait...")
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                show()
            }
            val queue = Volley.newRequestQueue(context)
            val loginRequest = object : StringRequest(Method.POST, "http://api-express-staging.codedisruptors.com:7010/user", Response.Listener { response ->
                pd.dismiss()
                Toast.makeText(context, "ACCOUNT CREATION SUCCESSFUL!",
                        Toast.LENGTH_SHORT).show()

            }, Response.ErrorListener { error ->
                //Do something
                pd.dismiss()
                if(error.networkResponse !=null) {
                    val err = String(error.networkResponse.data, Charset.forName("UTF-8"))
                    Toast.makeText(context, err,
                            Toast.LENGTH_SHORT).show()
                }




            }){
                //Send parameters
                override fun getParams(): MutableMap<String, String> {
                    return params
                }
            }

            queue.add(loginRequest)
        }
        else
        {
            Toast.makeText(context, "INVALID ACCOUNT",
                    Toast.LENGTH_SHORT).show()
        }





    }

    private fun clearAll() {
        et_firstname.setText("")
        et_lastname.setText("")
        et_password.setText("")
        et_reg_password.setText("")
        et_creg_password.setText("")
    }


    private fun isAccountValid(): Boolean {

        if(Util.isEmpty(et_firstname,"First Name is required"))

            if(Util.isEmpty(et_lastname, "Last Name is required"))

                if(Util.isEmpty(et_username, "Username is required"))

                    if(Util.isEmpty(et_reg_password, "Password is required"))

                        if (et_reg_password.text.toString() != et_creg_password.text.toString()) {
                            et_reg_password.error = "Password does not match"
                            et_creg_password.error = "Password does not match"
                            return false
                        }

        return true
    }

}
