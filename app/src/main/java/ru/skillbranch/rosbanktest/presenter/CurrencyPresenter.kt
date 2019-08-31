package ru.skillbranch.rosbanktest.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.skillbranch.rosbanktest.model.ListOfCurrencies
import ru.skillbranch.rosbanktest.model.POJO.Currency
import ru.skillbranch.rosbanktest.model.POJO.JsonAnswer
import ru.skillbranch.rosbanktest.view.RecyclerFragment
import java.util.concurrent.TimeUnit


class CurrencyPresenter(private val view: RecyclerFragment) {
    var valuteList:MutableList<Currency?> = mutableListOf()
    val disposable = CompositeDisposable()
    var recalculationList:ArrayList<Double> = arrayListOf()
    //проверка на обновление данных
    var isUpdateData = false

    //получение данных о валюте
    fun getCurrencyData() {
        val networkService = ListOfCurrencies.create()
        view.setAdapter(valuteList)

        val observable = networkService.getValute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
                //запрос каждые 60с
            .repeatWhen { t->t.delay (60,TimeUnit.SECONDS)}
            .subscribe(this::handleResponse, this::handleError)

        disposable.add(observable)
    }

    //успешный запрос
    private fun handleResponse(answer:JsonAnswer) {

        //очищение данных при повторном запросе
        if(valuteList.size != 0){
            valuteList.clear()
            recalculationList.clear()
            isUpdateData = true
        }

        //изменение данных
        for( (_,value) in answer.valute ){
            valuteList.add(value)
            //сохранение значения для пересчета кол-ва валюты в рублях
            recalculationList.add(value.value!!)
        }
        //если данные обноаились пересчиваем валюту
        if(isUpdateData){
            recalculationSum(view.priceBuf)
        }
        view.updateUI()
    }


    //ошибка при запросе
    private fun handleError(error: Throwable) {
        Log.d("Error","${error.stackTrace}")
    }

    //пересчет валюты
    fun recalculationSum(price:String) {
            if (price != "") {
                for ((index, _) in valuteList.withIndex()) {
                    val trueValue = price.toInt() / recalculationList[index]
                    valuteList[index]!!.value = valuteList[index]!!.nominal!! * trueValue
                }
                view.updateUI()
            }
    }
}