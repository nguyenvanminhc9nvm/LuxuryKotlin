package com.example.luxurykotlin.ui.main

import android.util.Log
import com.example.luxurykotlin.data.model.RegisterRequest
import com.example.luxurykotlin.ui.base.BaseViewModel
import com.example.luxurykotlin.utils.tracking.trackProgressBar
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel : BaseViewModel<MainViewModel.Input, MainViewModel.Output>() {

    /**
     * đầu vào của ViewModel là các giá trị thuộc class
     * Input View (Activity) sẽ cũng cấp các giá trị cho ViewModel thông qua hàm khởi tạo của Input
     * @param address đây là 1 giá trị observable vì sao lại cần observable vì observable sẽ thay đổi khi giá trị nó lắng nghe thay đổi theo
     * @param triggerSignUp đây là observale nhấn button có kiểu là Unit (đọc thêm về Unit kotlin)
     * */
    data class Input(
        val address: Observable<String>,
        val phoneNumber: Observable<String>,
        val password: Observable<String>,
        val triggerSignUp: Observable<Unit>
    )

    /**
     * Output trả ra cho View thông báo về luồng vừa thực hiện thành công hay không
     * */
    data class Output(
        val isSuccess: Observable<Boolean>
    )

    override fun transform(input: Input): Output {
        /*
        * BehaviorSubject là giá trị observable cho phép lưu dữ liệu và lấy dữ liệu từ chúng thông qua [value]
        *
        * */
        val mAddress = BehaviorSubject.create<String>()
        val mPhoneNumber = BehaviorSubject.create<String>()
        val mPassword = BehaviorSubject.create<String>()
        val mIsSuccess = BehaviorSubject.create<Boolean>()

        /**
         * các giá trị Input sẽ cung cấp cho các giá trị trong hàm transform để hàm transform lưu trữ dữ liệu
         * */
        input.address.subscribe(mAddress)
        input.phoneNumber.subscribe(mPhoneNumber)
        input.password.subscribe(mPassword)
        /**
         * sự kiện nhấn button
         * */
        input.triggerSignUp.map {
            /**
             * map là 1 function có kiểu trả về là giá trị
             * Observable
             * chức năng của map là chuyển đổi 1 hoặc nhiều giá trị về thành 1 giá trị khác
             * * */
            val addressStr = mAddress.value ?: ""
            val phoneNumberStr = mPhoneNumber.value ?: ""
            val passwordStr = mPassword.value ?: ""
            return@map RegisterRequest(passwordStr, phoneNumberStr, addressStr)
        }.flatMap {
            /**
             * flatmap có chức năng chuyển đổi 1 luồng observable sang 1 luồng observable mới
             * */
            return@flatMap doSignUp(it)
        }.subscribe({ response ->
            /**
             * response là trường hợp thành công
             * subscribe là nơi nhận các giá trị mà các đài quan sát Observable quan sát được
             * check response trả
             * */
            if (response == "Success") {
                mIsSuccess.onNext(true)
            } else if (response == "Failed" || response == "Null") {
                mIsSuccess.onNext(false)
            }
        }, { throws ->
            /**
             * throws là lỗi khi observable quan sát 1 giá trị null
             * */
            Log.d("error", "transform: ${throws.message}")
        }).addToDisposable()
        return Output(mIsSuccess)
    }

    /**
     * 1 function đăng ký có kiểu trả về là
     * Observable
     * @param registerRequest
     * */
    private fun doSignUp(
        registerRequest: RegisterRequest
    ): Observable<String> {
        return mDataManager.doLogin(registerRequest)
            .trackProgressBar(mProgressBar)
            .subscribeOn(mScheduler.io)
            .observeOn(mScheduler.ui)
    }


}