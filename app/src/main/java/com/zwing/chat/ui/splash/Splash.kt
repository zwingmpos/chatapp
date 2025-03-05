package com.zwing.chat.ui.splash

import com.zwing.chat.core.BaseActivity
import com.zwing.chat.databinding.ActivitySplashBinding
import com.zwing.chat.ui.main.MainActivity
import com.zwing.chat.utils.extensions.openActivity

class Splash : BaseActivity<SplashVM, ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(viewModel: SplashVM, binding: ActivitySplashBinding) {
        super.onCreate(viewModel, binding)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

    }
    fun actionGo(){
        openActivity<MainActivity>()
    }
}