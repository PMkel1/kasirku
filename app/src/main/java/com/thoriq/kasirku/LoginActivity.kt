package com.thoriq.kasirku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.thoriq.kasirku.cls.dialogKasirku
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.akun.Akun
import com.thoriq.kasirku.database.akun.AkunDao
import com.thoriq.kasirku.databinding.ActivityLoginBinding
import com.thoriq.kasirku.cls.cekclass


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val dialog = dialogKasirku(this)
        var dataSource = ListDatabase.getInstance(this).AkunDAO
        addAdmin(dataSource)
        val cc = cekclass()
        binding.loginButton.setOnClickListener {
            var username = binding.usernameEditText.text.toString()
            var password = binding.passwordEditText.text.toString()
            if (username.isEmpty() or password.isEmpty()) {
                dialog.dialogWarning("username atau password tidak boleh kosong")
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
                        dialog.dialogWarning("akun tidak ada")
                    }

                } else {
                    dialog.dialogWarning("username atau password salah")
                }
            }
        }
    }
}
fun cekAkun(username: String, password: String, akun: Akun?): Boolean {
    return username.equals(akun?.username) and password.equals(akun?.password)
}

fun addAdmin(dataSource: AkunDao) {
    if (dataSource.getByUsername("admin") == null){
        var akun = Akun(username =  "admin", password = "admin",tugas =  "admin")
        dataSource.insert(akun)
    }else{}
}
