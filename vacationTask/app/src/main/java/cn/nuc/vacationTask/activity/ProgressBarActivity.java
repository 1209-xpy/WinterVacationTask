package cn.nuc.vacationTask.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cn.nuc.vacationTask.R;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar mPg;
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        mPg = findViewById(R.id.progress_bar);
        mBtnStart = findViewById(R.id.btn_start);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (mPg.getProgress() < 100) {
                handler.postDelayed(runnable, 500);
            } else {
                Toast.makeText(ProgressBarActivity.this, "  加载完成  ", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mPg.setProgress(mPg.getProgress() + 10);
            handler.sendEmptyMessage(0);
        }
    };
}
