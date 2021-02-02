package kr.co.bonjin.naverapimvp.common

import android.content.Context
import kr.co.bonjin.naverapimvp.model.vo.Item
import kr.co.bonjin.naverapimvp.model.vo.Movie
import kr.co.bonjin.naverapimvp.presenter.BasePresenter
import org.json.JSONObject

interface SearchContract {

    interface View {    //view에서 처리할 이벤트 선언
        fun showMovieList(movieList: ArrayList<Item>)
    }

    interface Presenter : BasePresenter<View> {     //Present 에서 처리할 이벤트 선언
        fun getMovieList(title: String, context: Context, successHandler: (Movie) -> Unit)
    }

}