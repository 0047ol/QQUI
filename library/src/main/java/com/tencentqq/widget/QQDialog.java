package com.tencentqq.widget;

import java.util.List;
import android.graphics.Color;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import android.content.res.*;
import android.support.v7.widget.*;
import android.content.*;
import android.view.inputmethod.*;

public class QQDialog
{
    private Dialog mDialog;
    private Context mContext;
    private CharSequence PositiveText=null;
    private CharSequence NegativeText=null;
    private CharSequence NeutralText=null;
    private CharSequence messageText=null;
    private CharSequence setEditText=null;
    private CharSequence hint=null;
    private CharSequence titleText=null;
    private int defStyle;
    private int PositiveId=0;
    private int NegativeId=0;
    private int NeutralId=0;
    private int messageId=0;
    private int EditTextId=0;
    private int titleId=0;
    private int hintId=0;
    private boolean isPositiveButtonShow=false;
    private boolean isNegativeButtonShow=false;
    private boolean isNeutralButtonShow=false;
    private boolean isEditText=false;
    private View mView;
    private QQDialog.Builder mBuilder;
    private View.OnClickListener  PositiveListener,NegativeListener,NeutralListener;
	private ViewGroup v2;
	private EditText Edits;
	private int mViewId=0;
	private boolean CanceledOnTouchOutside=true;
	private boolean Cancelable=true;

    public QQDialog show()
	{
        mBuilder = new Builder();
        if (!mDialog.isShowing())
		{
            mDialog.show();
        }
        return this;
    }
    public QQDialog dismiss()
	{
        mDialog.dismiss();
        return this;
    }
    public QQDialog cancel()
	{
        mDialog.cancel();
        return this;
    }
    public QQDialog(Context context)
	{
        this.mContext = context;
        this.defStyle = R.style.CustomDialog;
    }

    private class Builder
	{
        private Button PositiveButton;
        private Button NegativeButton;
        private Button NeutralButton; 
        private TextView mMessage,mTitle;
        private LinearLayout mButton;
        private FrameLayout mFrameLayout;
		private View v1view;
		private View neutralview;
		private View positiveview;

        private Builder()
		{
            mDialog = new Dialog(mContext, defStyle);
            mDialog.setContentView(R.layout.qq_dialog_layout);
			mDialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
            Window dialogWindow = mDialog.getWindow();
            DisplayMetrics d = mContext.getResources().getDisplayMetrics();
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); 
			mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialogWindow.setGravity(Gravity.CENTER);
			p.x = 0;//设置x坐标
			p.y = 0;//设置y坐标
            p.width = (int)(d.widthPixels * 0.78);
			p.height = (int)(d.heightPixels *0.6);
            dialogWindow.setAttributes(p);
            setCustomView(mDialog);
        }

		
        private void setCustomView(Dialog dialog)
		{
            mTitle = (TextView)dialog.findViewById(R.id.custom_miui_dialog_title);
			v1view = (View)dialog.findViewById(R.id.v1);
			neutralview = (View)dialog.findViewById(R.id.neutral_view);
			positiveview = (View)dialog.findViewById(R.id.positive_view);
			PositiveButton = (Button)dialog.findViewById(R.id.positive_button);
            NegativeButton = (Button)dialog.findViewById(R.id.negative_button);
            NeutralButton = (Button)dialog.findViewById(R.id.neutral_button); 
            mButton = (LinearLayout)dialog.findViewById(R.id.custom_miui_dialog_button);
            mFrameLayout = (FrameLayout)dialog.findViewById(R.id.custom_miui_dialog_view);
            v2 = (ViewGroup)dialog.findViewById(R.id.v2);
			Edits = (EditText)dialog.findViewById(R.id.Edits);
			PositiveButton.setTextColor(Color.parseColor("#FF333333"));
			NegativeButton.setTextColor(Color.parseColor("#FF333333"));
			NeutralButton.setTextColor(Color.parseColor("#FF333333"));
			Edits.setVisibility(View.GONE);
			
            ScrollView scrollView=new ScrollView(mContext);
            mMessage = new TextView(mContext);
            mMessage.setPadding(90,30,90,0);
            mMessage.setTextColor(Color.parseColor("#FF333333"));
            mMessage.setTextSize(16);
            mMessage.setLineSpacing(1, 1.3f);
            scrollView.addView(mMessage);
            mFrameLayout.addView(scrollView);

            if (isEditText == true)
			{
				Edits.setVisibility(View.VISIBLE);

                if (EditTextId == 0 && getEditText() != null)
				{
                    Edits.setText(setEditText);
                }
                else if (EditTextId != 0 && getEditText() == null)
				{
                    Edits.setText(EditTextId);
                }
                if (hintId == 0 && hint != null)
				{
                    Edits.setHint(hint);
                }
                else if (hintId != 0 && hint == null)
				{
                    Edits.setHint(hintId);
                }
				
            }
            
            if (mViewId != 0 && mView == null)
			{
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
				lp.setMargins(0,0,-30,0);  
				v2.setLayoutParams(lp); 
				LayoutInflater inflater = LayoutInflater.from(mContext);
				LinearLayout mView = (LinearLayout) inflater.inflate(mViewId, null); 
                mView.setPadding(0,0,0,0);
                mFrameLayout.addView(mView);
			}
            else if (mViewId == 0 && mView != null)
			{
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
				lp.setMargins(0,0,-30,0);  
				v2.setLayoutParams(lp); 
				mView.setPadding(50,50,50,50);
                mFrameLayout.addView(mView);
            }
			
			
            if (CanceledOnTouchOutside == true)
			{
                 mDialog.setCanceledOnTouchOutside(true);
            }
            else if (CanceledOnTouchOutside == false)
			{
                mDialog.setCanceledOnTouchOutside(false);
  
            }
			if (Cancelable == true)
			{
				mDialog.setCancelable(true);
            }
            else if (Cancelable == false)
			{
                mDialog.setCancelable(false);

            }
			

			if (titleId == 0 && titleText == null)
			{
                mTitle.setVisibility(View.GONE);
            }
            else if (titleId != 0 && titleText == null)
			{
                mTitle.setText(titleId);
            }
            else if (titleId == 0 && titleText != null)
			{
                mTitle.setText(titleText);
            }
            if (messageId == 0 && messageText == null)
			{
                mMessage.setVisibility(View.GONE);
            }
            else if (messageId != 0 && messageText == null)
			{
                mMessage.setText(messageId);
            }
            else if (messageId == 0 && messageText != null)
			{
                mMessage.setText(messageText);
            }

            if (PositiveId != 0 && PositiveText == null)
			{
				PositiveButton.setText(PositiveId);
            }
            else if (PositiveId == 0 && PositiveText != null)
			{
                PositiveButton.setText(PositiveText);
            }

            if (NegativeId != 0 && NegativeText == null)
			{
                NegativeButton.setText(NegativeId);
            }
            else if (NegativeId == 0 && NegativeText != null)
			{
                NegativeButton.setText(NegativeText);
            }

            if (NeutralId != 0 && NeutralText == null)
			{
                NeutralButton.setText(NegativeId);
            }
            else if (NeutralId == 0 && NeutralText != null)
			{
                NeutralButton.setText(NeutralText);
            }

            if (PositiveListener != null)
			{
                PositiveButton.setOnClickListener(PositiveListener);
            }
            else
			{
                PositiveButton.setOnClickListener(new OnDialogButtonClickListener());
            }
            if (NegativeListener != null)
			{
                NegativeButton.setOnClickListener(NegativeListener);
            }
            else
			{
                NegativeButton.setOnClickListener(new OnDialogButtonClickListener());
            }
            if (NeutralListener != null)
			{
                NeutralButton.setOnClickListener(NeutralListener);
            }
            else
			{
                NeutralButton.setOnClickListener(new OnDialogButtonClickListener());
            }
            viewButton();

        }

        private void viewButton()
		{
            int button_shape_bg=R.drawable.button_shape_bg;
            int button_shape_left=R.drawable.button_shape_left;
            int button_shape_center=R.drawable.button_shape_center;
            int button_shape_right=R.drawable.button_shape_right;

            if (isPositiveButtonShow == true && isNegativeButtonShow == false && isNeutralButtonShow == false)
			{
                PositiveButton.setBackgroundResource(button_shape_bg);
                NegativeButton.setVisibility(View.GONE);
                NeutralButton.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
            }
            if (isPositiveButtonShow == false && isNegativeButtonShow == true && isNeutralButtonShow == false)
			{
                PositiveButton.setVisibility(View.GONE);
                NegativeButton.setBackgroundResource(button_shape_bg);
                NeutralButton.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
            }
            if (isPositiveButtonShow == false && isNegativeButtonShow == false && isNeutralButtonShow == true)
			{
                PositiveButton.setVisibility(View.GONE);
                NegativeButton.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
                NeutralButton.setBackgroundResource(button_shape_bg);
            }
            if (isPositiveButtonShow == true && isNegativeButtonShow == true && isNeutralButtonShow == false)
			{
                PositiveButton.setBackgroundResource(button_shape_right);
                NegativeButton.setBackgroundResource(button_shape_left);
                NeutralButton.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
            }
            if (isPositiveButtonShow == true && isNegativeButtonShow == false && isNeutralButtonShow == true)
			{
                PositiveButton.setBackgroundResource(button_shape_right);
                NegativeButton.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
                NeutralButton.setBackgroundResource(button_shape_left);
            }
            if (isPositiveButtonShow == false && isNegativeButtonShow == true && isNeutralButtonShow == true)
			{
                PositiveButton.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
                NegativeButton.setBackgroundResource(button_shape_right);
                NeutralButton.setBackgroundResource(button_shape_left);
            }
            if (isPositiveButtonShow == true && isNegativeButtonShow == true && isNeutralButtonShow == true)
			{
                PositiveButton.setBackgroundResource(button_shape_right);
                NegativeButton.setBackgroundResource(button_shape_center);
                NeutralButton.setBackgroundResource(button_shape_left);
            }
            if (isPositiveButtonShow == false && isNegativeButtonShow == false && isNeutralButtonShow == false)
			{
                mButton.setVisibility(View.GONE);
				v2.setVisibility(View.GONE);
				v1view.setVisibility(View.GONE);
            }
        }
	}
	public QQDialog setCanceledOnTouchOutside(boolean Cancele)
	{
        this.CanceledOnTouchOutside = Cancele;
        return this;
    }
	public QQDialog setCancelable(boolean OnTouch)
	{
        this.Cancelable = OnTouch;
        return this;
    }
	
	
    public QQDialog setTitle(CharSequence text)
	{
        this.titleText = text;
        return this;
    }
    public QQDialog setTitle(int resourcesId)
	{
        this.titleId = resourcesId;
        return this;
    }
    public QQDialog setMessage(CharSequence text)
	{
        this.messageText = text;
        return this;
    }
    public QQDialog setMessage(int resourcesId)
	{
        this.messageId = resourcesId;
        return this;
    }
    public QQDialog setView(View view)
	{
        this.mView = view;
        return this;
		
    }
	public QQDialog setView(int resourcesId)
	{
        this.mViewId = resourcesId;
        return this;
    }
    public QQDialog setEditText(CharSequence defaultText, int hintId)
	{
        this.setEditText = defaultText;
        this.hintId = hintId;
        this.isEditText = true;
        return this;
    }
    public QQDialog setEditText(int defaultTextId, CharSequence hint)
	{
        this.EditTextId = defaultTextId;
        this.hint = hint;
        this.isEditText = true;
        return this;
    }
    public QQDialog setEditText(CharSequence defaultText, CharSequence hint)
	{
        this.setEditText = defaultText;
        this.hint = hint;
        this.isEditText = true;
        return this;
    }
    public QQDialog setEditText(int defaultTextId, int hintId)
	{
        this.EditTextId = defaultTextId;
        this.hintId = hintId;
        this.isEditText = true;
        return this;
    }
    public String getEditText()
	{
		//EditText Edits = (EditText) LayoutInflater.from(mContext).inflate(R.layout.dialog_edittext, null).findViewById(R.id.Edits);
        return Edits.getText().toString();
    }
    
   
    public QQDialog setPositiveButton(int resourcesId, final View.OnClickListener listener)
	{

        this.PositiveId = resourcesId;
        this.PositiveListener = listener;
        this.isPositiveButtonShow = true;
        return this;
    }
    public QQDialog setNegativeButton(int resourcesId, final View.OnClickListener listener)
	{
        this.NegativeId = resourcesId;
        this.NegativeListener = listener;
        this.isNegativeButtonShow = true;
        return this;
    }
    public QQDialog setNeutralButton(int resourcesId, final View.OnClickListener listener)
	{
        this.NeutralId = resourcesId;
        this.NeutralListener = listener;
        this.isNeutralButtonShow = true;
        return this;
    }
    public QQDialog setPositiveButton(CharSequence text, final View.OnClickListener listener)
	{
        this.PositiveText = text;
        this.PositiveListener = listener;
        this.isPositiveButtonShow = true;
        return this;
    }
    public QQDialog setNegativeButton(CharSequence text, final View.OnClickListener listener)
	{
        this.NegativeText = text;
        this.NegativeListener = listener;
        this.isNegativeButtonShow = true;
        return this;
    }
    public QQDialog setNeutralButton(CharSequence text, final View.OnClickListener listener)
	{
        this.NeutralText = text;
        this.NeutralListener = listener;
        this.isNeutralButtonShow = true;
        return this;
    }
	
    private class OnDialogButtonClickListener implements OnClickListener
    {

        @Override
        public void onClick(View p1)
        {
            // TODO: Implement this method
            mDialog.dismiss();
        }
    }
}

