package com.hngg.jianshi.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hngg.jianshi.R;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class PopupWindowUtil {

    private final Activity mCtx;

    public PopupWindowUtil(Activity activity) {
        mCtx = activity;
    }

    public PopupWindow createPopupWindow(Activity context, ViewGroup rootView) {
        View contentView = LayoutInflater.from(context)
                .inflate(R.layout.popup_video_menu, rootView, false);
        ListView lvMenu = contentView.findViewById(R.id.lv_videoMenu);
        initLVData(lvMenu);

        PopupWindow popWnd = new PopupWindow(context);
        popWnd.setContentView(contentView);

        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        return popWnd;
    }

    private void initLVData(ListView lvMenu) {
        String[] stringArray = mCtx.getResources().getStringArray(R.array.menu_video);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mCtx, R.layout.item_text, stringArray);
        lvMenu.setAdapter(adapter);

        lvMenu.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    //删除

                    break;
                case 1:
                    //优先下载
                    break;
                case 2:
                    //前往详情页
                    break;
            }
        });
    }


}
