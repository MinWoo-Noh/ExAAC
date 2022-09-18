package com.nowornaver.exviewmodeloverview

import androidx.lifecycle.LiveData

interface MyRepository {
    fun getCounter(): LiveData<Int> // DB 에서 가져온 Data 를 가져오는 함수
    fun increaseCounter() // 카운터 값을 증가시키는 함수
}