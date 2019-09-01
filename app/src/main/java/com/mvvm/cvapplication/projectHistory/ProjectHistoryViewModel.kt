package com.mvvm.cvapplication.projectHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.cvapplication.cvdetail.model.Projects
import javax.inject.Inject

class ProjectHistoryViewModel @Inject constructor(): ViewModel() {
    val projectListLiveData = MutableLiveData<List<Projects>>()

}