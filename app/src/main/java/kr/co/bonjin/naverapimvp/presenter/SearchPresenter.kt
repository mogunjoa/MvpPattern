package kr.co.bonjin.naverapimvp.presenter

import android.content.Context
import android.widget.Toast
import kr.co.bonjin.naverapimvp.common.SearchContract
import kr.co.bonjin.naverapimvp.model.adapter.MovieAdapter
import kr.co.bonjin.naverapimvp.model.api.Api
import kr.co.bonjin.naverapimvp.model.api.ApiService
import kr.co.bonjin.naverapimvp.model.repository.MovieRepository
import kr.co.bonjin.naverapimvp.model.vo.Item
import kr.co.bonjin.naverapimvp.model.vo.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter : SearchContract.Presenter {
    private var searchView: SearchContract.View? = null
    private var movieRepository = MovieRepository()
    var movieList = ArrayList<Item>()

    override fun takeView(view: SearchContract.View) {
        searchView = view
    }

    override fun getMovieList(title: String, context: Context) {
        if (title.isEmpty()) {
            Toast.makeText(context, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        } else {
            movieRepository.getMovieList(
                title = title,
                success = {
                    movieList.addAll(it.items)
                },
                failure = {
                    Toast.makeText(context, "서버에서 데이터를 불러오지 못하였다 이노옴!", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    override fun dropView() {
        searchView = null
    }

}