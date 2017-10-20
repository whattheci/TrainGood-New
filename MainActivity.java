package com.example.traingood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import swapi.SWApiService;
import swapi.SubwayInfo;

public class MainActivity extends AppCompatActivity {

    RequestQueue q;
    Button confirmBtn;
    Button trainBtn;
    EditText trainEdit;
    EditText confirmEdit;
    EditText destEdit;
    Button destBtn;

    List<String> trainNoList;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        q = Volley.newRequestQueue(this);

        mContext = this;

        confirmBtn = (Button) findViewById(R.id.confirm);
        trainBtn = (Button) findViewById(R.id.trainBtn);
        confirmEdit = (EditText) findViewById(R.id.confirmEdit);
        trainEdit = (EditText) findViewById(R.id.trainEdit);
        destEdit = (EditText) findViewById(R.id.destinationEdit);

        destBtn = (Button) findViewById(R.id.destinationBtn);
        destBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppProperties.myDestination = destEdit.getText().toString();
                Toast.makeText(MainActivity.this, "등록되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        trainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRetrofit(trainEdit.getText().toString());
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppProperties.myDestination == null) {
                    Toast.makeText(mContext, "목적지를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (trainNoList.contains(confirmEdit.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, TrainActivity.class);
                    intent.putExtra("TrainNum", confirmEdit.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "열차번호가 일치하지 않습니다. 탑승하려는 열차번호와 일치하는지 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initRetrofit(final String stationName) {
        SWApiService service = SWApiService.retrofit.create(SWApiService.class);
        Call<SubwayInfo> call = service.subwayInfos("575173486773656834354f4f5a5768", "realtimeStationArrival", stationName);
        call.enqueue(new Callback<SubwayInfo>() {
            @Override
            public void onResponse(Call<SubwayInfo> call, retrofit2.Response<SubwayInfo> response) {
                if (response == null || response.body() == null || response.body().getRealtimeArrivalList() == null) {
                    Toast.makeText(mContext, "역명을 잘못 입력하셨습니다. 다시 조회해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (SubwayInfo.RealtimeArrivalList obj : response.body().getRealtimeArrivalList()) {
                    trainNoList.add(obj.getBtrainNo());
                }
                Log.e("TESTDATA", trainNoList.toString());
                Toast.makeText(mContext, "열차정보가 조회되었습니다. 전광판의 도착 열차편의 열차번호를 입력해주세요", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SubwayInfo> call, Throwable t) {
                Log.d("ERROR", "onFailure: ");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppProperties.userID == null || AppProperties.userID.equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        AppProperties.myDestination = null;
        trainNoList = new ArrayList<>();
    }
}
