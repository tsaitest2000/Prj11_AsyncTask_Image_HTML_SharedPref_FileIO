package com.test.tt101204;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
      final StringRequest request = new UTF8StringRequest("http://rate.bot.com.tw/xrt?Lang=zh-TW", new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
            // Log.d("NET", response);
            int loc1 = response.indexOf("美金 (USD)");
            int loc2 = loc1 + 368;
            String usd = response.substring(loc2, loc2 + 5);
            Log.d("NET", usd);
         }
      }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {

         }
      });
      queue.add(request);
      queue.start();
   }

}
