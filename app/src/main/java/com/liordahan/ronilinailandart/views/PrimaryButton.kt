package com.liordahan.ronilinailandart.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.liordahan.ronilinailandart.R
import com.liordahan.ronilinailandart.databinding.PrimaryButtonBinding

class PrimaryButton @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr){

    private var binding: PrimaryButtonBinding = PrimaryButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.PrimaryButton)
            if (a.hasValue(R.styleable.PrimaryButton_title)) {
                val title = a.getString(R.styleable.PrimaryButton_title)
                setTitle(title)
            }

            a.recycle()
        }

    }

    fun setTitle(title: String?) {
        binding.primaryBtnText.text = title
    }

}