package com.thoriq.kasirku.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.Spinner
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoriq.kasirku.R
import com.thoriq.kasirku.cls.cekclass
import com.thoriq.kasirku.cls.dialogKasirku
import com.thoriq.kasirku.database.akun.Akun
import com.thoriq.kasirku.databinding.ActivityRegisterBinding
import com.thoriq.kasirku.stock.StockFragmentAdapter
import com.thoriq.kasirku.stock.StockFragmentViewModel

class RegisterActivity : AppCompatActivity(),RegisterActivityAdapter.RowOnClickListener, AdapterView.OnItemSelectedListener   {
    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterActivityViewmodel
    lateinit var recyclerViewAdapter: RegisterActivityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dialog = dialogKasirku(this)
        binding.akunList.apply {
            layoutManager = LinearLayoutManager(application)
            recyclerViewAdapter = RegisterActivityAdapter(this@RegisterActivity )
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(application, GridLayout.VERTICAL)
            addItemDecoration(divider)
        }
        viewModel =
            ViewModelProvider(
                this).get(RegisterActivityViewmodel::class.java)
        viewModel.getAllAkunObservers().observe(this, Observer{
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.getAllAkun()
        val spinner  = binding.tugasSpinner
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.pilihanTugas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        val cc = cekclass()
        binding.submit.setOnClickListener{
            val username = binding.tambahUsername.text.toString()
            val password = binding.tambahPassword.text.toString()
            if (username.isEmpty() or password.isEmpty()) {
                dialog.dialogWarning("username atau password tidak boleh kosong")
            } else{
                if (cc.mengandungSimbolRegister(username)){
                    dialog.dialogWarning("username tidak boleh mengandung simbol")
                }else if (password.length<6) {
                    dialog.dialogWarning("password minimal 6 digit")
                }else {
                    if (viewModel.getListAkun(username) == null){
                        spinner.onItemSelectedListener = this
                        viewModel.insertakun(Akun(username = username, password = password, tugas = spinner.selectedItem.toString()))
                        binding.tambahUsername.text = null
                        binding.tambahPassword.text = null
                    }else{
                        dialog.dialogWarning("akun telah tersedia")
                    }
                }
            }
        }
    }

    override fun onDeleteUserClickListener(akun: Akun) {
        viewModel.deleteAkunlistakun(akun)
    }

    override fun onItemClickListener(akun: Akun) {

    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}