package com.nowornaver.exviewmodeloverview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nowornaver.exviewmodeloverview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding Code 주석처리
//    private val binding: ActivityMainBinding by lazy {
//        ActivityMainBinding.inflate(layoutInflater)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // ViewBinding Code 주석처리
//        setContentView(binding.root)

        // 1. 리셋되는 카운터 적용
//        var count = 100
//        binding.textView.text = count.toString()
//
//        binding.button.setOnClickListener {
//            count += 1
//            binding.textView.text = count.toString()


        // 2. ViewModel 적용
        // ViewModel count 사용
        // ViewModel 인스턴스를 그냥 생성하면 여러개 만드러지는 경우가 있을수 있기때문에
        // ViewModelProvider 을 통해 싱글톤으로 생성해준다.
//        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
////        myViewModel.count = 100 count 값이 onCreate 에 있기때문에 Activity 가 재 생성될때 초기값이 100으로 돌아간다.
////        하지만 ViewModelProvider() 로 객체를 생성할때 초기값 전달하는것이 금지돼어있기때문에 펙토리패턴을 사용해야한다.
//
//        binding.textView.text = myViewModel.counter.toString()
//
//        binding.button.setOnClickListener {
//            myViewModel.counter += 1
//            binding.textView.text = myViewModel.counter.toString()
//        }

        // 3. 팩토리 패턴을 통해 뷰모델에 초기값 적용
        val factory = MyViewModelFactory(10, this)
//        val myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
        // 위임작업
        val myViewModel by viewModels<MyViewModel>() {factory}
        /* ViewBinding Code 주석처리
        binding.textView.text = myViewModel.counter.toString()
           */
        // 4. Data Binding
        // live data 를 관측하기위해서 lifecycleOwner(리싸이클오너)를 정해주고,
        binding.lifecycleOwner = this
        // xml 에 view 와 연동할 data 를 가지고 있는 ViewModel 을 지정을 해 준다.
        binding.viewmodel = myViewModel

        binding.button.setOnClickListener {
//            myViewModel.counter += 1
//            myViewModel.saveState()
//            binding.textView.text = myViewModel.counter.toString()
            myViewModel.liveCounter.value = myViewModel.liveCounter.value?.plus(1)
        }

        // 라이브 데이터 옵저빙
//        myViewModel.liveCounter.observe(this){ counter ->
//            binding.textView.text = counter.toString()
//        }

        // 라이브 데이터 값 변경
//        myViewModel.modifiedCounter.observe(this){ counter ->
//            binding.textView.text = counter.toString()
//        }
    }
}
