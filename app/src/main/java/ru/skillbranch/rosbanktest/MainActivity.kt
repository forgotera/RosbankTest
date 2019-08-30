package ru.skillbranch.rosbanktest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import ru.skillbranch.rosbanktest.view.RecyclerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editPrice = findViewById<EditText>(R.id.editPrice)
        val fm = supportFragmentManager
        val fragment = RecyclerFragment()
        fm.beginTransaction().add(R.id.fragmentСontainer,fragment).commit()

        editPrice.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                fragment.cliclEditText(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

    }
}
