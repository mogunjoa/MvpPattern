package kr.co.bonjin.naverapimvp.model.repository

import kr.co.bonjin.naverapimvp.model.vo.Movie

interface MovieRepository {
    fun getMovieList(title: String, success: (Movie) -> Unit, failure: (Throwable) -> Unit)
}