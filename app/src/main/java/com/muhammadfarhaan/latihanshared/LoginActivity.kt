package com.muhammadfarhaan.latihanshared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.text.TextUtils
import android.view.View
import android.widget.TextView

import com.muhammadfarhaan.latihanshared.Utils.Preferences
import com.muhammadfarhaan.latihanshared.Model.UserModel

/*
* Tanggal Pengerjaan  : 27-April-2020
* Deskripsi Pekerjaan : Membuat package Model dan Utils.
*                       Membuat values color, dimen, strings, dan styles.
*                       Membuat drawable bg_btn_daftar, bg_btn_masuk, cursor_color, dan menambahkan ic_child_friendly.png .
*                       Membuat class UserModel pada package Model.
*                       Membuat class Preferences dan Interface UtilStatic pada package Utils.
*                       Membuat activity LoginActivity, Register Activity, dan HomeActivity. Serta
*                       Membuat layout activity_login, activity_register, dan activity_home.
*                       Membuat deklarasi username, password, dan phone pada class UserModel serta membuat
*                       getter-setter nya.
*                       Membuat deklarasi default untuk DEFAULT_STRING, STRING_0, STRING_NULL, DEFAULT_INT, dan DEFAULT_BOOLEAN.
*                       Membuat Context sebagai "penghubung" antar class
*                       Membuat fungsi-fungsi pada class Preferences untuk :
*                           - Menyimpan data user menggunakan SharedPreferences
*                           - Mengecek username
*                           - Mengecek password
*                           - Mengecek phone (walaupun tidak terpakai)
*                           - Menyimpan data status login
*                           - Mengecek status login
*                           - Logout dengan menghapus status login (false)
*                       Membuat validasi untuk login
*                       Membuat validasi untuk register
*                       Membuat fungsi untuk menampilkan username si User yang login pada Home
* NIM                 : 10117145
* Nama                : Muhammad Farhaan
* Kelas               : IF-4
* */

class LoginActivity : AppCompatActivity() {

    private lateinit var txtMasuk: TextView
    private lateinit var txtRegister: TextView
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        declareView()

        txtMasuk.setOnClickListener { validasiLogin() }

        txtRegister.setOnClickListener {
            startActivity(
                Intent(
                    baseContext,
                    RegisterActivity::class.java
                )
            )
        }
    }

    /**
     * ke MainActivity jika data Status Login dari Data Preferences bernilai true
     */
     override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(baseContext)) {
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        }
    }

    private fun declareView() {

        txtRegister = findViewById(R.id.txt_login_register)
        txtMasuk = findViewById(R.id.txt_login_masuk)
        edtUsername = findViewById(R.id.edt_login_username)
        edtPassword = findViewById(R.id.edt_login_password)

    }

    private fun validasiLogin() {

        // Mereset semua Error dan fokus menjadi default
        edtUsername.error = null
        edtPassword.error = null
        var fokus: View? = null
        var cancel = false

        //Set Input Value dari View
        val userName = edtUsername.text.toString()
        val password = edtPassword.text.toString()


        // Jika form user kosong atau memenuhi kriteria di Method cekUser() maka, Set error di Form-
        // User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(userName)) {
            edtUsername.error = "Harus diisi"
            fokus = edtUsername
            cancel = true
        } else if (!cekUser(userName)) {
            edtUsername.error = "Username Tidak Ditemukan"
            fokus = edtUsername
            cancel = true
        }

        // Jika form password kosong dan memenuhi kriteria di Method cekPassword maka,
        // Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            edtPassword.error = "Harus Diisi"
            fokus = edtPassword
            cancel = true
        } else if (!cekPassword(password)) {
            edtPassword.error = "Data yang dimasukkan tidak sesuai"
            fokus = edtPassword
            cancel = true
        }

        // Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
        // Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel) {
            fokus!!.requestFocus()
        } else {
            // Deklarasi Model
            val userModel = UserModel()
            userModel.setUsername(userName)
            userModel.setPassword(password)
            // Simpan data ke shared preferences
            Preferences.setUserPreferences(baseContext, userModel)
            Preferences.setLoggedInStatus(baseContext, true)
            //Pindah Halaman ke home
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        }

    }

    // True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private fun cekUser(user: String): Boolean {
        return user == Preferences.getRegisteredUser(baseContext)
    }

    // True jika parameter password sama dengan parameter repassword */
    private fun cekPassword(password: String): Boolean {
        return password == Preferences.getRegisteredPassword(baseContext)
    }
}
