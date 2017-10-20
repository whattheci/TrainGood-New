package com.example.traingood;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.traingood.AppProperties.OCCUPIEDLIST;
import static com.example.traingood.AppProperties.ROOT;

public class TrainActivity extends AppCompatActivity {

    String trainNo;
    ListView listView;
    SeatListAdapter adapter;
    List<SeatInfo> occupiedList;

    RequestQueue q;
    Context mContext;

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        mContext = this;
        q = Volley.newRequestQueue(this);
        listView = (ListView) findViewById(R.id.seatListView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onResume();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        occupiedList = new ArrayList<>();
        trainNo = getIntent().getExtras().getString("TrainNum");
//        trainNo = "9234"; // TODO: TESTCODE

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ROOT + OCCUPIEDLIST + "?TrainNum=" + trainNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success") == false) {
                        Log.e("TESTLOG", "OccupiedList 조회실패");
                        return;
                    }

                    for (int i = 1; i <= 27; i++) {
                        occupiedList.add(new SeatInfo((i * 2) - 1, i * 2, trainNo, "", "", "", ""));
                    }

                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = new JSONObject(jsonArray.get(i).toString());
                        int targetNo = obj.getInt("ChairNumber");
                        String destination = obj.getString("Destination");

                        SeatInfo info = occupiedList.get(((targetNo + 1) / 2) - 1);
                        info.setTrainNo(trainNo);

                        if (targetNo % 2 == 1) {
                            info.setDestination(destination);
                            info.setOccupied(true);
                            info.setOccupiedUserID(obj.getString("OccupiedUser"));
                            if (!obj.isNull("ReservationUser")) {
                                info.setReserved(true);
                                info.setReservedUserID(obj.getString("ReservationUser"));
                            } else {
                                info.setReserved(false);
                            }
                        } else {
                            info.setDestination2(destination);
                            info.setOccupied2(true);
                            info.setOccupiedUserID2(obj.getString("OccupiedUser"));
                            if (!obj.isNull("ReservationUser")) {
                                info.setReserved2(true);
                                info.setReservedUserID2(obj.getString("ReservationUser"));
                            } else {
                                info.setReserved2(false);
                            }
                        }
                    }

                    adapter = new SeatListAdapter(mContext, occupiedList);
                    listView.setAdapter(adapter);

//                    for (SeatInfo info : occupiedList) {
//                        System.err.println(info.chairNo + ":" + info.isOccupied());
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEYERROR", "OCCUPIEDLIST");
                return;
            }
        });

        q.add(stringRequest);
    }

    public void listViewRefresh() {
        onResume();
    }
}
