package kunal.project.expenio.di

import android.content.Context
import androidx.room.Room
import kunal.project.expenio.models.remote.MoneyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kunal.project.expenio.models.local.ExpenseDAO
import kunal.project.expenio.models.local.MoneyRoomDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoneyModule {

    @Singleton
    @Provides
    fun provideAPIService(): MoneyAPI {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = Retrofit.Builder()
            .baseUrl("http://13.232.169.202:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
            .build()
        return builder.create(MoneyAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context):MoneyRoomDatabase{
        val builder = Room.databaseBuilder(
            context,
            MoneyRoomDatabase::class.java,
            "money_db"
        )

        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideTaskDAO(db: MoneyRoomDatabase): ExpenseDAO{
        return db.getExpenseDAO()
    }


}