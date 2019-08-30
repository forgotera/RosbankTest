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
    var recalculationList:ArrayList<Double> = arrayListOf()
    //получение данных о валюте
    fun getCurrencyData() {
        val networkService = ListOfCurrencies.create()

        val observable = networkService.getValute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)

        disposable.add(observable)
    }

    //успешный запрос
    private fun handleResponse(answer:JsonAnswer) {
        //изменение данных
        for( (_,value) in answer.valute ){
            valuteList.add(value)
            recalculationList.add(value.value!!)
        }
        view.setAdapter(valuteList)
        view.updateUI()
    }


    //ошибка
    private fun handleError(error: Throwable) {
        Log.d("responce","error connection")
    }

    fun recalculationSum(price:String) {
        Log.d("price11","$price")
        if (price != "") {
            for ((index,element) in valuteList.withIndex()) {
                val trueValue = recalculationList[index] / valuteList[0]!!.nominal!!
                valuteList[index]!!.value = price.toInt() / trueValue
            }
        }
        view.updateUI()
    }
}