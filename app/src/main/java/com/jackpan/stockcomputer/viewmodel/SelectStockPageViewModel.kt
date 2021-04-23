package com.jackpan.stockcomputer.viewmodel

import android.app.Activity
import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

class SelectStockPageViewModel  @ViewModelInject constructor(application: Application, val  activity: Activity): AndroidViewModel(application){




}