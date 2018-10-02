package com.demo.joe.radiorv.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.joe.radiorv.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);
        eventBus = EventBus.builder().addIndex(new MyEventBusIndex()).build();
        eventBus.register(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.post(new TextEvent("123456"));
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void change(TextEvent event) {
        mTextView.setText(event.getString());
    }
}
