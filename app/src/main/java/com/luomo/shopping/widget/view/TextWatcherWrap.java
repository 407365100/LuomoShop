package com.luomo.shopping.widget.view;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 对文本输入监听类做的一个封装
 */
public abstract class TextWatcherWrap implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public abstract void onTextChanged(CharSequence charSequence, int start, int count, int after);

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
