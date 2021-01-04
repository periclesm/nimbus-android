package net.cloudfields.nimbus.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import net.cloudfields.nimbus.R
import net.cloudfields.nimbus.model.dao.CloudListDAO
import net.cloudfields.nimbus.model.objects.Cloud

class DetailActivity : AppCompatActivity() {

    var cloudObject = CloudListDAO.selectedObject ?: Cloud()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        this.setupUI()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@DetailActivity, ListActivity::class.java))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.wikiButton) {
            Log.d("[Nimbus]", "Open Wikipedia link")

            val link = cloudObject.detail?.wiki
            if (!link.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun setupUI() {
        Picasso.get()
            .load(cloudObject.detail?.image)
            //.placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .fit().centerCrop()
            .into(cloudImage)

        cloudInitials.text = cloudObject.initials
        cloudName.text = cloudObject.name
        cloudType.text = cloudObject.type?.name ?: "No type specified"
        cloudDetails.text = cloudObject.detail?.detail ?: "Detail Text Missing"
    }
}