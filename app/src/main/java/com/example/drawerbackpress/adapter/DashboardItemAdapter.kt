package com.adapter

import CategoryItems
import Contents
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drawerbackpress.R
import com.example.drawerbackpress.utils.EqualSpacingItemDecoration
import com.example.drawerbackpress.utils.StartSnapHelper
import kotlinx.android.synthetic.main.row_layout_home.view.*
import kotlin.collections.ArrayList

class DashboardItemAdapter(
    var context: Context
) :
    RecyclerView.Adapter<DashboardItemAdapter.DashboardItemHolder>(),
    DashboardChildAdapter.onChildClick {


    private var horizontalAdapter: DashboardChildAdapter? = null
    private var recycledViewPool: RecyclerView.RecycledViewPool?=null

    var dataItemList : MutableList<CategoryItems> = ArrayList()
    init {
        recycledViewPool = RecyclerView.RecycledViewPool()
        dataItemList = arrayListOf()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemHolder {
        val theView = LayoutInflater.from(context).inflate(R.layout.row_layout_home, parent, false)
        return DashboardItemHolder(theView)
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: DashboardItemHolder, position: Int) {
        holder.textViewMore.text = dataItemList.get(position).title
        horizontalAdapter = DashboardChildAdapter(context, dataItemList[position].contents, ""+dataItemList.get(position).type, this)

        if (dataItemList[position].type!!.contentEquals("audio")) {

        }

        holder.recyclerViewHorizontal.adapter = horizontalAdapter
        val snapHelperStart = StartSnapHelper()
        snapHelperStart.attachToRecyclerView(holder.recyclerViewHorizontal)
        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool)

        holder.textViewMore.setOnClickListener {

        }
    }
    fun add(categoryItems: CategoryItems){
        dataItemList.add(categoryItems)
        notifyItemInserted(dataItemList.size-1)
    }

    fun addAll(categoryItems: ArrayList<CategoryItems>){
        dataItemList.addAll(categoryItems)
        notifyDataSetChanged()
    }

    fun getCategoryItem(): ArrayList<CategoryItems>{
        return dataItemList as ArrayList<CategoryItems>
    }

    inner class DashboardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var recyclerViewHorizontal: RecyclerView = view.home_recycler_view_horizontal
        var textViewMore = view.text_category_more!!
        private val horizontalManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        init {
            recyclerViewHorizontal.setHasFixedSize(true)
            recyclerViewHorizontal.isNestedScrollingEnabled = false
            recyclerViewHorizontal.layoutManager = horizontalManager
            recyclerViewHorizontal.addItemDecoration(
                EqualSpacingItemDecoration(
                    20,
                    EqualSpacingItemDecoration.HORIZONTAL
                )
            )
            recyclerViewHorizontal.itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onAudioChildClickCallBack(position: Int, contents: Contents) {

    }

    override fun onVideoChildCallBack(position: Int, contents: Contents) {

    }
}