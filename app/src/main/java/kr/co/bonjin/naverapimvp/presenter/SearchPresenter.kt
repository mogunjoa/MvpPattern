package kr.co.bonjin.naverapimvp.presenter

import kr.co.bonjin.naverapimvp.contract.SearchContract
import kr.co.bonjin.naverapimvp.model.repository.MovieRepository
import kr.co.bonjin.naverapimvp.model.vo.Movie

class SearchPresenter : SearchContract.Presenter {
    private var movieRepository = MovieRepository()

    override fun getMovieList(
        title: String,
        successHandler: (Movie) -> Unit,
        failHandler: (String) -> Unit
    ) {
        if (title.isEmpty()) {
            failHandler("검색어를 입력해주세요.")
            return
        } else {
            movieRepository.getMovieList(
                title = title,
                success = {
                    successHandler(it)
                },
                failure = {
                }
            )
        }

    }

}