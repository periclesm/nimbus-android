package net.cloudfields.nimbus.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import net.cloudfields.nimbus.R
import net.cloudfields.nimbus.model.dao.CloudListDAO
import net.cloudfields.nimbus.model.entity.CloudListEntity

class DetailActivity : AppCompatActivity() {

    var cloudObject = CloudListDAO.selectedObject ?: CloudListEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        this.setupUI()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@DetailActivity, ListActivity::class.java))
        finish()
    }

    fun setupUI() {
        Picasso.get()
            .load(cloudObject.detail?.image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .fit().centerCrop()
            .into(cloudImage)

        cloudInitials.text = cloudObject.initials
        cloudName.text = cloudObject.name
        cloudType.text = cloudObject.type?.name ?: "No type specified"
        cloudDetails.text = cloudObject.detail?.detail ?: "Detail Text Missing"
    }
}