package com.zwing.chat.core

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zwing.chat.network.base.BaseDataSource
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding> : Fragment() {
    lateinit var viewModel: VM
    lateinit var binding: VB
    lateinit var loader: Dialog
    val isLoading by lazy { MutableLiveData(false) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[getViewModelClass()]
        binding = getViewBinding()
        binding.lifecycleOwner = viewLifecycleOwner
       // loader = Loader.getLoader(requireActivity())
        return binding.root
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    abstract fun getViewBinding(): VB

//    fun showErrorPopup(activity: Activity) {
//        val dialog = Dialog(activity, R.style.DialogFragmentTheme)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
//        dialog.setContentView(R.layout.error_layout)
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.show()
//    }


    open fun <T> wsWithLoader(dataResource: BaseDataSource.Resource<T>, block: () -> Unit) {
        when (dataResource.status) {
            BaseDataSource.Resource.Status.SUCCESS -> {
                Log.d("data", dataResource.data.toString())
                loader.dismiss()
                block.invoke()
            }

            BaseDataSource.Resource.Status.LOADING -> {
                if (!loader.isShowing) {
                    loader.show()
                }
            }

         /*   BaseDataSource.Resource.Status.TOKEN_EXPIRED -> {
                if (!loader.isShowing) {
                    loader.show()
                }
            }*/

            BaseDataSource.Resource.Status.ERROR -> {
                Toast.makeText(
                    requireContext(),
                    dataResource.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("error", dataResource.message.toString())
//                if (dataResource.message.toString() == "unauthorized"){
//                    Toast.makeText(requireContext(),"",Toast.LENGTH_SHORT).show()
//                    requireActivity().finishAffinity()
//                    PrefManager.clear()
//                    requireActivity().finishAffinity()
//                    //requireActivity().openActivity<SignupEmailActivity>()
//                }
//                else if (dataResource.message.toString() == "Your account has been deactived or blocked"){
//                    Toast.makeText(requireContext(),dataResource.message.toString(),Toast.LENGTH_SHORT).show()
//                    requireActivity().finishAffinity()
//                    PrefManager.clear()
//                    requireActivity().finishAffinity()
//                    //requireActivity().openActivity<SignupEmailActivity>()
//                }
//                else{
//
//                }
                loader.dismiss()
            }
        }
    }

    open fun <T> wsWithViewLoader(
        dataResource: BaseDataSource.Resource<T>,
        block: () -> Unit
    ) {
        when (dataResource.status) {
            BaseDataSource.Resource.Status.SUCCESS -> {
                isLoading.postValue(false)
                block.invoke()
            }

            BaseDataSource.Resource.Status.LOADING -> {
                isLoading.postValue(true)
            }
/*
            BaseDataSource.Resource.Status.TOKEN_EXPIRED -> {
                isLoading.postValue(false)
                Toast.makeText(requireContext(), dataResource.message ?: "Token expired. Please log in again.", Toast.LENGTH_LONG).show()
            }*/


            BaseDataSource.Resource.Status.ERROR -> {
                isLoading.postValue(false)
                Toast.makeText(
                    requireContext(),
                    dataResource.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    open fun <T> ws(dataResource: BaseDataSource.Resource<T>, block: () -> Unit) {
        when (dataResource.status) {
            BaseDataSource.Resource.Status.SUCCESS -> {
                Log.d("data", dataResource.data.toString())
                block.invoke()
            }

            BaseDataSource.Resource.Status.LOADING -> {

            }

            BaseDataSource.Resource.Status.ERROR -> {
                Toast.makeText(
                    requireContext(),
                    dataResource.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("error", dataResource.message.toString())
            }

        /*    BaseDataSource.Resource.Status.TOKEN_EXPIRED -> {
                Toast.makeText(
                    requireContext(),
                    dataResource.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("token_expired", dataResource.message.toString())
            }*/
        }
    }
}