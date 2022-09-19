package net.cloudfields.nimbus.view

import android.content.Intent
import android.os.Bundle
import android.view.HapticFeedbackConstants
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import net.cloudfields.nimbus.R
import net.cloudfields.nimbus.model.DataManager
import net.cloudfields.nimbus.model.realmmanager.RealmDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setActions()

        RealmDatabase.shared.initDatabase()

        DataManager.prefetchData { completed ->
            if (completed) {
                runOnUiThread {
                    proceedButton.isEnabled = true
                }
            }
        }
    }

    private fun setActions() {
        proceedButton.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            this.proceedAction()
        }
    }

    private fun proceedAction() {
        val listIntent = Intent(this@MainActivity, ListActivity::class.java)
        startActivity(listIntent)
        finish()
    }
}
