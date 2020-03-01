package com.example.drawerbackpress.fragment

import CategoryItems
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adapter.DashboardItemAdapter
import com.api.ApiClient
import com.api.ApiInterface
import com.example.drawerbackpress.R
import com.example.drawerbackpress.adapter.SliderAdapter
import com.example.drawerbackpress.controller.BaseFragment
import com.example.drawerbackpress.utils.BEARER
import com.example.drawerbackpress.utils.StartSnapHelper
import com.example.drawerbackpress.utils.TOKEN
import com.github.islamkhsh.CardSliderViewPager
import com.pojo.dashboard.DashboardItem
import com.pojo.sliderpojo.SliderItem
import kotlinx.android.synthetic.main.test_dashbord.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongListFragment : BaseFragment() ,  SliderAdapter.onBannerCallback{

    var dataList: ArrayList<CategoryItems>? = null
    var layoutManager: LinearLayoutManager? = null
    var recyclerView: RecyclerView? = null
    var viewPagerLayout: CardSliderViewPager? = null
    var dashboardItemAdapter: DashboardItemAdapter? = null
    var apiInterface: ApiInterface?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.test_dashbord, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList = ArrayList()
        layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        recyclerView = view.main_recycleview
        viewPagerLayout = view.viewPager
        ViewCompat.setNestedScrollingEnabled(recyclerView!!, false)
        dashboardItemAdapter = DashboardItemAdapter(activity!!)
        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        recyclerView?.layoutManager = layoutManager
        val snapHelperStart = StartSnapHelper()
        snapHelperStart.attachToRecyclerView(recyclerView)
        recyclerView?.adapter = dashboardItemAdapter

        //apiCall()
        //getSliderApicall()

    }

    companion object {
        var ARG_TITLE = "title"
        fun newInstance(title: String): SongListFragment {
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            val fragment = SongListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun screenName(): String {
        return "pavel"
    }

    fun apiCall(){
        apiInterface?.getDashboardItems(BEARER+ TOKEN , "all" , 1)!!.enqueue(object: Callback<DashboardItem>{
            override fun onResponse(
                call: Call<DashboardItem>?,
                response: Response<DashboardItem>?
            ) {
                dashboardItemAdapter?.addAll(response!!.body().items)
                dashboardItemAdapter?.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<DashboardItem>?, t: Throwable?) {


            }
        })
    }


    fun getSliderApicall() {
        apiInterface?.getSlider(BEARER + TOKEN, "mobile")
            ?.enqueue(object : Callback<ArrayList<SliderItem>> {
                override fun onResponse(
                    call: Call<ArrayList<SliderItem>>?,
                    response: Response<ArrayList<SliderItem>>?
                ) {
                    setUpSlider(response!!.body())
                }

                override fun onFailure(call: Call<ArrayList<SliderItem>>?, t: Throwable?) {

                }

            })
    }

    private fun setUpSlider(list: ArrayList<SliderItem>) {
        viewPagerLayout?.adapter = SliderAdapter(context!!, list, this)
    }

    override fun onBannerClick(item: SliderItem) {
        pushDetailFragment(DetailsFragment())
    }

    fun pushDetailFragment(fragment: Fragment?) {
        val transitions: List<Pair<View, String>> =
            java.util.ArrayList()
        navigationController!!.pushViewController(fragment!!, "DetailFragment", transitions)
    }

}