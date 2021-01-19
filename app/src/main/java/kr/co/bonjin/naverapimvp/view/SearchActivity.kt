package kr.co.bonjin.naverapimvp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import kr.co.bonjin.naverapimvp.R
import kr.co.bonjin.naverapimvp.common.Const
import kr.co.bonjin.naverapimvp.databinding.ActivitySearchBinding
import kr.co.bonjin.naverapimvp.model.adapter.MovieAdapter
import kr.co.bonjin.naverapimvp.model.api.Api
import kr.co.bonjin.naverapimvp.model.api.ApiService
import kr.co.bonjin.naverapimvp.model.vo.Item
import kr.co.bonjin.naverapimvp.model.vo.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySearchBinding
    lateinit var adapter : MovieAdapter
    var list = MutableLiveData<List<Item>>()
    var movieList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        adapter = MovieAdapter(this)
        binding.rvMovieList.adapter = adapter
        binding.btSearch.setOnClickListener(this)
    }

    private fun setUI(){
        binding.rvMovieList

    }

    private fun setUpRecyclerView(){

    }

    fun startSearch(title: String) {
        if (title.isEmpty()) {
            Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            getMovieList(title)
        }
    }

    private fun getMovieList(title: String){
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)
        val callGetSearchMovie = api.getMovies(title, 100, 1)

        callGetSearchMovie?.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                Log.d("통신", "아쥬 성공적이구만?")
                println("응답데이터:"+response.body()?.items)

                list.value = response.body()?.items
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                Log.d("통신", "죶되버렸고만?")
            }
        })



    }

    override fun onClick(v: View?) {
        when(v){
            binding.btSearch -> {
                getMovieList(binding.etSearch.text.toString())
            }

        }
    }
}