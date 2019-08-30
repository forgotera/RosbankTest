package ru.skillbranch.rosbanktest.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.skillbranch.rosbanktest.model.ListOfCurrencies
import ru.skillbranch.rosbanktest.model.POJO.Currency
import ru.skillbranch.rosbanktest.model.POJO.JsonAnswer
import ru.skillbranch.rosbanktest.view.RecyclerFragment


class CurrencyPresenter(private val view: RecyclerFragment) {
    var valuteList:MutableList<Currency?> = mutableListOf()
    val disposable = CompositeDisposable()
    fun getCurrencyData() {
        val networkService = ListOfCurrencies.create()

        view.setAdapter(valuteList)

        val observable = networkService.getValute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)

        disposable.add(observable)
    }

    //успешный запрос
    private fun handleResponse(answer:JsonAnswer) {
        //изменение данных
        Log.d("reponce234","${answer.valute}")
        for( (_,value) in answer.valute ){
            valuteList.add(value)
        }
        view.updateUI()
    }



    //не успешный запрос
    private fun handleError(error: Throwable) {
        Log.d("reponce234","error")
    }
}