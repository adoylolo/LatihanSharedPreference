package com.muhammadfarhaan.latihanshared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

import com.muhammadfarhaan.latihanshared.Utils.Preferences

class HomeActivity : AppCompatActivity() {

    private lateinit var txtKeluar : TextView
    private lateinit var txtName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        declareView()
        txtKeluar.setOnClickListener {
            //Clear Set Preferences
            Preferences.setLogout(baseContext)

            //Pindah Halaman ke Login
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun declareView() {
        txtKeluar = findViewById(R.id.txt_logout)
        txtName = findViewById(R.id.txtName)

        txtName.text = Preferences.getRegisteredUser(baseContext)
    }
}
