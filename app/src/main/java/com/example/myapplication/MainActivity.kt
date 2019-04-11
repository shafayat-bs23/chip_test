package com.example.myapplication

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var items = mutableListOf<String>("a", "b")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputField.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items))
        inputField.setOnItemClickListener { parent, view, position, id ->
            val chip = Chip(this@MainActivity)
            chip.isCloseIconEnabled = true
            chip.setOnCloseIconClickListener { chipGroup.removeView(chip as View) }
            chip.isClickable = true
            chip.isCheckable = false
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))
            chip.text = (view as AppCompatTextView).text
            chipGroup.addView(chip)
        }

        inputField.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                items.add(s.toString())
                inputField.setAdapter(ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_dropdown_item_1line, items))
                (inputField.adapter as ArrayAdapter<String>).notifyDataSetChanged()
            }

        })


    }
}
