package com.example.luxurykotlin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.luxurykotlin.R
import com.example.luxurykotlin.ui.base.BaseActivity
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<MainViewModel>() {
    override fun createViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        /**
         * cung cấp các giá trị input cho ViewModel
         *
         * */
        viewModel.transform(
            MainViewModel.Input(
            edAddress.textChanges().map { it.toString() },
                edPassword.textChanges().map { it.toString() },
                edPhoneNumber.textChanges().map { it.toString() },
                button.clicks().throttleFirst(300, TimeUnit.MILLISECONDS)
        )).apply {
            /**
             * nhận output và xử lý
             * */
            isSuccess.observeOn(schedulerProvider.ui).subscribe {
                if (it) {
                    Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()
                }
            }.addToDisposable()
        }
    }

    override fun bindViewModel() {
    }

}