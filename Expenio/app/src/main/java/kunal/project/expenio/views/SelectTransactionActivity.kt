package kunal.project.expenio.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_select_transaction.*
import kunal.project.expenio.R

class SelectTransactionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_transaction)
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        cvSelectTransactionIncome.setOnClickListener {
            val intent = Intent(this@SelectTransactionActivity, AddIncomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        cvSelectTransactionExpense.setOnClickListener {
            val intent = Intent(this@SelectTransactionActivity, AddExpenseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}