package com.tencentqq.widget;

import android.widget.*;
import android.widget.AbsListView.*;
import java.util.concurrent.*;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.os.*;

public class ReboundEffectsView extends ListView implements OnScrollListener{  

    private static final String TAG = "ReboundEffectsView";  

    //下拉因子,实现下拉时的延迟效果  
    private static final float PULL_FACTOR = 0.4F;  

    //回弹时每次减少的高度  
    private static final float  PULL_BACK_REDUCE_STEP = 1F;  

    //回弹时递减headview高度的频率, 注意以纳秒为单位  
    private static final int PULL_BACK_TASK_PERIOD = 500000;  


    //记录下拉的起始点  
    private boolean isRecored;  

    //记录刚开始下拉时的触摸位置的Y坐标  
    private int startY;   

    //第一个可见条目的索引  
    private int firstItemIndex;  

    //用于实现下拉弹性效果的headView  
    private View headView;  

    private int currentScrollState;  

    //实现回弹效果的调度器  
    private ScheduledExecutorService  schedulor;  

    //实现回弹效果的handler,用于递减headview的高度并请求重绘  
    private Handler handler = new Handler(){  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  

            AbsListView.LayoutParams params = (LayoutParams) headView.getLayoutParams();  

            //递减高度  
            params.height -= PULL_BACK_REDUCE_STEP;  

            headView.setLayoutParams(params);  

            //重绘  
            headView.invalidate();  

            //停止回弹时递减headView高度的任务  
            if(params.height <= 0){  
                schedulor.shutdownNow();  
            }  
        }  
    };  

    /** 
     * 构造函数 
     * @param context 
     */  
    public ReboundEffectsView(Context context) {  
        super(context);  

        init();  

    }  

    /** 
     * 构造函数 
     * @param context 
     * @param attr 
     */  
    public ReboundEffectsView(Context context, AttributeSet attr) {  
        super(context, attr);  

        init();  
    }  

    /** 
     * 初始化 
     */  
    private void init() {  
        //监听滚动状态  
        setOnScrollListener(this);  

        //创建PullListView的headview  
        headView = new View(this.getContext());  

        //默认白色背景,可以改变颜色, 也可以设置背景图片  
        headView.setBackgroundColor(Color.parseColor("#FFF9F9FB"));    

        //默认高度为0  
        headView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.FILL_PARENT, 0));  

        this.addHeaderView(headView);  
    }  



    /** 
     * 覆盖onTouchEvent方法,实现下拉回弹效果 
     */  
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  

        switch (event.getAction()) {  
			case MotionEvent.ACTION_DOWN:  

				//记录下拉起点状态  
				if (firstItemIndex == 0 ) {  

					isRecored = true;  
					startY = (int) event.getY();  

				}  
				break;  

			case MotionEvent.ACTION_CANCEL:  
			case MotionEvent.ACTION_UP:  


				if(!isRecored){  
					break;  
				}  

				//以一定的频率递减headview的高度,实现平滑回弹  
				schedulor = Executors.newScheduledThreadPool(1);  
				schedulor.scheduleAtFixedRate(new Runnable() {  

						@Override  
						public void run() {  
							handler.obtainMessage().sendToTarget();  

						}  
					}, 0, PULL_BACK_TASK_PERIOD, TimeUnit.NANOSECONDS);  

				isRecored = false;  

				break;  


			case MotionEvent.ACTION_MOVE:  

				if (!isRecored && firstItemIndex == 0 ) {  
					isRecored = true;  
					startY = (int) event.getY();  
				}  

				if(!isRecored){  
					break;  
				}  

				int tempY = (int) event.getY();  
				int moveY = tempY - startY;  

				if(moveY < 0){  
					isRecored = false;  
					break;  
				}  

				headView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.FILL_PARENT,  (int)(moveY * PULL_FACTOR)));  
				headView.invalidate();  

				break;  
        }  
        return super.onTouchEvent(event);  
    }  



    public void onScroll(AbsListView view, int firstVisiableItem,  
						 int visibleItemCount, int totalItemCount) {  
        firstItemIndex = firstVisiableItem;  

    }  

    public void onScrollStateChanged(AbsListView view, int scrollState) {  
        currentScrollState = scrollState;  
    }  

}  
