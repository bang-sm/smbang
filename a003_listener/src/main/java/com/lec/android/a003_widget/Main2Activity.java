package com.lec.android.a003_widget;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity{
    EditText resultValue;
    EditText hiddenNum;
    Button btnPlus,btnMius,btnEq,btnC,btnPer,btnBy;
    private double num1=0;  //첫번째 값
    private double num2=0; //두번째 값
    private double lastNum=0;

    int giHo = 0;   // 1 +    2 -    3 *    4 /
    int giHoCount = 0;   //  기호 누른횟수
    boolean giHoBoolean = false;   //처음엔
    boolean calContinue=false;    //더연산할지말지



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        resultValue=findViewById(R.id.resultValue);
        hiddenNum=findViewById(R.id.hiddenNum);

        btnPlus=findViewById(R.id.btnPlus);
        btnMius=findViewById(R.id.btnMius);
        btnC=findViewById(R.id.btnC);
        btnPer=findViewById(R.id.btnPer);
        btnEq=findViewById(R.id.btnEq);
        btnBy=findViewById(R.id.btnBy);

        btnMius.setOnClickListener(btnListener);
        btnC.setOnClickListener(btnListener);
        btnEq.setOnClickListener(btnListener);
        btnPer.setOnClickListener(btnListener);
        btnPlus.setOnClickListener(btnListener);
        btnBy.setOnClickListener(btnListener);
    }
    Button.OnClickListener btnListener=new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            Log.d("myaa","re"+resultValue.getText().toString().length());
            if(resultValue.getText().toString().length()==0){
                Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                return;
            }
            switch (v.getId()) {
                case R.id.btnC:
                    num1=0;
                    num2=0;
                    giHo=0;
                    giHoCount=0;
                    hiddenNum.setText("");
                    resultValue.setText("");
                    calContinue=false;
                    lastNum=0;
                    break;
                case R.id.btnPlus:
                    if (giHoBoolean  == false || calContinue==true)
                    {
                        Log.d("myapp","giHoBoolean");
                        if(giHo==0){
                            Log.d("myapp","들어옴 기호0");
                            num2=Double.parseDouble(hiddenNum.getText().toString());
                            hiddenNum.setText("");
                            resultValue.setText(num2+"");
                            Log.d("myapp","num2: "+num2);
                        }
                        if(giHo==1){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            hiddenNum.setText("");
                            resultValue.setText("");
                            num2=num2+num1;
                            Log.d("myapp","num2: "+num2);
                            Log.d("myapp","giHoCount: "+giHoCount);
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==2){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2-num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==3){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2*num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==4){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2/num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        giHoBoolean = true;   // 눌렀으니 true
                        giHoCount ++;
                        giHo = 1;  // 기호와 매칭

                    }
                    break;
                case R.id.btnMius:

                    if (giHoBoolean  == false || calContinue==true)
                    {
                        if(giHo==0){
                            Log.d("myapp","들어옴 기호0");
                            num2=Double.parseDouble(hiddenNum.getText().toString());
                            hiddenNum.setText("");
                            resultValue.setText(num2+"");
                            Log.d("myapp","num2: "+num2);
                        }

                        if(giHo==1){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            hiddenNum.setText("");
                            resultValue.setText("");
                            num2=num2+num1;
                            Log.d("myapp","num2: "+num2);
                            Log.d("myapp","giHoCount: "+giHoCount);
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==2){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            if (num2>num1) {
                                num2=num2-num1;
                            }else{
                                num2=-(num1-num2);
                            }
                            Log.d("myappgiho2","num2: "+num2);
                            Log.d("myappgiho2","num1: "+num1);
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==3){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  // 앞에 입력된값
                            num2=num1*num2;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==4){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2/num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        giHoBoolean = true;   // 눌렀으니 true
                        giHoCount ++;
                        giHo = 2;  // 기호와 매칭

                    }
                    break;
                case R.id.btnBy:
                    if (giHoBoolean  == false || calContinue==true)
                    {
                        if(giHo==0){
                            Log.d("myapp","들어옴 기호0");
                            num2=Double.parseDouble(hiddenNum.getText().toString());
                            hiddenNum.setText("");
                            resultValue.setText(num2+"");
                            Log.d("myapp","num2: "+num2);
                        }

                        if(giHo==1){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            hiddenNum.setText("");
                            resultValue.setText("");
                            num2=num2+num1;
                            Log.d("myapp","num2: "+num2);
                            Log.d("myapp","giHoCount: "+giHoCount);
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==2){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2-num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==3){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2*num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==4){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2/num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        giHoBoolean = true;   // 눌렀으니 true
                        giHoCount ++;
                        giHo = 3;  // 기호와 매칭

                    }
                    break;
                case R.id.btnPer:
                    if (giHoBoolean  ==  false|| calContinue==true)
                    {
                        if(giHo==0){
                            Log.d("myapp","들어옴 기호0");
                            num2=Double.parseDouble(hiddenNum.getText().toString());
                            hiddenNum.setText("");
                            resultValue.setText(num2+"");
                            Log.d("myapp","num2: "+num2);
                        }

                        if(giHo==1){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            hiddenNum.setText("");
                            resultValue.setText("");
                            num2=num2+num1;
                            Log.d("myapp","num2: "+num2);
                            Log.d("myapp","giHoCount: "+giHoCount);
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==2){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2-num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==3){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2*num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        if(giHo==4){
                            num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                            num2=num2/num1;
                            resultValue.setText("");
                            hiddenNum.setText("");
                            if(giHoCount>=1){
                                resultValue.setText(num2+"");
                            }
                        }
                        giHoBoolean = true;   // 눌렀으니 true
                        giHoCount ++;
                        giHo = 4;  // 기호와 매칭

                    }
                    break;
               case R.id.btnEq:
                   if (giHoBoolean  == false)
                   {
                       if(giHo==0){
                           num2=Double.parseDouble(hiddenNum.getText().toString());
                           hiddenNum.setText("");
                           resultValue.setText(num2+"");
                           Log.d("myapp","num2: "+num2);
                       }
                       if(giHo==1){
                           num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                           hiddenNum.setText("");
                           resultValue.setText("");
                           num2=num2+num1;
                           Log.d("myapp","num2: "+num2);
                           Log.d("myapp","giHoCount: "+giHoCount);
                           if(giHoCount>=1){
                               resultValue.setText(num2+"");
                           }
                       }
                       if(giHo==2){
                           num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                           num2=num2-num1;
                           resultValue.setText("");
                           hiddenNum.setText("");
                           if(giHoCount>1){
                               resultValue.setText(num2+"");
                           }
                       }
                       if(giHo==3){
                           num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                           num2=num2*num1;
                           resultValue.setText("");
                           if(giHoCount>=1){
                               resultValue.setText(num2+"");
                           }
                       }
                       if(giHo==4){
                           num1=Double.parseDouble(hiddenNum.getText().toString());  //앞에 입력된값
                           num2=num2/num1;
                           resultValue.setText("");
                           hiddenNum.setText("");
                           if(giHoCount>=1){
                               resultValue.setText(num2+"");
                           }
                       }
                       giHoBoolean=true;
                       calContinue=true;
                       giHoCount ++;
                       giHo = 0;  // 기호와 매칭
                       hiddenNum.setText(num2+"");
                   }
                   break;
            }
        }
    };
    public void numberPadClick(View v){
        giHoBoolean=false;
        switch(v.getId()){
            case R.id.btn0 :
                if (hiddenNum.getText().length()<1) {
                    hiddenNum.setText("");
                    resultValue.setText("");
                }else{
                    hiddenNum.setText(hiddenNum.getText().toString() + 0);
                    resultValue.setText(hiddenNum.getText().toString());
                }
                break;
            case R.id.btn1 :
                hiddenNum.setText(hiddenNum.getText().toString() + 1);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn2 :
                hiddenNum.setText(hiddenNum.getText().toString() + 2);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn3 :
                hiddenNum.setText(hiddenNum.getText().toString() + 3);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn4 :
                hiddenNum.setText(hiddenNum.getText().toString() + 4);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn5 :
                hiddenNum.setText(hiddenNum.getText().toString() + 5);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn6 :
                hiddenNum.setText(hiddenNum.getText().toString() + 6);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn7 :
                hiddenNum.setText(hiddenNum.getText().toString() + 7);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn8 :
                hiddenNum.setText(hiddenNum.getText().toString() + 8);
                resultValue.setText(hiddenNum.getText().toString());
                break;
            case R.id.btn9 :
                hiddenNum.setText(hiddenNum.getText().toString() + 9);
                resultValue.setText(hiddenNum.getText().toString());
                break;
        }
    }

}

