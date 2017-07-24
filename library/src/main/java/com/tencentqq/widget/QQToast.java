package com.tencentqq.widget;

import android.content.*;  
import android.os.Handler;  
import android.os.Looper;  
import android.widget.Toast;
import java.lang.reflect.*;
import android.view.*;
import android.widget.*;  

public class QQToast
{

		public static Toast makeText(Context context,CharSequence tvString,int cocel,int textcolor,int images) {

			Toast toast = makeText(context,tvString,0);
			View layout = LayoutInflater.from(context).inflate(R.layout.toast_layout,null);
			ViewGroup viewgroup = (ViewGroup) layout.findViewById(R.id.toast_bg);
			ImageView imageview = (ImageView) layout.findViewById(R.id.iv);
			TextView textview = (TextView) layout.findViewById(R.id.text);
			viewgroup.setBackgroundColor(cocel);
			imageview.setBackgroundResource(images);
			textview.setText(tvString);
			textview.setTextColor(textcolor);
			toast = new Toast(context);
			toast.setView(layout);
			toast.getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置Toast可以布局到系统状态栏的下面
			toast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL,0,0);
			toast.setDuration(0);
			toast.show();
			if (images == 0)
			{
				imageview.setBackgroundResource(R.drawable.toast_image);
		     }
			if (textcolor == 0)
			{
				textview.setTextColor(0xff000000);
			}
			if (cocel == 0)
			{
				viewgroup.setBackgroundColor(0xffffffff);
			}
			try {
				Object mTN ;
				mTN = getField(toast, "mTN");
				if (mTN != null) {
					Object mParams = getField(mTN, "mParams");
					if (mParams != null
						&& mParams instanceof WindowManager.LayoutParams) {
						WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
						params.windowAnimations = R.style.Lite_Animation_Toast;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return toast;
		}


		private static Toast makeText(Context context, CharSequence tvString, int p2)
		{
			// TODO: Implement this method
			return null;
		}


		/**
		 * 反射字段
		 * @param object 要反射的对象
		 * @param fieldName 要反射的字段名称
		 */
		private static Object getField(Object object, String fieldName)
		throws NoSuchFieldException, IllegalAccessException {
			Field field = object.getClass().getDeclaredField(fieldName);
			if (field != null) {
				field.setAccessible(true);
				return field.get(object);
			}
			return null;
		}

	}


