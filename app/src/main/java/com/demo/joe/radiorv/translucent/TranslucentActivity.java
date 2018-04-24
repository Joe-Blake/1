package com.demo.joe.radiorv.translucent;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.joe.radiorv.R;
import com.tencent.tinker.android.dex.Code;

public class TranslucentActivity extends Activity {

    private EditText code1;
    private EditText code2;
    private EditText code3;
    private EditText code4;
    //用户输入的验证码
    private String mCode;


    private TextView title;
    private RelativeLayout back;

    private TextView get;
    private RelativeLayout number;
    private RelativeLayout code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transslucent);

        title = (TextView) findViewById(R.id.title);
        back = (RelativeLayout) findViewById(R.id.back);

        number = (RelativeLayout) findViewById(R.id.input_number);
        code = (RelativeLayout) findViewById(R.id.code);
        get = (TextView) findViewById(R.id.get_code);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAnimation();
                back.setVisibility(View.VISIBLE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAnimation();
                back.setVisibility(View.GONE);
            }
        });

        code1 = (EditText) findViewById(R.id.verification_code1);
        code2 = (EditText) findViewById(R.id.verification_code2);
        code3 = (EditText) findViewById(R.id.verification_code3);
        code4 = (EditText) findViewById(R.id.verification_code4);

        code1.setFocusable(true);
        code1.setFocusableInTouchMode(true);
        code2.setFocusable(true);
        code2.setFocusableInTouchMode(true);
        code3.setFocusable(true);
        code3.setFocusableInTouchMode(true);
        code4.setFocusable(true);
        code4.setFocusableInTouchMode(true);

        code2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    String content = code2.getText().toString();
                    int length = content.length();
                    if (length == 0) {
                        code1.requestFocus();
                        code1.setText("");
                    } else {
                        code2.setText("");
                    }
                }
                return false;
            }
        });
        code3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    String content = code3.getText().toString();
                    int length = content.length();
                    if (length == 0) {
                        code2.requestFocus();
                        code2.setText("");
                    } else {
                        code3.setText("");
                    }
                }
                return false;
            }
        });
        code4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    String content = code4.getText().toString();
                    int length = content.length();
                    if (length == 0) {
                        code3.requestFocus();
                        code3.setText("");
                    } else {
                        code4.setText("");
                    }
                }
                return false;
            }
        });

        TextWatcher watcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (code2.getText().length() == 1 && code3.getText().length() == 1 && code4.getText().length() ==
                            1) {
                        mCode = code1.getText().toString() + code2.getText().toString() + code3.getText().toString()
                                + code4.getText().toString();
                        Toast.makeText(TranslucentActivity.this, "输入完成了", Toast.LENGTH_SHORT).show();
                    } else {
                        code2.requestFocus();
                    }
                }
            }
        };
        TextWatcher watcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (code1.getText().length() == 1 && code3.getText().length() == 1 && code4.getText().length() == 1) {
                        mCode = code1.getText().toString() + code2.getText().toString() + code3.getText().toString()
                                + code4.getText().toString();
                        Toast.makeText(TranslucentActivity.this, "输入完成了", Toast.LENGTH_SHORT).show();
                    } else {
                        code3.requestFocus();
                    }
                }
            }
        };
        TextWatcher watcher3 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (code1.getText().length() == 1 && code2.getText().length() == 1 && code4.getText().length() ==
                            1) {
                        mCode = code1.getText().toString() + code2.getText().toString() + code3.getText().toString()
                                + code4.getText().toString();
                        Toast.makeText(TranslucentActivity.this, "输入完成了", Toast.LENGTH_SHORT).show();
                    } else {
                        code4.requestFocus();
                    }
                }

            }
        };

        TextWatcher watcher4 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (code1.getText().length() == 1 && code2.getText().length() == 1 && code3.getText().length() == 1) {
                        mCode = code1.getText().toString() + code2.getText().toString() + code3.getText().toString()
                                + code4.getText().toString();
                        Toast.makeText(TranslucentActivity.this, "输入完成了", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };

        code1.addTextChangedListener(watcher1);
        code2.addTextChangedListener(watcher2);
        code3.addTextChangedListener(watcher3);
        code4.addTextChangedListener(watcher4);
    }

    private void nextAnimation() {
        number.startAnimation(moveToViewLeft());
        code.setVisibility(View.VISIBLE);
        code.startAnimation(moveToViewLocationFromRight());

        code1.setFocusable(true);
        code1.setFocusableInTouchMode(true);
        code1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    private void backAnimation() {
        code.startAnimation(moveToViewReft());
        number.startAnimation(moveToViewLocationFromLeft());
    }


    private TranslateAnimation moveToViewLeft() {
        TranslateAnimation hiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        hiddenAction.setDuration(500);
        hiddenAction.setFillAfter(true);
        return hiddenAction;
    }

    private TranslateAnimation moveToViewReft() {
        TranslateAnimation hiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        hiddenAction.setDuration(500);
        hiddenAction.setFillAfter(true);
        return hiddenAction;
    }


    private TranslateAnimation moveToViewLocationFromRight() {
        TranslateAnimation showAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        showAction.setFillAfter(true);
        showAction.setDuration(500);
        return showAction;
    }

    private TranslateAnimation moveToViewLocationFromLeft() {
        TranslateAnimation showAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        showAction.setFillAfter(true);
        showAction.setDuration(500);
        return showAction;
    }
}
