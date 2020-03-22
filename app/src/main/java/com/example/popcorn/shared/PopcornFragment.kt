package com.example.popcorn.shared

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.popcorn.BR
import com.example.popcorn.util.AutoClearedValue

abstract class PopcornFragment<VB : ViewDataBinding, VM : ViewModel>(@LayoutRes private val layoutResourceId: Int) : Fragment() {

    protected var binding by AutoClearedValue<VB>()
        private set

    protected abstract val viewModel: VM

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<VB>(inflater, layoutResourceId, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.setVariable(BR.viewModel, viewModel)
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // There are multiple glitches throughout the app where insets wouldn't get applied and the UI elements wouldn't be positioned properly. I'm not
        // sure why this is happening, but until we investigate, this solves the issue:
        with(binding.root) { post { requestLayout() } }
    }

    override fun onPause() {
        super.onPause()
        dismissKeyboard(binding.root)
    }

    inline fun Fragment.handleOnBackPressed(crossinline func: () -> Unit) =
        requireActivity().onBackPressedDispatcher.addCallback(this) { func.invoke() }


    protected fun dismissKeyboard(view: View) =
        (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)

    protected fun showKeyboard(view: View) =
        (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)


}
