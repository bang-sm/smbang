package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* HTTP 요청하기
   - 메니페스트 설정 하기 : android.permission.INTERNET 권한
   - <application> 에 추가 usesCleartextTraffic="true"
       HTTP와 같은 cleartext 네트워크 트래픽을 사용할지 여부를 나타내는 flag로
       이 플래그가 flase 로 되어 있으면, 플랫폼 구성 요소 (예 : HTTP 및 FTP 스택, DownloadManager, MediaPlayer)는
       일반 텍스트 트래픽 사용에 대한 앱의 요청을 거부하게 됩니다. 이 flag를 설정하게 되면 모든 cleartext 트래픽은 허용처리가 됩니다

   - URL 객체 만들기 -> HttpURLConnection 객체 만들기
       setXXX() 메소르도 Conneciton 세팅
           ex) setRequestMethod(method) :  "GET" "POST " 등의 문자열
           ex) setRequestProperty(field, value) :

   - request 는 별도의 Thread 로 진행!
   - 위 Thread에서 화면 UI 접근하려면 (당연히) Handler 사용
*/
public class MainActivity extends AppCompatActivity {

    EditText etUrl;
    TextView tvResult;
    Button btnRequest, btnClear;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = findViewById(R.id.etUrl);
        tvResult = findViewById(R.id.tvResult);

        btnRequest = findViewById(R.id.btnWebView);
        btnClear = findViewById(R.id.btnBrowser);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlStr=etUrl.getText().toString();
                //http 리퀘스트 별도의 스레드로 진행
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                });

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
            }
        });

    }
    public void request(String urlStr){
        final StringBuilder builder=new StringBuilder();
        BufferedReader reader=null;
        HttpURLConnection connection = null;

        try {
            URL url=new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            if(connection!=null){
                connection.setConnectTimeout(5000);  //시간설정 경과하면 예외발생
                connection.setUseCaches(false);
                connection.setRequestMethod("GET"); //겟방식으로
                connection.setDoInput(true); //true는 입력요 false는 출력용
                int requestCode=connection.getResponseCode(); //성공하면 200
                if (requestCode==HttpURLConnection.HTTP_OK) {
                    reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line=null;
                    while (true){
                        line=reader.readLine();
                        if(line==null){
                            break;
                        }
                        builder.append(line+"\n");
                    }
                }
            }
        }catch (IOException e){
            e.getStackTrace();
        }finally {
            try {
                if(reader!=null) reader.close();
                if(connection!=null) connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvResult.setText("응답결과"+" / "+builder.toString());
            }
        });

    }
}
