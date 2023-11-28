package org.sopt.dosopttemplate.api// 보시면 알겠지만 LiveData는 lifecycle 라이브러리에 들어있습니다!
// 서비스 객체를 따로 만들지 않고 바로 불러왔습니다.
// 언제나 그렇듯 Call과 Callback은 Retrofit2의 것을 사용해야 합니다. okhttp 아님 주의!
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.api.ServicePool.authService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    // MutableLiveData를 사용하여 login result 객체를 생성합니다.

    private val _loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginResult: MutableLiveData<ResponseLoginDto> get() = _loginResult
    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> get() = _loginSuccess

    val isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)

    fun login(id: String, password: String) {
        authService.login(RequestLoginDto(id, password))
            .enqueue(object : Callback<ResponseLoginDto> {
                override fun onResponse(
                    call: Call<ResponseLoginDto>,
                    response: Response<ResponseLoginDto>,
                ) {
                    if (response.isSuccessful) {
                        _loginResult.value = response.body()
                        _loginSuccess.value = true
                    } else {
                        // TODO: 에러 처리 로직
                        _loginSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                    // TODO: 에러 처리 로직
                }
            })
    }

    fun onLoginButtonClick() {
        isLoginButtonClicked.value = true
    }
}