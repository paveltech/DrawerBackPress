package com.api



import com.example.drawerbackpress.model.MoviesResponse
import com.pojo.dashboard.DashboardItem
import com.pojo.sliderpojo.SliderItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiInterface {

    @GET("/api/category")
    fun getDashboardItems(@Header("Authorization") token: String , @Query("type") type :String,  @Query("page") page : Int):Call<DashboardItem>

    @GET("api/banner")
    fun getSlider(@Header("Authorization") token :String , @Query("type") type :String):Call<ArrayList<SliderItem>>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse?>?

}