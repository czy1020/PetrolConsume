package com.example.czy.petrolconsume.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by CZY on 2017/3/1.
 */

public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout wrapper;//水平滚动条
    private ViewGroup menu, content;//菜单和内容区
    private int screenWidth;//屏幕宽度
    private int menuRightPadding;//菜单距离屏幕右侧的宽度，单位dp
    private boolean once;
    private int menuWidth;//菜单宽度
    private boolean isOpen;


    /**
     * 通过new创建这个对象的时候用这个方法
     *
     * @param context
     */
    public SlidingMenu(Context context) {
        this(context, null);
    }

    /**
     * 当在xml文件中，创建CustonView的时候，会调用这个构造方法
     *
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        /*
        abstract Display  getDefaultDisplay()； 获取默认显示的 Display 对象


        WindowManager是Android中一个重要的服务（Service）。
        WindowManager Service 是全局的，是唯一的。
        它将用户的操作，翻译成为指令，发送给呈现在界面上的各个Window。
        Activity会将顶级的控件注册到 Window Manager 中，
        当用户真实触碰屏幕或键盘的时候，Window Manager就会通知到，而当控件有一些请求产生，
        也会经由ViewParent送回到Window Manager中。从而完成整个通信流程。
         */
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        /*
        andorid.util 包下的DisplayMetrics 类提供了一种关于显示的通用信息，如显示大小，分辨率和字体。
        getWindowManager().getDefaultDisplay().getMetrics(display);

        注：构造方法DisplayMetrics 不需要传递任何参数
        getDefaultDisplay() 方法将取得的宽高存放于DisplayMetrics对象中，
        而取得的宽高是以像素为单位(Pixel) ，
        “像素”所指的是“绝对像素”而非“相对像素”。
         */

        //将当前窗口的一些信息放在DisplayMetrics类中
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        //得到了屏幕的宽度
        screenWidth = displayMetrics.widthPixels;

        //将dp转换为px
        // TODO: 2017/3/2  
        menuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 120, context.getResources().getDisplayMetrics());
    }

    /**
     * 当使用自定义属性时会调用此构造方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        //获取自定义属性
//        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);
//
//        int index=typedArray.getIndexCount();
//        for (int i = 0; i < index; i++) {
//            int attr = typedArray.getIndex(i);
//            switch (attr) {
//                case R.styleable.SlidingMenu_rightPadding:
//                    menuRightPadding = typedArray.getDimensionPixelSize(attr, menuRightPadding);
//                    break;
//            }
//        }
//
//        typedArray.recycle();
//
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
//        //得到了屏幕的宽度
//        screenWidth = displayMetrics.widthPixels;
//
//        //将dp转换为px
//        // TODO: 2017/3/2
//        menuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 50, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置子view的宽和高和自己的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //如果是第一次进来
        if (!once) {

            wrapper = (LinearLayout) getChildAt(0);
            //得到菜单区域
            menu = (ViewGroup) wrapper.getChildAt(0);
            //得到内容区域
            content = (ViewGroup) wrapper.getChildAt(1);

            //得到菜单区域的宽度:屏幕宽度减去菜单距离屏幕右侧的区域
            menuWidth = menu.getLayoutParams().width = screenWidth - menuRightPadding;
            //得到内容区域的宽度
            content.getLayoutParams().width = screenWidth;

            //下次就不会进来，不用重复设置宽度
            once = true;

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量将menu隐藏
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            //x为正值是滚动条向右移动，内容区域向左移动
            this.scrollTo(menuWidth, 0);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:

                //隐藏在左侧的宽度
                int scrollX = getScrollX();

                //将菜单隐藏
                if (scrollX >= menuWidth / 2) {
                    this.smoothScrollTo(menuWidth, 0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }

                return true;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (isOpen) {
            return true;
        }

        if (ev.getX() > 100) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen)
            return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (isOpen) {
            this.smoothScrollTo(menuWidth, 0);
            isOpen = false;
        }
    }

    /**
     * 切换菜单状态
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }
}
