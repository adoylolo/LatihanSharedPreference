package com.muhammadfarhaan.latihanshared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView

import com.muhammadfarhaan.latihanshared.Utils.Preferences
import com.muhammadfarhaan.latihanshared.Model.UserModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtMasuk : TextView
    private lateinit var edtUserName : EditText
    private lateinit var edtPassWord : EditText
    private lateinit var edtRePassWord : EditText
    private lateinit var edtPhoneNumber : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        declareView()
        txtMasuk.setOnClickListener { validasiRegister() }
    }

    private fun declareView() {
        txtMasuk = findViewById(R.id.txt_reg_masuk)
        edtUserName = findViewById(R.id.edt_reg_username)
        edtPassWord = findViewById(R.id.edt_reg_password)
        edtRePassWord = findViewById(R.id.edt_reg_password_confirm)
        edtPhoneNumber = findViewById(R.id.edt_reg_phone)
    }

    private fun validasiRegister() {

        // Mereset semua Error dan fokus menjadi default
        edtUserName.setError(null)
        edtPassWord.setError(null)
        edtRePassWord.setError(null)
        var fokus: View? = null
        var cancel = false

        //Set Input Value dari View
        val userName = edtUserName.getText().toString()
        val password = edtPassWord.getText().toString()
        val rePassword = edtRePassWord.getText().toString()
        val phoneNumber = edtPhoneNumber.getText().toString()


        // Jika form user kosong atau memenuhi kriteria di Method cekUser() maka, Set error di Form-
        // User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(userName)) {
            edtUserName.setError("Harus diisi")
            fokus = edtUserName
            cancel = true
        } else if (cekUser(userName)) {
            edtUserName.setError("Username sudah terdaftar")
            fokus = edtUserName
            cancel = true
        }

        // Jika form password kosong dan memenuhi kriteria di Method cekPassword maka,
        // Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            edtPassWord.setError("Harus Diisi")
            fokus = edtPassWord
            cancel = true
        } else if (!cekPassword(password, rePassword)) {
            edtPassWord.setError("Password yang dimasukkan tidak sesuai")
            fokus = edtPassWord
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
            userModel.setPhone(phoneNumber)
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
    private fun cekPassword(password: String, repassword: String): Boolean {
        return password == repassword
    }
}
