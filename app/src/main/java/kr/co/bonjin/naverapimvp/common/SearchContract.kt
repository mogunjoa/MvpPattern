package kr.co.bonjin.naverapimvp.common

import android.content.Context
import kr.co.bonjin.naverapimvp.model.vo.Item
import kr.co.bonjin.naverapimvp.presenter.BasePresenter
import org.json.JSONObject

interface SearchContract {

    interface View {
        fun showMovieList(movieList: List<Item>)
    }

    interface Presenter : BasePresenter<View> {
        fun getMovieList(title: String, context: Context)
    }

}