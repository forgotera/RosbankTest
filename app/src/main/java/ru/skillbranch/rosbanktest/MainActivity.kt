package ru.skillbranch.rosbanktest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.skillbranch.rosbanktest.view.RecyclerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fm = supportFragmentManager
        val fragment = RecyclerFragment()
        fm.beginTransaction().add(R.id.fragmentСontainer,fragment).commit()
    }
}
