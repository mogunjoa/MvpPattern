package kr.co.bonjin.naverapimvp.model.api

import kr.co.bonjin.naverapimvp.model.vo.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface Api {
    @GET("movie.json")
    @Headers("X-Naver-Client-Id: eSOKMo1mqeadOBy2zlnu", "X-Naver-Client-Secret: VuYF9_G_Sp")
    fun getMovies(
        @Query("query") title: String?,
        @Query("display") displaySize: Int,
        @Query("start") startPosition: Int
    ): Call<Movie?>?
}