package com.sprout.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sprout.base.BaseViewModel
import com.sprout.model.LoginData
import com.sprout.net.Injection
import kotlinx.coroutines.launch

class BindLoginActivityViewModel : BaseViewModel(Injection.repository) {

    var data: MutableLiveData<LoginData> = MutableLiveData()

    fun login(map: HashMap<String, String>) {
        viewModelScope.launch {
            var result = repository.login(map)
            if (result != null) {
                data.postValue(result.data)
            }
        }
    }

}