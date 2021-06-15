package com.ekoenterprise.socialdemo

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.ekoapp.ekosdk.EkoClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val apiKey = getString(R.string.api_key)
        EkoClient.setup(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}