package com.test.tt101205;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   SharedPreferences sp;
   TextView tv;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      tv = (TextView) findViewById(R.id.textView);
      sp = getSharedPreferences("mydata", MODE_PRIVATE);

      String username = sp.getString("username", "user");
      tv.setText(username);
   }

   public void clickWrite(View v) {
      EditText ed;
      ed = (EditText) findViewById(R.id.editText);
      SharedPreferences.Editor editor = sp.edit();
      editor.putString("username", ed.getText().toString());
      editor.commit();
   }

}
