package com.example.madlevel2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madlevel2example.adapters.ReminderAdapter
import com.example.madlevel2example.classes.Reminder
import com.example.madlevel2example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    // Don't forget to create a binding object as you did in previous assignments.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        TODO("Not yet implemented")
    }

}