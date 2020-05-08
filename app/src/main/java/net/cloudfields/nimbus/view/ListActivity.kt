package net.cloudfields.nimbus.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.*
import net.cloudfields.nimbus.R
import net.cloudfields.nimbus.model.dao.CloudListDAO
import net.cloudfields.nimbus.model.entity.CloudListEntity

class ListActivity : AppCompatActivity () {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CloudListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        linearLayoutManager = LinearLayoutManager(this)
        cloudListRecycler.layoutManager = linearLayoutManager
        adapter = CloudListAdapter()
        cloudListRecycler.adapter = adapter

    }

    override fun onBackPressed() {
        startActivity(Intent(this@ListActivity, MainActivity::class.java))
        finish()
    }
}

class CloudListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    override fun getItemCount(): Int {
        return CloudListDAO.listData.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cell = LayoutInflater.from(parent.context).inflate(R.layout.listitem_cloudlist, null)
        return cellView(cell)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cloud: CloudListEntity = CloudListDAO.listData[position]
        val cell = holder as cellView

        //cell.cloudImage = image
        cell.cloudName.text = cloud.name
        cell.cloudDetail.text = cloud.initials
    }
}

class cellView(view: View): RecyclerView.ViewHolder(view) {
    var cloudImage = view.findViewById<ImageView>(R.id.cloudImage)
    var cloudName = view.findViewById<TextView>(R.id.cloudName)
    var cloudDetail = view.findViewById<TextView>(R.id.cloudDetail)
}