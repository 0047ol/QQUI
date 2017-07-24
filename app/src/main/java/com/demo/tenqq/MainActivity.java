package com.demo.tenqq;

import android.app.*;
import android.view.View.*;
import android.widget.*;
import android.os.*;
import android.view.*;
import android.view.WindowManager.*;
import android.graphics.drawable.*;
import android.graphics.*;
import com.tencentqq.widget.QQToast;
import com.tencentqq.widget.*;
import android.widget.AdapterView.*;
import android.content.*;

public class MainActivity extends Activity implements OnClickListener
{
	private Button one;
	private Button two;
	private Button view;
	private Button mEdit;
	private Button qq_toast;
	private Button more;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
//		Window window = getWindow();
//		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//		window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}

		one=(Button)findViewById(R.id.one);
        one.setOnClickListener(this);

        two=(Button)findViewById(R.id.two);
        two.setOnClickListener(this);

        view=(Button)findViewById(R.id.view_dialog);
        view.setOnClickListener(this);

        mEdit=(Button)findViewById(R.id.edit_dialog);
        mEdit.setOnClickListener(this);    
		
		qq_toast=(Button)findViewById(R.id.qq_toast);
		qq_toast.setOnClickListener(this);
		
        more=(Button)findViewById(R.id.other);
	    more.setOnClickListener(this);   
}
				@Override
				public void onClick(View p1)
				{
					switch(p1.getId()){
					case R.id.one:
									final QQDialog a=new QQDialog(MainActivity.this);
									a.setTitle("Title"); //设置对话框标题
							a.setMessage("This Is Message"); //设置对话框信息
									a.setCanceledOnTouchOutside(true); //设置对话框是否可以触摸关闭
									a.setCancelable(true); //设置对话框是否可以点击返回键关闭
									a.setPositiveButton("Ok", new OnClickListener(){
											//设置对话框按钮
											@Override
											public void onClick(View p1)
											{
												// 按钮点击事件
												a.dismiss(); //关闭对话框
												
											}
										});
									a.show(); //显示对话框
									break;
								case R.id.two:
							final QQDialog b=new QQDialog(MainActivity.this);
							b.setTitle("Title");
							b.setMessage("This Is Message");
									b.setCanceledOnTouchOutside(false);
									b.setNeutralButton("Cancel",null);
									b.setPositiveButton("Ok", new OnClickListener(){

											@Override
											public void onClick(View p1)
											{
												// TODO: Implement this method
												b.dismiss();
											}
										});
									b.show();
									break;
								case R.id.view_dialog:
									ImageView image=new ImageView(MainActivity.this);
									image.setImageResource(R.drawable.ic_launcher);
							final QQDialog d=new QQDialog(MainActivity.this);
							d.setTitle("Title");
									d.setView(R.layout.test);
									//d.setView(image);
									d.setCanceledOnTouchOutside(false);
									d.setNegativeButton("Cancel",null);
									d.setPositiveButton("Ok", new OnClickListener(){

											@Override
											public void onClick(View p1)
											{
												// TODO: Implement this method
												d.dismiss();
											}
										});
									d.show();
									break;
								case R.id.edit_dialog:
							final QQDialog e=new QQDialog(MainActivity.this);
							e.setTitle("Title");
							//e.setMessage("This is message");
									e.setEditText("Text","The Is hinttext");
									e.setCanceledOnTouchOutside(false);
									e.setNegativeButton("Cancel",null);
									e.setPositiveButton("Ok", new OnClickListener(){

											@Override
											public void onClick(View p1)
											{
												// TODO: Implement this method
												e.dismiss();
												//关闭对话框
												if("".equals(e.getEditText().toString().trim()))
												{ //判断输入框内是否有内容
													Toast.makeText(MainActivity.this, "你还没有输入文字",0).show();
												}else{ //如果有内容则
													Toast.makeText(MainActivity.this,e.getEditText(),0).show();
												}
											}
										});
									e.show();
							break;
						case R.id.qq_toast:
							QQToast.makeText(MainActivity.this,"This Is Toast.",0,0,0);
						break;
						case R.id.other:
							View view = getLayoutInflater().inflate(R.layout.qq_choose_dialog, null);
							final Dialog dialog = new Dialog(MainActivity.this, R.style.transparentFrameWindowStyle);
							dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
																		 LayoutParams.WRAP_CONTENT));
							final Window window = dialog.getWindow();
							// 设置显示动画
							window.setWindowAnimations(R.style.main_menu_animstyle);
							WindowManager.LayoutParams wl = window.getAttributes();
							wl.x = 0;
							wl.y = getWindowManager().getDefaultDisplay().getHeight();
							// 以下这两句是为了保证按钮可以水平满屏
							wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
							wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
							// 设置显示位置
							dialog.onWindowAttributesChanged(wl);
							// 设置点击外围解散
							dialog.setCanceledOnTouchOutside(true);
							Button more_button1 = (Button) window.findViewById(R.id.more_button1);
							Button more_button2 = (Button) window.findViewById(R.id.more_button2);
							Button more_cancel = (Button) window.findViewById(R.id.more_cancel);
							more_button1.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v)
									{
										dialog.cancel();
									}
								});
							more_button2.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v)
									{
										dialog.cancel();

									}
								});
							more_cancel.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v)
									{
										dialog.cancel();
									}
								});
							dialog.show();
							break;
					}
				}
				}
