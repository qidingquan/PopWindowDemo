package com.example.qdq.popwindowdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/9/11.
 * Author:qdq
 * Description:列表
 */
public class ListPopwindow extends PopupWindow {
    private ListView listview_one;
    private ListView listview_two;
    private LinearLayout layout_bottom;
    private PopListAdapter oneListAdapter;
    private PopListAdapter twoListAdapter;
    private Activity activity;

    private int oneHeight = 41;
    private int twoHeight = 41;
    private int showNum = 5;


    public ListPopwindow(Context context) {
        this(context, null);
    }

    public ListPopwindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListPopwindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.activity = (Activity) context;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_list_edittext, null);


        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setContentView(view);

        listview_one = view.findViewById(R.id.listview_one);
        listview_two = view.findViewById(R.id.listview_two);
        oneListAdapter = new PopListAdapter(context);
        twoListAdapter = new PopListAdapter(context);
        listview_one.setAdapter(oneListAdapter);
        listview_two.setAdapter(twoListAdapter);

        layout_bottom = view.findViewById(R.id.layout_bottom);

        oneListAdapter.updateData(getListData());
        twoListAdapter.updateData(getListData());
        initHeight(context);
    }

    @Override
    public void showAsDropDown(View anchorView, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int[] a = new int[2];
            anchorView.getLocationInWindow(a);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, xoff, a[1] + anchorView.getHeight() + yoff);
        } else {
            super.showAsDropDown(anchorView, xoff, yoff);
        }
    }

    private void initHeight(final Context context) {

        LinearLayout.LayoutParams oneParams = (LinearLayout.LayoutParams) listview_one.getLayoutParams();
        oneParams.height = getInitHeight();
        listview_one.setLayoutParams(oneParams);

        LinearLayout.LayoutParams twoParams = (LinearLayout.LayoutParams) listview_two.getLayoutParams();
        twoParams.height = getInitHeight();
        listview_two.setLayoutParams(twoParams);
        final View decorView=activity.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                decorView.getWindowVisibleDisplayFrame(r);
                int visibleHeight = r.bottom - r.top;

                int currentHeight = visibleHeight - ScreenUtil.dp2px(context, 48+40+40);
                currentHeight=getInitHeight()>currentHeight?currentHeight:getInitHeight();

                LinearLayout.LayoutParams oneParams = (LinearLayout.LayoutParams) listview_one.getLayoutParams();
                oneParams.height = currentHeight;
                listview_one.setLayoutParams(oneParams);

                LinearLayout.LayoutParams twoParams = (LinearLayout.LayoutParams) listview_two.getLayoutParams();
                twoParams.height = currentHeight;
                listview_two.setLayoutParams(twoParams);
            }
        });
    }
    private int getInitHeight(){
        int currentHeight = 0;

        int oneTotalNum = listview_one.getAdapter().getCount();
        int twoTotalNum = listview_two.getAdapter().getCount();

        int maxTotalNum = oneTotalNum > twoTotalNum ? oneTotalNum : twoTotalNum;
        int maxItemHeight = oneHeight > twoHeight ? oneHeight : twoHeight;

        if (maxTotalNum > showNum) {
            currentHeight = ScreenUtil.dp2px(activity, maxItemHeight) * showNum;
        } else {
            currentHeight = ScreenUtil.dp2px(activity, maxItemHeight) * maxTotalNum;
        }
        return currentHeight;
    }
    private List<String> getListData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add("子项目" + i);
        }
        return dataList;
    }
}
