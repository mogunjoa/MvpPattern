package kr.co.bonjin.naverapimvp.presenter

interface BasePresenter<T> {
    fun takeView(view: T)
    fun dropView()
}