package ru.skillbranch.rosbanktest.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*
import ru.skillbranch.rosbanktest.R
import ru.skillbranch.rosbanktest.extensions.round
import ru.skillbranch.rosbanktest.model.POJO.Currency
import ru.skillbranch.rosbanktest.presenter.CurrencyPresenter

interface RecyclerFragmentView{
    fun setAdapter(data: MutableList<Currency?>)
    fun updateUI()
}

class RecyclerFragment: Fragment(),RecyclerFragmentView {

    private lateinit var mRecyclerView: RecyclerView
    private var mCurrencyPresenter: CurrencyPresenter? = null
    private var mAdapter: CurrencyAdapter? = null
    var priceBuf = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)
        mRecyclerView = view.findViewById(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        //связывание c presenter
        if (mCurrencyPresenter == null){
            mCurrencyPresenter = CurrencyPresenter(this)
        }
        //получение данных
        mCurrencyPresenter!!.getCurrencyData()

        return view
    }

    override fun setAdapter(data: MutableList<Currency?>) {
        mAdapter = CurrencyAdapter(data)
        mRecyclerView.adapter = mAdapter
    }

    override fun updateUI() {
        mAdapter!!.notifyDataSetChanged()
    }


    fun clickEditText(price:String){
        priceBuf = price
        mCurrencyPresenter!!.recalculationSum(price)
    }



    private inner class CurrencyAdapter(val data: MutableList<Currency?>): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {

            val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
            return CurrencyHolder(view)
        }

        override fun getItemCount(): Int {
            //кол-во элементов
            return data.size
        }

        override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
            holder.mCharCode.text = data[position]!!.charCode
            holder.mName.text = data[position]!!.name
            holder.mValue.text = data[position]!!.value!!.round(4).toString()
        }

        private inner class CurrencyHolder(mView:View):RecyclerView.ViewHolder(mView){
            var mCharCode: TextView = mView.charCode
            val mName: TextView = mView.name
            val mValue:TextView = mView.value
        }

    }
}