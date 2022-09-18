package com.nowornaver.exviewmodeloverview

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

// 1개의 속성
//@BindingAdapter("app:progressScaled")
//fun setProgress(progressBar: ProgressBar, counter: Int){
//    progressBar.progress = counter
//}

// 2개 이상의 속성을 동시에 다르고 싶을 경우 함수 수정
// layout 의 max 속성에 "100" -> "@{100}" 로 수정해준다.
/* 함수 설명
"app:progressScaled" ,"android:max" 를 동시에 관찰하면서 변화가 있을때 프로그레스 증가량을 2배씩 증가 하되,
Max 값 이상이 되지 않게 하는 함수이다.*/

// @ :: 엣 -> to-way binding 의 표시는    !!!!! @={~~~} 이다. !!!!
// 엣을 사용하는 바인딩은 데이타 View 로 데이터를 보내기만 하는 구조이다.
// View 가 데이터를 보내게도 할 수 있다. 이긋이 투에이 바인딩이다.
// to-way 바인딩을 위해서 ViewModel 에 변수(hasChecked)를 하나 추가한다.
@BindingAdapter(value = ["app:progressScaled" ,"android:max"], requireAll = true)
fun setProgress(progressBar: ProgressBar, counter: Int, max: Int){
    progressBar.progress = (counter * 2).coerceAtMost(max)
}