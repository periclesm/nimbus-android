package net.cloudfields.nimbus.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.cloudfields.nimbus.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@DetailActivity, ListActivity::class.java))
        finish()
    }
}