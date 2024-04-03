package com.example.mvvmdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvpdemo.model.BookModel
import com.example.mvpdemo.model.DataItem
import com.example.mvpdemo.model.MainModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

class MainViewModel(model: MainModel) : ViewModel() {
    val resultListObservable = MutableLiveData<List<DataItem>>()
    val resultListErrorObservable = MutableLiveData<HttpException>()
    fun getResultListObservable(): LiveData<List<DataItem>> = resultListObservable
    fun getResultListErrorObservable(): LiveData<HttpException> = resultListErrorObservable
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    lateinit var entityList: List<DataItem>
    val mainModel = model

    fun fetchData() {
        val disposable : Disposable = mainModel.fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<BookModel?>() {
                override fun onSuccess(t: BookModel) {
                entityList = t.data
                resultListObservable.postValue(t.data)
                }

                override fun onError(e: Throwable) {
                resultListErrorObservable.postValue(e as HttpException)
                }
            })
        compositeDisposable.add(disposable)
    }

    fun onStop() {
        compositeDisposable.clear()
    }

    infix fun fetchItemTextFrom(it: DataItem): String {
        return "${it.year}: ${it.title}"
    }
}