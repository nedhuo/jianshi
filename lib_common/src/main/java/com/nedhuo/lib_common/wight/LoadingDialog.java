package com.nedhuo.lib_common.wight;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.nedhuo.lib_common.R;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}