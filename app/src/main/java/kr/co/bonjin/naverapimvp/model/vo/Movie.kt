package kr.co.bonjin.naverapimvp.model.vo

import kr.co.bonjin.naverapimvp.model.vo.Item

data class Movie(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)