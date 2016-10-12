package com.test.tt101203;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

   ImageView img;
   TextView tv;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      img = (ImageView) findViewById(R.id.imageView);
      tv = (TextView) findViewById(R.id.textView);
//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//        ImageRequest request = new ImageRequest("https://farm3.staticflickr.com/2194/1893036291_2ec9abc7f4_o_d.jpg",
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        img.setImageBitmap(response);
//                    }
//                }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565 , new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("NET", error.toString());
//            }
//        });
//        queue.add(request);
//        queue.start();
      MyTask c = new MyTask();
      c.execute("https://farm3.staticflickr.com/2194/1893036291_2ec9abc7f4_o_d.jpg");

   }

   class MyTask extends AsyncTask<String, Integer, Bitmap> {

      private Bitmap bitmap = null;
      private InputStream inputStream = null;
      private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      @Override
      protected Bitmap doInBackground(String... params) {
         try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            inputStream = conn.getInputStream();
            double fullSize = conn.getContentLength(); // 總長度
            int sumSize = 0;
            byte[] buffer = new byte[64]; // buffer ( 每次讀取長度 )
            int readSize = 0; // 當下讀取長度
            double sum = 0;
            while ((readSize = inputStream.read(buffer)) != -1) {
               outputStream.write(buffer, 0, readSize);
               sumSize += readSize;
               sum = (sumSize / fullSize) * 100; // 累計讀取進度

               publishProgress((int) sum);
            }
            // 將 outputStream 轉 byte[] 再轉 Bitmap
            byte[] result = outputStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {
               inputStream.close();
               outputStream.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
         return bitmap;
      }

      @Override
      protected void onProgressUpdate(Integer... values) {
         super.onProgressUpdate(values);
         tv.setText(String.valueOf(values[0]));
      }

      @Override
      protected void onPostExecute(Bitmap bitmap) {
         super.onPostExecute(bitmap);
         img.setImageBitmap(bitmap);
      }
   }

}
