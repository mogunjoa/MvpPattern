package kr.co.bonjin.naverapimvp.model

import android.content.Context

class InfoRepository(context: Context) : InfoDataSource {

    private val infoRemoteDataSource = InfoRemoteDataSource(context)

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {

    }
}