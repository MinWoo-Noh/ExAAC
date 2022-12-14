package com.nowornaver.exviewmodeloverview

import androidx.lifecycle.*

// count 를 저장하는 ViewModel
class MyViewModel(
    _counter: Int,
    private val repositoryImpl: MyRepositoryImpl,
// 1. savedStateHandle 는 Key, Value 로 값을 받는다.
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // 5. ViewModel 은 Repository 를 통해서만 Data를 다뤄야한한다. 그러므로 ViewModel 에서는 자시이 참고할 Repository 는 생성자로 받아야한다.
    val counterFromRepository : LiveData<Int> = repositoryImpl.getCounter()

    fun increaseCounter(){
        repositoryImpl.increaseCounter()
    }

    //    var counter : Int = _counter
    var counter = savedStateHandle.get<Int>(SAVE_STATE_KEY) ?: _counter

    // counter 를 liveData 로 대체 (LiveData 는 변경 불가능한 타입이라서 Mutable(변할 수 있는) 사용)
    var liveCounter: MutableLiveData<Int> = MutableLiveData(_counter)
    val modifiedCounter : LiveData<String> = Transformations.map(liveCounter) {counter ->
        "$counter 입니다."
    }

    // to-way binding 을 하기 위한 변수 추가
    val hasChecked : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun saveState() {
        savedStateHandle.set(SAVE_STATE_KEY, counter)
    }

    companion object {
        // 2. Key 정의
        private const val SAVE_STATE_KEY = "counter"
    }
}