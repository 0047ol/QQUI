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

public class MainActivity extends Activity
{
	private String[] data={"One Button","Two Button","Custom Layout","EditText View","Custom Toast","Other More"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();  
		actionBar.hide();  
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		
		ListView listView=(ListView)findViewById(R.id.rec); 
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.item,R.id.text_item,data); 
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
	    
				@Override
				public void onItemClick(AdapterView<?>p0, View p1, int p2, long p3)
				{
					// TODO Auto-generated method stub
					if (p2 == 1)
					{
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
					}
					else if (p2 == 2)
					{
						final QQDialog b=new QQDialog(MainActivity.this);
						b.setTitle("Title");
						b.setMessage("This Is Message");
						b.setCanceledOnTouchOutside(false);
						b.setNeutralButton("Cancel", null);
						b.setPositiveButton("Ok", new OnClickListener(){

								@Override
								public void onClick(View p1)
								{
									// TODO: Implement this method
									b.dismiss();
								}
							});
						b.show();
					}
					else if (p2 == 3)
					{
						//ImageView image=new ImageView(MainActivity.this);
						//image.setImageResource(R.drawable.ic_launcher);
						final QQDialog d=new QQDialog(MainActivity.this);
						//d.setTitle("Title"); //标题
						d.setView(R.layout.test);
						//d.setView(image); //图片
						d.setCanceledOnTouchOutside(false);
						d.setNegativeButton("Cancel", null);
						//按钮
//				d.setPositiveButton("Ok", new OnClickListener(){
//
//						@Override
//						public void onClick(View p1)
//						{
//							// TODO: Implement this method
//							d.dismiss();
//						}
//					});
						d.show();
					}
					else if (p2 == 4)
					{
						final QQDialog e=new QQDialog(MainActivity.this);
						e.setTitle("Title");
						e.setEditText("Text", "The Is hinttext");
						e.setCanceledOnTouchOutside(false);
						e.setNegativeButton("Cancel", null);
						e.setPositiveButton("Ok", new OnClickListener(){

								@Override
								public void onClick(View p1)
								{
									// TODO: Implement this method
									e.dismiss();
									//关闭对话框
									if ("".equals(e.getEditText().toString().trim()))
									{ //判断输入框内是否有内容
										Toast.makeText(MainActivity.this, "你还没有输入文字", 0).show();
									}
									else
									{ //如果有内容则
										Toast.makeText(MainActivity.this, e.getEditText(), 0).show();
									}
								}
							});
						e.show();
					}
					else if (p2 == 5)
					{
						QQToast.makeText(MainActivity.this, "This Is Toast.", 0, 0, 0);
					}
					else if (p2 == 6)
					{
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
					}
				}
			});
	}
}
