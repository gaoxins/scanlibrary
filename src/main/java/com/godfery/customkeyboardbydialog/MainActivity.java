package com.godfery.customkeyboardbydialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.godfery.R;
import com.godfery.Zxing.CaptureActivity;

public class MainActivity extends Activity {
    private EditText cstEdittext;
    private Context mContext;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardbydialog);
        mContext = this;
        mActivity = this;
        cstEdittext = (EditText) findViewById(R.id.cst_editText);
        findViewById(R.id.cst_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CaptureActivity.class);
                startActivityForResult(intent, 100);
            }
        });
//        cstEdittext.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                EditText et = (EditText) v;
//                int inType = et.getInputType(); // back up the input type
//                getCursorIndex(et, inType, v, Build.VERSION.SDK_INT);
//                cstEdittext.onTouchEvent(event);
//                cstEdittext.setInputType(inType);
//                return true;
//            }
//        });



        EditListener(cstEdittext);
        findViewById(R.id.cst_imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new KeyboardUtil(mContext, mActivity, cstEdittext).showKeyboard();
                int inputType = cstEdittext.getInputType();
                cstEdittext.setInputType(InputType.TYPE_NULL);
                new KeyboardUtil(mContext, mActivity, cstEdittext).showKeyboard();
                cstEdittext.setInputType(inputType);
            }
        });

    }

    protected void onActivityResult(int arg0, int arg1, Intent data) {
        super.onActivityResult(arg0, arg1, data);
        /**
         * 拿到解析完成的字符串
         */
        if (data != null) {
            cstEdittext.setText(data.getStringExtra("result"));
        }
    }
    public void EditListener(EditText edit){
        edit.addTextChangedListener(new CustomEditLintener(edit));

    }


}
