package kunal.project.expenio.models.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1)
abstract class MoneyRoomDatabase : RoomDatabase() {

    abstract fun getExpenseDAO(): ExpenseDAO

    companion object{

        private var INSTANCE : MoneyRoomDatabase? = null

        fun getDatabaseObject(context: Context): MoneyRoomDatabase {
            if (INSTANCE == null){
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyRoomDatabase::class.java,
                    "money_db"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }
    }
}