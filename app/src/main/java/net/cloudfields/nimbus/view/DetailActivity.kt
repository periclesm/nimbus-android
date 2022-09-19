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

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        this.setupUI()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@DetailActivity, ListActivity::class.java))
        finish()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_detail, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.wikiButton) {
            Log.d("[Nimbus]", "Open Wikipedia link")

            val link = CloudVM.shared.selectedCloud?.detail?.wiki
            if (!link.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun setupUI() {
        Picasso.get()
            .load(CloudVM.shared.selectedCloud?.detail?.image)
            //.placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .fit().centerCrop()
            .into(cloudImage)

        cloudInitials.text = CloudVM.shared.selectedCloud?.initials
        cloudName.text = CloudVM.shared.selectedCloud?.name
        cloudType.text = CloudVM.shared.selectedCloud?.type?.name ?: "No type specified"
        cloudDetails.text = CloudVM.shared.selectedCloud?.detail?.detail ?: "Detail Text Missing"
    }
}