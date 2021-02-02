package kr.co.bonjin.naverapimvp.contract

import kr.co.bonjin.naverapimvp.model.vo.Item
import kr.co.bonjin.naverapimvp.model.vo.Movie

interface SearchContract {

    interface View {    //view에서 처리할 이벤트 선언
        fun showMovieList(movieList: ArrayList<Item>)
        fun showToast(msg: String)
    }

    interface Presenter {     //Present 에서 처리할 이벤트 선언
        fun getMovieList(title: String, successHandler: (Movie) -> Unit, failHandler: (String) -> Unit)
    }

}