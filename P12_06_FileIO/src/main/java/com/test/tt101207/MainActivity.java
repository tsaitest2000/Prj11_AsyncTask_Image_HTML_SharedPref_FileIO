package com.test.tt101207;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      File file1 = getFilesDir();
      Log.d("FILE", file1.toString());

      File file2 = getCacheDir();
      Log.d("FILE", file2.toString());

      File file3 = getExternalCacheDir();
      Log.d("FILE", file3.toString());

      File file4 = Environment.getExternalStorageDirectory();
      Log.d("FILE", file4.toString());

      InputStream is = getResources().openRawResource(R.raw.mydata);
   }

}
