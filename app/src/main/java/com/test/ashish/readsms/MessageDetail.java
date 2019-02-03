package com.test.ashish.readsms;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageDetail extends Activity {

    private TextView sendNameTextView;
    private TextView messageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        sendNameTextView = findViewById(R.id.sender_name);
        messageTextView  = findViewById(R.id.message);
        if(getIntent() != null){
            String sender = getIntent().getStringExtra("sender");
            String message = getIntent().getStringExtra("message");
            sendNameTextView.setText(sender);
            messageTextView.setText(message);
        }
    }
}
