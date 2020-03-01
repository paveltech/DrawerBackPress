package com.example.drawerbackpress.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.example.drawerbackpress.R
import com.example.drawerbackpress.utils.IMAGE_URL
import com.github.islamkhsh.CardSliderAdapter
import com.pojo.sliderpojo.SliderItem
import kotlinx.android.synthetic.main.item_home_banner.view.*

class SliderAdapter(var context: Context, items: ArrayList<SliderItem> , var onBanner: onBannerCallback) :
    CardSliderAdapter<SliderItem>(items) {

    override fun getItemContentLayout(position: Int) = R.layout.item_home_banner

    override fun bindView(position: Int, itemContentView: View, item: SliderItem?) {
        item.run {
            Glide.with(context).load(IMAGE_URL + this?.path).into(itemContentView.movie_poster)

            //Glide.with(context).load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg").into(itemContentView.movie_poster)

        }
        itemContentView.setOnClickListener {
            onBanner.onBannerClick(item!!)
        }
    }

    interface onBannerCallback{
        fun onBannerClick(item: SliderItem)
    }
}