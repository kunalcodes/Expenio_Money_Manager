package kunal.project.expenio.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kunal.project.expenio.R
import kunal.project.expenio.models.local.Expense
import kunal.project.expenio.utils.MoneyAdapter
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var userName : String
    private lateinit var token : String
    private lateinit var adapter : MoneyAdapter
    private var list = ArrayList<Expense>()
    private val viewModel : MoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        token = PreferenceHelper.getStringFromPreference(this@MainActivity, "token").toString()
        userName = PreferenceHelper.getStringFromPreference(this@MainActivity, "email").toString()
        checkNewLogin()
        setAdapter()
        viewModel.getAllTransactions().observe(this, Observer {
            list.clear()
            it.forEach {
                list.add(it)
            }
            adapter.notifyDataSetChanged()
        })
        setClicklistenersOnViews()
    }

    private fun checkNewLogin() {
        val isNewLogin = PreferenceHelper.getIntFromPreference(this@MainActivity,"newlogin")
        if (isNewLogin==1){
            viewModel.refreshTransactionsFromAPI(token)
        }
        PreferenceHelper.writeIntToPreference(this@MainActivity, "newlogin", 0)
    }

    private fun setAdapter() {
        adapter = MoneyAdapter(list)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewHome.adapter = adapter
        recyclerViewHome.layoutManager = layoutManager
    }

    private fun setClicklistenersOnViews() {
        tvHomeUser.text = userName
        tvHomeTotalBalance.text = "₹25,000"
        btnHomeAddTransaction.setOnClickListener {
            val intent = Intent(this@MainActivity, SelectTransactionActivity::class.java)
            startActivity(intent)
        }
    }
}