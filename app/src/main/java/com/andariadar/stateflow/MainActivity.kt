package com.andariadar.stateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.andariadar.stateflow.databinding.ActivityMainBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var days: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.daysSpinner.onItemSelectedListener = this

        createSpinner()
        setObserver()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        days = resources.getStringArray(R.array.days_array)
        viewModel.setDay(days[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun createSpinner() {
        ArrayAdapter.createFromResource(
                this,
                R.array.days_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.daysSpinner.adapter = adapter
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.dayState.collect { day ->
                binding.dayOfWeek.text = day
            }
        }
    }
}
