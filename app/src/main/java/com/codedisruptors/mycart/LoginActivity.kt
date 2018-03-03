package com.codedisruptors.mycart

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    var session:Session?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = Session(this)

        if(session?.isUserActive()!!){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }



        btn_Login.setOnClickListener{
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()

            session?.setUserActive(true)
        }
    }
}
