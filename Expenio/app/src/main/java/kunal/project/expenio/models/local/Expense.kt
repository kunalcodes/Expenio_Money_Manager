package kunal.project.expenio.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "desc")
    var desc: String,
    @ColumnInfo(name = "amount")
    var amount: Int,
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null
) {

}