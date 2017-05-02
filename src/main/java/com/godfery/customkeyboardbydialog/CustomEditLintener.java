package com.godfery.customkeyboardbydialog;

import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by gaoxin on 2017/3/15.
 * TextWatcher 用于检测移动edittext输入光标
 */

public class CustomEditLintener implements TextWatcher {
    private static String TAG = "CustomTextWatcher";
    private EditText mEditText;
    private TextView mTextView;
    private Double aDouble;

    public CustomEditLintener(EditText e) {
        mEditText = e;
        mEditText.setOnTouchListener(onTouchListener);
    }

    public CustomEditLintener(EditText edit, TextView textView, double adou) {
        mEditText = edit;
        mTextView = textView;
        aDouble = adou;




    }


    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        Log.d(TAG, "onTextChanged: " + mTextView.getText().toString());
//        Log.d(TAG, "onTextChanged: " + aDouble);
        //  mTextView.setText(String.format("%.2f", aDouble) + " 元");
//        if (s.toString().contains(".")) {
//            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
//                s = s.toString().subSequence(0,
//                        s.toString().indexOf(".") + 3);
//                mEditText.setText(s);
//                mEditText.setSelection(s.length());
//            }
//        }
//        if (s.toString().trim().substring(0).equals(".")) {
//            s = "0" + s;
//            mEditText.setText(s);
//            mEditText.setSelection(2);
//        }
//
//        if (s.toString().startsWith("0")
//                && s.toString().trim().length() > 1) {
//            if (!s.toString().substring(1, 2).equals(".")) {
//                mEditText.setText(s.subSequence(0, 1));
//                mEditText.setSelection(1);
//                return;
//            }
//        }

    }


    public void afterTextChanged(Editable s) {
        mEditText.setSelection(s.length());
    }

    public static void getCursorIndex(EditText et, int inType, View v, int sdkInt) {
        //隐藏输入法，显示光标
        et = (EditText) v;
        inType = et.getInputType(); // back up the input type

        if (sdkInt >= 11) {
            Class<EditText> cls = EditText.class;
            try {
                Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(false);
                setShowSoftInputOnFocus.invoke(et, false);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            et.setInputType(InputType.TYPE_NULL); // disable soft input
            et.setInputType(inType);

        }
    }
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            EditText et = (EditText) v;
            int inType = et.getInputType(); // back up the input type
            getCursorIndex(et, inType, v, Build.VERSION.SDK_INT);
            mEditText.onTouchEvent(event);
            mEditText.setInputType(inType);
            return true;
        }
    };

}
