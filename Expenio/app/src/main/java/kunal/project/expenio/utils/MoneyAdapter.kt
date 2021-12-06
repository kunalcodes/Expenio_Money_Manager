package kunal.project.expenio.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kunal.project.expenio.R
import kunal.project.expenio.models.local.Expense

class MoneyAdapter(val list : ArrayList<Expense>) : RecyclerView.Adapter<MoneyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MoneyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        val expense = list[position]
        holder.setData(expense)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}