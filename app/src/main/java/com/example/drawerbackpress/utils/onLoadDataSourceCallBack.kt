package com.example.drawerbackpress.utils

import Contents

interface onLoadDataSourceCallBack {
    fun onAdapterItemClick(contents: Contents)
    fun onChilddataLoad(childDataList: ArrayList<Contents>)

}