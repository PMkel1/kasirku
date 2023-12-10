package com.thoriq.kasirku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.akun.Akun
import com.thoriq.kasirku.database.akun.AkunDao
import com.thoriq.kasirku.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var dataSource = ListDatabase.getInstance(this).AkunDAO
        addAdmin(dataSource,this)

        binding.loginButton.setOnClickListener {
            var username = binding.usernameEditText.text.toString()
            var password = binding.passwordEditText.text.toString()
            if (isEmpty(username, password)) {
                Toast.makeText(
                    this,
                    "username atau passsord tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var akun = dataSource.getByUsername(username)
                var tugas = akun?.tugas
                if (cekAkun(username, password, akun)) {
                    if (tugas.equals("admin")) {
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else if(tugas.equals("kasir")){
                        val intent = Intent(this, KasirActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else if(tugas.equals("stock")) {
                        val intent = Intent(this, StockActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    Toast.makeText(this, "username atau passsord salah", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
fun cekAkun(username: String, password: String, akun: Akun?): Boolean {
    return username.equals(akun?.username) and password.equals(akun?.password)
}

fun isEmpty(username: String, password: String): Boolean {
    return username.equals("") or password.equals("")
}

fun addAdmin(dataSource: AkunDao,context: Context) {
    if (dataSource.getByUsername("admin") == null){
        var akun = Akun(username =  "admin", password = "admin",tugas =  "admin")
        dataSource.insert(akun)
    }else{}
}
