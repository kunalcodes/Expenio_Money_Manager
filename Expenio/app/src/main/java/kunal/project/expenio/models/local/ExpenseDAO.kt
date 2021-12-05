package kunal.project.expenio.models.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kunal.project.expenio.models.local.Expense

@Dao
interface ExpenseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTasks(tasks: ArrayList<Expense>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(expense: Expense)

    @Query("select * from expenses")
    fun getTasks() : LiveData<List<Expense>>

    @Delete
    fun delete(task: Expense)

    @Update
    fun updateTask(task: Expense)

    @Query("delete from expenses")
    fun deleteAll()

}