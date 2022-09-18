package com.nowornaver.exviewmodeloverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// MyRepository 구현하는 Class 보통 구현 인터페이스의 뒤에 Impl 을 붙여줘서 네이밍을한다.
class MyRepositoryImpl(count : Int) : MyRepository {

    private val liveCounter = MutableLiveData<Int>(count) // DB 에서 가져온 Data 를 사용해야하지만 없으므로 대체

    // 삾을 반환하는 함수
    override fun getCounter(): LiveData<Int> {
        return liveCounter
    }

    // 값을 1 증가시키는 함수
    override fun increaseCounter() {
        liveCounter.value = liveCounter.value?.plus(1)
    }
}