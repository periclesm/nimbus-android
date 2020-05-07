package net.cloudfields.nimbus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import kotlinx.android.synthetic.main.activity_main.*
import net.cloudfields.nimbus.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setActions()
    }

    private fun setActions() {
        proceedButton.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            this.proceedAction()
        }
    }

    private fun proceedAction() {
        startActivity(Intent(this@MainActivity, ListActivity::class.java))
        finish()
    }
}
