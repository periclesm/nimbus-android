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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.listitem_cloudlist.view.*
import net.cloudfields.nimbus.R
import net.cloudfields.nimbus.model.realmobjects.CloudDetail
import net.cloudfields.nimbus.model.realmobjects.Cloud

class ListActivity : AppCompatActivity () {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CloudListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        CloudVM.shared.getData()

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
    override fun getItemCount() = CloudVM.shared.cloudData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = parent.inflate(R.layout.listitem_cloudlist, false)
        return ListItemView(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cloud: Cloud = CloudVM.shared.cloudData[position]
        val cell = holder as ListItemView
        cell.bindData(cloud)
    }
}

//extension
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

class ListItemView(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
    private var cellView: View = view
    private var selectedCloudObject: Cloud? = null

    init {
        view.setOnClickListener(this)
    }

    fun bindData(cloud: Cloud) {
        this.selectedCloudObject = cloud

        cellView.cloudName.text = cloud.name

        val cloudObject: CloudDetail? = cloud.detail
        if (cloudObject != null) {
            cellView.cloudDetail.text = cloudObject.detail ?: ""
            Picasso.get()
                .load(cloudObject.image)
                //.placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fit().centerCrop()
                .into(cellView.cloudImage)
        }
    }

    override fun onClick(v: View) {
        CloudVM.shared.selectedCloud = this.selectedCloudObject
        val context = itemView.context
        val detailIntent = Intent(context, DetailActivity::class.java)
        context.startActivity(detailIntent)
    }
}
