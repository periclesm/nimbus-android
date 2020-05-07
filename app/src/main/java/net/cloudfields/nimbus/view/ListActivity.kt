package net.cloudfields.nimbus.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.cloudfields.nimbus.R

class ListActivity : AppCompatActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}