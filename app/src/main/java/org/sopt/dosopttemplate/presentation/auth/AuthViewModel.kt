package org.sopt.dosopttemplate.presentation.auth// 보시면 알겠지만 LiveData는 lifecycle 라이브러리에 들어있습니다!
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.api.ServicePool.authService
import org.sopt.dosopttemplate.data.model.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.ResponseLoginDto
import org.sopt.dosopttemplate.util.UtilClass.isIdConditionSatisfied
import org.sopt.dosopttemplate.util.UtilClass.isPasswordConditionSatisfied

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val data: ResponseLoginDto?) : LoginState()
    object Error : LoginState()
}

class AuthViewModel : ViewModel() {
    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Loading)
    private val loginState: MutableStateFlow<LoginState> get() = _loginState
    private val _signUpResult: MutableLiveData<Unit> = MutableLiveData()
    val signUpResult: MutableLiveData<Unit> get() = _signUpResult

    private val _signUpSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signUpSuccess: MutableLiveData<Boolean> get() = _signUpSuccess

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> get() = _loginSuccess

    val isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    val idConditionSatisfied: MutableLiveData<Boolean> = MutableLiveData(false)
    val passwordConditionSatisfied: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginConditionSatisfied: MutableLiveData<Boolean> =
        MutableLiveData(idConditionSatisfied.value ?: false && passwordConditionSatisfied.value ?: false)

    val _id: MutableLiveData<String> = MutableLiveData()
    val id: String get() = _id.value ?: ""
    val _password: MutableLiveData<String> = MutableLiveData()
    val password: String get() = _password.value ?: ""
    val _nickName: MutableLiveData<String> = MutableLiveData()
    val nickName: String get() = _nickName.value ?: ""

    fun login() {
//        viewModelScope.launch {
//            kotlin.runCatching {
//                authService.login(RequestLoginDto(id, password))
//            }.onSuccess {
//                if (it.isSuccessful && it.body() != null) {
//                    _loginState.value = LoginState.Success(it.body())
//                    _loginSuccess.value = true
//                } else {
//                    _loginSuccess.value = false
//                }
//            }.onFailure {
//                _loginSuccess.value = false
//                Log.d("login fail", it.message ?: "")
//            }
//        }
    }

    fun signUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.signUp(RequestSignUpDto(id, nickName, password))
            }.onSuccess {
                if (it.isSuccessful) {
                    _signUpResult.value = it.body()
                    _signUpSuccess.value = true
                } else {
                    _signUpSuccess.value = false
                }
            }.onFailure {
                _signUpSuccess.value = false
            }
        }
    }


    fun onLoginButtonClick() {
        isLoginButtonClicked.value = true
    }

    fun onIDTextChanged() {
        idConditionSatisfied.value = isIdConditionSatisfied(id)
        loginConditionSatisfied.value =
            idConditionSatisfied.value ?: false && passwordConditionSatisfied.value ?: false
    }

    fun onPasswordTextChanged() {
        passwordConditionSatisfied.value = isPasswordConditionSatisfied(password)
        loginConditionSatisfied.value =
            idConditionSatisfied.value ?: false && passwordConditionSatisfied.value ?: false
        Log.v("logincondition", loginConditionSatisfied.value.toString())
    }
}