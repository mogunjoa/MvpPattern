package kr.co.bonjin.naverapimvp.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import kr.co.bonjin.naverapimvp.BaseActivity
import kr.co.bonjin.naverapimvp.R
import kr.co.bonjin.naverapimvp.databinding.ActivitySearchBinding
import kr.co.bonjin.naverapimvp.model.adapter.MovieAdapter
import kr.co.bonjin.naverapimvp.presenter.SearchPresenter

class SearchActivity : BaseActivity(), View.OnClickListener {
    private lateinit var searchPresenter: SearchPresenter
    private lateinit var binding: ActivitySearchBinding
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        searchPresenter.takeView(this)

        adapter = MovieAdapter(this, movieList)
        binding.rvMovieList.adapter = adapter
        binding.btSearch.setOnClickListener(this)
    }

    override fun initPresenter() {
        searchPresenter = SearchPresenter()
    }

    private fun setUI() {}

    private fun setUpRecyclerView() {}


    override fun onClick(v: View?) {
        when (v) {
            binding.btSearch -> {
                searchPresenter.getMovieList(
                    title = binding.etSearch.text.toString(),
                    context = this
                )
            }
        }
    }

}