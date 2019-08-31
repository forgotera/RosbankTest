package ru.skillbranch.rosbanktest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import ru.skillbranch.rosbanktest.view.RecyclerFragment

class MainActivity : AppCompatActivity() {

    companion object{
        const val IS_ADDED_FRAGMENT = "IS ADDED FRAGMENT"
        const val INPUT_EDIT_TEXT = "INPUT_EDIT_TEXT"
    }

    var isAddedFragment = false
    var editTextString:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editPrice = findViewById<EditText>(R.id.editPrice)
        isAddedFragment = savedInstanceState?.getBoolean(IS_ADDED_FRAGMENT) ?: false

        val fm = supportFragmentManager
        val fragment = RecyclerFragment()
        //если фрагмент еще не добавлен
        if(!isAddedFragment) {
            fm.beginTransaction().add(R.id.fragmentСontainer, fragment).commit()
            isAddedFragment = true
        }else{
            fm.beginTransaction().replace(R.id.fragmentСontainer, fragment).commit()
        }

        //ввод данных
        editPrice.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.isNotEmpty()) {
                    editTextString = savedInstanceState?.getString(INPUT_EDIT_TEXT) ?: p0.toString()
                    fragment.clickEditText(editTextString!!)
                }
            }

        })

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d("crashactivity","onSaveInstanceState")
        outState?.putBoolean(IS_ADDED_FRAGMENT,isAddedFragment)
        outState?.putString(INPUT_EDIT_TEXT,editTextString)
    }

}
