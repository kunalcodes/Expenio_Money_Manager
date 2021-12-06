package kunal.project.expenio.views

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kotlinx.android.synthetic.main.activity_profile.*
import kunal.project.expenio.R
import kunal.project.expenio.models.local.Expense
import kunal.project.expenio.utils.MoneyHomeAdapter
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var userName : String
    private lateinit var token : String
    private lateinit var adapter : MoneyHomeAdapter
    private var list = ArrayList<Expense>()
    private val viewModel : MoneyViewModel by viewModels()
    private var totalIncome = 0
    private var totalExpense = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        token = PreferenceHelper.getStringFromPreference(this@MainActivity, "token").toString()
        userName = PreferenceHelper.getStringFromPreference(this@MainActivity, "email").toString()
        checkLogin()
        checkNewLogin()
        setAdapter()
        viewModel.getAllTransactions().observe(this, Observer {
            list.clear()
            if (it.size <=10){
                it.forEach {
                    list.add(it)
                }
            } else {
                for (i in (it.size)-10 until it.size){
                    list.add(it[i])
                }
            }
            list.reverse()
            adapter.notifyDataSetChanged()
        })
        viewModel.getTotalIncome().observe(this, Observer {
            it?.let {
                totalIncome = it
                val balance = totalIncome - totalExpense
                tvHomeTotalBalance.text = "₹$balance"
            }
        })
        viewModel.getTotalExpenses().observe(this, Observer {
            it?.let {
                totalExpense = it
                val balance = totalIncome - totalExpense
                tvHomeTotalBalance.text = "₹$balance"
            }
        })
        setClicklistenersOnViews()
    }

    override fun onResume() {
        super.onResume()
        checkLogin()
    }

    private fun checkLogin() {
        token = PreferenceHelper.getStringFromPreference(this@MainActivity, "token").toString()
        if (token == ""){
            finish()
        }
    }

    private fun checkNewLogin() {
        val isNewLogin = PreferenceHelper.getIntFromPreference(this@MainActivity,"newlogin")
        if (isNewLogin==1){
            viewModel.refreshTransactionsFromAPI(token)
        }
        PreferenceHelper.writeIntToPreference(this@MainActivity, "newlogin", 0)
    }

    private fun setAdapter() {
        adapter = MoneyHomeAdapter(list)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewHome.adapter = adapter
        recyclerViewHome.layoutManager = layoutManager
    }

    private fun setClicklistenersOnViews() {
        tvHomeUser.text = userName
        btnHomeAddTransaction.setOnClickListener {
            val intent = Intent(this@MainActivity, SelectTransactionActivity::class.java)
            startActivity(intent)
        }
        btnHomeMyBalance.setOnClickListener {
            val intent = Intent(this@MainActivity, AllTransactionsActivity::class.java)
            startActivity(intent)
        }
        tvHomeSeeAll.setOnClickListener {
            val intent = Intent(this@MainActivity, AllTransactionsActivity::class.java)
            startActivity(intent)
        }
        ivHomeUserImage.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}