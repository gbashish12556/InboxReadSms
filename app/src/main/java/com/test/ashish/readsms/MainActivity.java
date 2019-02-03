package com.test.ashish.readsms;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.view.View;
import android.widget.LinearLayout;

import android.widget.TextView;

import java.util.Date;

public class MainActivity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 101;
    LinearLayout smsListLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsListLinearLayout = findViewById(R.id.sms_list_linear_layout);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkPermission()) {
            pupulateUi();
        }
    }

    public void pupulateUi(){

        if(smsListLinearLayout.getChildCount()>0){
            smsListLinearLayout.removeAllViews();
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout category0LineaerLayout = new LinearLayout(this);
        category0LineaerLayout.setLayoutParams(layoutParams);
        category0LineaerLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout category1LineaerLayout = new LinearLayout(this);
        category1LineaerLayout.setLayoutParams(layoutParams);
        category1LineaerLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout category2LineaerLayout = new LinearLayout(this);
        category2LineaerLayout.setLayoutParams(layoutParams);
        category2LineaerLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout category3LineaerLayout = new LinearLayout(this);
        category3LineaerLayout.setLayoutParams(layoutParams);
        category3LineaerLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout category6LineaerLayout = new LinearLayout(this);
        category6LineaerLayout.setLayoutParams(layoutParams);
        category6LineaerLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout category12LineaerLayout = new LinearLayout(this);
        category12LineaerLayout.setLayoutParams(layoutParams);
        category12LineaerLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout category24LineaerLayout = new LinearLayout(this);
        category24LineaerLayout.setLayoutParams(layoutParams);
        category24LineaerLayout.setOrientation(LinearLayout.VERTICAL);

        View smsView = getLayoutInflater().inflate(R.layout.item_sms_header, category0LineaerLayout, false);
        TextView smsTextView = smsView.findViewById(R.id.sms_category);
        smsTextView.setText("1 Hour Ago");
        category0LineaerLayout.addView(smsView);

        View smsView1 = getLayoutInflater().inflate(R.layout.item_sms_header, category0LineaerLayout, false);
        TextView smsTextView1 = smsView1.findViewById(R.id.sms_category);
        smsTextView1.setText("2 Hour Ago");
        category1LineaerLayout.addView(smsView1);

        View smsView2 = getLayoutInflater().inflate(R.layout.item_sms_header, category0LineaerLayout, false);
        TextView smsTextView2 = smsView2.findViewById(R.id.sms_category);
        smsTextView2.setText("3 Hours Ago");
        category2LineaerLayout.addView(smsView2);

        View smsView3 = getLayoutInflater().inflate(R.layout.item_sms_header, category0LineaerLayout, false);
        TextView smsTextView3 = smsView3.findViewById(R.id.sms_category);
        smsTextView3.setText("6 Hours Ago");
        category3LineaerLayout.addView(smsView3);

        View smsView4 = getLayoutInflater().inflate(R.layout.item_sms_header, category0LineaerLayout, false);
        TextView smsTextView4 = smsView4.findViewById(R.id.sms_category);
        smsTextView4.setText("12 Hours Ago");
        category6LineaerLayout.addView(smsView4);

        View smsView5 = getLayoutInflater().inflate(R.layout.item_sms_header, category0LineaerLayout, false);
        TextView smsTextView5 = smsView5.findViewById(R.id.sms_category);
        smsTextView5.setText("1 Day Ago");
        category12LineaerLayout.addView(smsView5);


        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c =null;
        try {
            c = getContentResolver().query(uri, null, null, null, null);
            Date date = new Date();
            long timeNow = date.getTime();

            if (c.moveToFirst()) {

                for (int i = 0; i < c.getCount(); i++) {
                    View miniServiceView = getLayoutInflater().inflate(R.layout.item_sms_content, category0LineaerLayout, false);
                    TextView messageTextView = miniServiceView.findViewById(R.id.message);
                    TextView userNameTextView = miniServiceView.findViewById(R.id.userName);

                    final String message = c.getString(c.getColumnIndexOrThrow("body")).toString();
                    final String sender_number = c.getString(c.getColumnIndexOrThrow("address")).toString();
                    messageTextView.setText(message);
                    userNameTextView.setText(sender_number);

                    String messageDate = c.getString(c.getColumnIndexOrThrow("date"));
                    Long timestamp = Long.parseLong(messageDate);
                    long timeStampDifference = timeNow - timestamp;
                    miniServiceView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, MessageDetail.class);
                            intent.putExtra("message", message);
                            intent.putExtra("sender", sender_number);
                            startActivity(intent);
                        }
                    });
                    if (timeStampDifference <= 60 * 60 * 1000) {
                        category0LineaerLayout.addView(miniServiceView);
                    } else if ((timeStampDifference > 1 * 60 * 60 * 1000) && (timeStampDifference <= 2 * 60 * 60 * 1000)) {
                        category1LineaerLayout.addView(miniServiceView);
                    } else if ((timeStampDifference > 2 * 60 * 60 * 1000) && (timeStampDifference <= 3 * 60 * 60 * 1000)) {
                        category2LineaerLayout.addView(miniServiceView);
                    } else if ((timeStampDifference > 3 * 60 * 60 * 1000) && (timeStampDifference <= 6 * 60 * 60 * 1000)) {
                        category3LineaerLayout.addView(miniServiceView);
                    } else if ((timeStampDifference > 6 * 60 * 60 * 1000) && (timeStampDifference <= 12 * 60 * 60 * 1000)) {
                        category6LineaerLayout.addView(miniServiceView);
                    } else if ((timeStampDifference > 12 * 60 * 60 * 1000) && (timeStampDifference <= 24 * 60 * 60 * 1000)) {
                        category12LineaerLayout.addView(miniServiceView);
                    }

                    c.moveToNext();
                }
            }
        }finally {
                if (c != null) {
                    c.close();
                    c = null;
                }
            }
        if(category0LineaerLayout.getChildCount()>1) {
            smsListLinearLayout.addView(category0LineaerLayout);
        }
        if(category1LineaerLayout.getChildCount()>1) {
            smsListLinearLayout.addView(category1LineaerLayout);
        }
        if(category2LineaerLayout.getChildCount()>1) {
            smsListLinearLayout.addView(category2LineaerLayout);
        }
        if(category3LineaerLayout.getChildCount()>1) {
            smsListLinearLayout.addView(category3LineaerLayout);
        }
        if(category6LineaerLayout.getChildCount()>1) {
            smsListLinearLayout.addView(category6LineaerLayout);
        }
        if(category12LineaerLayout.getChildCount()>1) {
            smsListLinearLayout.addView(category12LineaerLayout);
        }
    }

    public boolean checkPermission(){

        String permission1 = Manifest.permission.RECEIVE_SMS;
        String permission2 = Manifest.permission.READ_SMS;

        int grant1 = ContextCompat.checkSelfPermission(this, permission1);
        int grant2 = ContextCompat.checkSelfPermission(this, permission2);
        if ( grant1 != PackageManager.PERMISSION_GRANTED || grant2 != PackageManager.PERMISSION_GRANTED) {

            String[] permission_list = new String[2];
            permission_list[0] = permission1;
            permission_list[1] = permission2;
            ActivityCompat.requestPermissions(this, permission_list, MY_PERMISSIONS_REQUEST_READ_SMS);

        }else{
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pupulateUi();
                } else {
                    checkPermission();
                }
                return;
            }
        }
    }
}
