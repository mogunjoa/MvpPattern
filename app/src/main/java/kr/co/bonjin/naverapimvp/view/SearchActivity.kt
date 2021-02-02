package kr.co.bonjin.naverapimvp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.bonjin.naverapimvp.R
import kr.co.bonjin.naverapimvp.contract.SearchContract
import kr.co.bonjin.naverapimvp.databinding.ActivitySearchBinding
import kr.co.bonjin.naverapimvp.view.adapter.MovieAdapter
import kr.co.bonjin.naverapimvp.model.vo.Item
import kr.co.bonjin.naverapimvp.presenter.SearchPresenter

class SearchActivity : AppCompatActivity(), SearchContract.View, View.OnClickListener {
    private lateinit var searchPresenter: SearchPresenter
    private lateinit var binding: ActivitySearchBinding
    lateinit var adapter: MovieAdapter
    var movieList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.btSearch.setOnClickListener(this)
        searchPresenter = SearchPresenter()
    }

    override fun showMovieList(movieList: ArrayList<Item>) {
        adapter = MovieAdapter(this, movieList)
        binding.rvMovieList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btSearch -> {
                searchPresenter.getMovieList(
                    title = binding.etSearch.text.toString(),
                    successHandler = {
                        movieList.clear()
                        movieList.addAll(it.items)
                        showMovieList(movieList)
                    },
                    failHandler = {
                        showToast(it)
                    }
                )
            }

        }
    }

}