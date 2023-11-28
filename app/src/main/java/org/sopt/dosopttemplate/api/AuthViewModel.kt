package org.sopt.dosopttemplate.api// 보시면 알겠지만 LiveData는 lifecycle 라이브러리에 들어있습니다!
// 서비스 객체를 따로 만들지 않고 바로 불러왔습니다.
// 언제나 그렇듯 Call과 Callback은 Retrofit2의 것을 사용해야 합니다. okhttp 아님 주의!
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.api.ServicePool.authService

class AuthViewModel : ViewModel() {
    // MutableLiveData를 사용하여 login result 객체를 생성합니다.

    private val _loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginResult: MutableLiveData<ResponseLoginDto> get() = _loginResult

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> get() = _loginSuccess

    private val _signUpResult: MutableLiveData<Unit> = MutableLiveData()
    val signUpResult: MutableLiveData<Unit> get() = _signUpResult

    private val _signUpSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signUpSuccess: MutableLiveData<Boolean> get() = _signUpSuccess

    val isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)

    val _id: MutableLiveData<String> = MutableLiveData()
    val id: String get() = _id.value?:""
    val _password: MutableLiveData<String> = MutableLiveData()
    val password: String get() = _password.value?:""

    fun login() {
//        authService.login(RequestLoginDto(id, password))
//            .enqueue(object : Callback<ResponseLoginDto> {
//                override fun onResponse(
//                    call: Call<ResponseLoginDto>,
//                    response: Response<ResponseLoginDto>,
//                ) {
//                    if (response.isSuccessful) {
//                        _loginResult.value = response.body()
//                        _loginSuccess.value = true
//                    } else {
//                        // TODO: 에러 처리 로직
//                        _loginSuccess.value = false
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
//                    // TODO: 에러 처리 로직
//                }
//            })

        viewModelScope.launch {
            kotlin.runCatching {
                authService.login(RequestLoginDto(id, password))
            }.onSuccess {
                if (it.isSuccessful) {
                    loginResult.value = it.body()
                    loginSuccess.value = true
                } else {
                    loginSuccess.value = false
                }
            }.onFailure {
                // 에러 처리
            }
        }
    }

    fun signUp(id: String, nickname: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.signUp(RequestSignUpDto(id, nickname, password))
            }.onSuccess {
                if (it.isSuccessful) {
                    signUpResult.value = it.body()
                    signUpSuccess.value = true
                } else {
                    signUpSuccess.value = false
                }
            }.onFailure {
                // 에러 처리
            }
        }
    }


    fun onLoginButtonClick() {
        isLoginButtonClicked.value = true
    }
}