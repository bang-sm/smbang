package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main4Activity extends AppCompatActivity {
    String imgUrl = "https://developer.android.com/studio/images/studio-icon-stable.png";
    ImageView iv1;
    TextView tvUrl;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        iv1=findViewById(R.id.iv1);
        tvUrl=findViewById(R.id.tvUrl);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(imgUrl);
                    InputStream inputStream =url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Log.d("myaa","들어옴스레드");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv1.setImageBitmap(bitmap);
                            tvUrl.setText(imgUrl);
                        }
                    });
                    //iv1.setImageBitmap(bitmap);
                    //핸들러없이 사용하면 에러
                    //android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                }catch (IOException e){
                    e.getStackTrace();
                }
            }
        });
        thread.start();


    }
}
