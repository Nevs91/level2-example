package com.example.madlevel2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2example.adapters.ReminderAdapter
import com.example.madlevel2example.classes.Reminder
import com.example.madlevel2example.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        // Create an onClickListener associated with the button
        binding.btnAddReminder.setOnClickListener {
            val reminder = binding.etReminder.text.toString()
            addReminder(reminder)
        }
    }

    /**
     * Method for setting up the views for the application
     */
    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvReminders.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvReminders.adapter = reminderAdapter
        binding.rvReminders.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        createItemTouchHelper().attachToRecyclerView(binding.rvReminders)
    }

    /**
     * Method for adding reminders
     */
    private fun addReminder(reminder: String) {
        if (reminder.isNotBlank()) {
            reminders.add(Reminder(reminder))
            reminderAdapter.notifyDataSetChanged()
            binding.etReminder.text?.clear()
            Snackbar.make(binding.btnAddReminder, R.string.reminder_added, Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.etReminder, R.string.reminder_validation_message, Snackbar.LENGTH_SHORT).show()
        }
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()
                Snackbar.make(binding.btnAddReminder, R.string.reminder_removed, Snackbar.LENGTH_SHORT).show()
            }
        }

        return ItemTouchHelper(callback)
    }
}