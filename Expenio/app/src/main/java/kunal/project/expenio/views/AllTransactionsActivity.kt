package kunal.project.expenio.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_all_transactions.*
import kotlinx.android.synthetic.main.activity_main.*
import kunal.project.expenio.R
import kunal.project.expenio.models.local.Expense
import kunal.project.expenio.utils.ItemClickListener
import kunal.project.expenio.utils.MoneyAllAdapter
import kunal.project.expenio.utils.MoneyHomeAdapter
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class AllTransactionsActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var adapter: MoneyAllAdapter
    private var list = ArrayList<Expense>()
    private lateinit var token: String
    private val viewModel: MoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_transactions)
        token = PreferenceHelper.getStringFromPreference(this@AllTransactionsActivity, "token").toString()
        setAdapter()
        viewModel.getAllTransactions().observe(this, Observer {
            list.clear()
            it.forEach {
                list.add(it)
            }
            list.reverse()
            adapter.notifyDataSetChanged()
        })
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        btnAllTransactionsAdd.setOnClickListener {
            val intent = Intent(this@AllTransactionsActivity, SelectTransactionActivity::class.java)
            startActivity(intent)
        }
        tvAllTransactionsBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdapter() {
        adapter = MoneyAllAdapter(list, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewAllTransactions.adapter = adapter
        recyclerViewAllTransactions.layoutManager = layoutManager
    }

    override fun onItemClicked(expense: Expense) {
        val intent = Intent(this@AllTransactionsActivity, SingleTransactionActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("expense", expense)
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    }

    override fun onItemLonClicked(expense: Expense) {
        viewModel.deleteTransaction(token, expense)
    }


}