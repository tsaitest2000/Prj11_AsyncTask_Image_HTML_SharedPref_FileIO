package com.test.tt101201;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   TextView tv;
   int i = 10;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      tv = (TextView) findViewById(R.id.textView);
      tv.setText(String.valueOf(i));
      MyClass c = new MyClass();
      c.execute(5);
   }

   class MyClass extends AsyncTask<Integer, Integer, String> {

      int i;

      @Override
      protected String doInBackground(Integer... params) {
         i = params[0];
         do {
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            i--;
            publishProgress(i);
         } while (i > 0);
         return "OK";
      }

      @Override
      protected void onProgressUpdate(Integer... values) {
         tv.setText(String.valueOf(values[0]));
         super.onProgressUpdate(values);
      }

      @Override
      protected void onPostExecute(String s) {
         tv.setText(s);
         super.onPostExecute(s);
      }
   }

}
