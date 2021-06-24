package com.hngg.jianshi.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BottomDeleteDialog extends Dialog {

    private final Context mCtx;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    public BottomDeleteDialog(@NonNull Context context) {
        super(context, R.style.BottomDialog);
        mCtx = context;
        init();
    }

    private void init() {
        Window window = getWindow();
        window.setWindowAnimations(R.style.Animation_Bottom_Rising);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; //不拦截透明部分点击事件
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);
        setCanceledOnTouchOutside(false);
        setCancelable(true);

        LayoutInflater inflater = (LayoutInflater) mCtx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // instantiate the dialog with the custom Theme
        //  final CustomDialog dialog = new CustomDialog(mCtx, R.style.Dialog);
        View layout = inflater.inflate(R.layout.dialog_bottom_delete, null);
        ButterKnife.bind(this, layout);
        addContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        // set the dialog title
        btnDelete.setOnClickListener(v -> {
            onDeleteListener();
            dismiss();
        });
        btnCancel.setOnClickListener(v -> {
            onCancelListener();
            dismiss();
        });
    }

    public abstract void onDeleteListener();
    public abstract void onCancelListener();
}
