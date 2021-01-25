package kr.co.bonjin.naverapimvp.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
    lateinit var adapter: MovieAdapter
    private var apiService = ApiService()


    //    var list = MutableLiveData<List<Item>>()
    var movieList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        adapter = MovieAdapter(this, movieList)
        binding.rvMovieList.adapter = adapter
        binding.btSearch.setOnClickListener(this)
    }

    private fun setUI() {}

    private fun setUpRecyclerView() {}

    private fun startSearch(title: String) {
        if (title.isEmpty()) {
            Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            getMovieList(title, this)
        }
    }

    private fun getMovieList(title: String, context: Context) {
        apiService
            .createService(Api::class.java)
            .getMovies(title, 100, 1)
            ?.enqueue(object : Callback<Movie?> {
                override fun onFailure(call: Call<Movie?>, t: Throwable) {
                    Toast.makeText(context, "서버통신에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                    if (response.body()?.items.isNullOrEmpty()) {
                        Toast.makeText(context, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
                        return
                    }
                    response.body()?.items?.let {
                        movieList.addAll(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            })
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btSearch -> {
                movieList.clear()
                startSearch(binding.etSearch.text.toString())
            }
        }
    }

}