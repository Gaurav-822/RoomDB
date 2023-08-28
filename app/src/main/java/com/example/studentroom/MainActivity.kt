package com.example.studentroom

import android.app.appsearch.GlobalSearchSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database: StudentDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext,
        StudentDatabase::class.java,
        "studentDB").build()

        GlobalScope.launch {
            database.studentDao().insertStudent(Student(0, "Gaurav", 18))
        }
        //Accessing the live data in the logCat
        database.studentDao().getAllStudent().observe(this) {
            Log.d("ROOMDB", "onCreate: $it")
        }
    }
}