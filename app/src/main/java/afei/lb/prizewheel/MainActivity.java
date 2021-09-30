package afei.lb.prizewheel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Pointer mPointer;
    Thread thread;
    boolean mFlag;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFlag = !mFlag;
                if(mFlag){
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(mFlag) {
                                try {
                                    i+=10;
                                    mHandler.sendEmptyMessage(9527);
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    thread.start();
                }
            }
        });
        Wheel wheel = findViewById(R.id.wheel);
        wheel.setPrize("防窥膜","啥都没有","蛋糕","巴掌","谢谢惠顾");
        mPointer = findViewById(R.id.pointer);
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 9527){
                mPointer.setDegrees(i);
            }
        }
    };


}