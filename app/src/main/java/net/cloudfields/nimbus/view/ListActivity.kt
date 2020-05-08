package net.cloudfields.nimbus.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.listitem_cloudlist.view.*
import net.cloudfields.nimbus.R
import net.cloudfields.nimbus.model.dao.CloudListDAO
import net.cloudfields.nimbus.model.entity.CloudDetailEntity
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
        //val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.listitem_cloudlist, null, false)
        val inflatedView = parent.inflate(R.layout.listitem_cloudlist, false)
        return CellView(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cloud: CloudListEntity = CloudListDAO.listData[position]
        val cell = holder as CellView
        cell.bindData(cloud)
    }
}

//extension
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

class CellView(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
    private var cellView: View = view

    init {
        view.setOnClickListener(this)
    }

    fun bindData(cloud: CloudListEntity) {
        //cell.cloudImage = image
        cellView.cloudName.text = cloud.name

        val details = cloud.detail as CloudDetailEntity
        cellView.cloudDetail.text = details.detail ?: ""
    }

    override fun onClick(v: View) {
//        val context = itemView.context
//        val showPhotoIntent = Intent(context, PhotoActivity::class.java)
//        showPhotoIntent.putExtra(PHOTO_KEY, photo)
//        context.startActivity(showPhotoIntent)
    }
}