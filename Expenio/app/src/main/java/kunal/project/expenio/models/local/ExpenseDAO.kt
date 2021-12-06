package kunal.project.expenio.models.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kunal.project.expenio.models.local.Expense

@Dao
interface ExpenseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTransactionsToDB(expenseList: ArrayList<Expense>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTransactionToDB(expense: Expense)

    @Query("select * from expenses")
    fun getAllTransactionsFromDB() : LiveData<List<Expense>>

    @Delete
    fun deleteTransactionFromDB(expense: Expense)

    @Update
    fun updateTransactionInDB(task: Expense)

    @Query("delete from expenses")
    fun deleteAllTransactionsFromDB()

}