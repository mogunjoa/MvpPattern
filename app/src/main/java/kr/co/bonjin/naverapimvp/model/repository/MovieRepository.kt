package kr.co.bonjin.naverapimvp.model.repository

import android.util.Log
import kr.co.bonjin.naverapimvp.model.api.Api
import kr.co.bonjin.naverapimvp.model.api.ApiService
import kr.co.bonjin.naverapimvp.model.vo.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    fun getMovieList(
        title: String,
        success: (Movie) -> Unit,
        failure: (Throwable) -> Unit){

        ApiService
            .createService(Api::class.java)
            .getMovies(title, 100, 1)
            ?.enqueue(object : Callback<Movie?> {
                override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                    Log.d("movie 통신", "성공해부렀어야")
                    val data = response.body() ?: return
                    success(data)
                }
                override fun onFailure(call: Call<Movie?>, t: Throwable) {
                    Log.d("movie 통신", "실패해부렀어야")
                    failure(t)
                }
            })
    }


}