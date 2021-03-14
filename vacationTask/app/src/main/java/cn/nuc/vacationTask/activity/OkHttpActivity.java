package cn.nuc.vacationTask.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cn.nuc.vacationTask.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpActivity extends AppCompatActivity {

    private TextView tvResponse;
    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        tvResponse = findViewById(R.id.tv_response_ok);

    }

    public void sendRequest(View view){
        sendRequestWithOkHttp("https://www.baidu.com", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                showResponse("访问失败!");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                showResponse(response.body().string());
            }
        });
    }

    /**
     * 使用okhttp访问网络
     */
    private void sendRequestWithOkHttp(final String address, final Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResponse.setText(response);
            }
        });
    }

}
