package com.example.traingood;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.traingood.AppProperties.CONQUERSEAT;
import static com.example.traingood.AppProperties.LEAVESEAT;
import static com.example.traingood.AppProperties.RESERVESEAT;
import static com.example.traingood.AppProperties.ROOT;
import static com.example.traingood.AppProperties.UNRESERVESEAT;
import static com.example.traingood.AppProperties.userID;

/**
 * Created by pacilo on 2017. 8. 31..
 */

public class SeatListAdapter extends BaseAdapter {

    Context mContext;
    List<SeatInfo> seatInfos;
    RequestQueue q;
    Integer tempNum;

    public SeatListAdapter(Context context, List<SeatInfo> list) {
        mContext = context;
        seatInfos = list;
        q = Volley.newRequestQueue(mContext);
    }

    @Override
    public int getCount() {
        return seatInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return seatInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return seatInfos.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.list_row_seat, null);
        }

        View divider = (View) v.findViewById(R.id.partition);

        switch (position) {
            case 2:
            case 9:
            case 16:
            case 23:
                divider.setVisibility(View.VISIBLE);
                break;
            default:
                divider.setVisibility(View.GONE);
                break;
        }


        TextView destinationLeft = (TextView) v.findViewById(R.id.seatDestinationLeft);
        TextView seatStateLeft = (TextView) v.findViewById(R.id.seatStateLeft);
        destinationLeft.setText(seatInfos.get(position).getDestination());
        seatStateLeft.setText(
                seatInfos.get(position).isReserved() == true ? "예약됨" :
                        seatInfos.get(position).isOccupied() != true ? "빈좌석" : "점유됨"
        );

        Button leftBtn = (Button) v.findViewById(R.id.seatBtnLeft);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                tempNum = seatInfos.get(position).getChairNo();

                if (seatInfos.get(position).isReserved() && AppProperties.userID.toString().equals(seatInfos.get(position).getReservedUserID())) {
                    // 예약 취소 루틴
                    builder.setMessage("예약을 취소하시겠습니까?");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StringRequest unReserveReq = new StringRequest(Request.Method.DELETE, ROOT + UNRESERVESEAT
                                    + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                    + "&ChairNum=" + seatInfos.get(position).getChairNo(), unReservationListener, errorListener);
                            q.add(unReserveReq);
                        }
                    });
                } else if (seatInfos.get(position).isOccupied() && AppProperties.userID.toString().equals(seatInfos.get(position).getOccupiedUserID())) {
                    // 착석 취소 루틴
                    builder.setMessage("좌석을 떠나시겠습니까?");
                    if (seatInfos.get(position).isReserved()) {
                        // PUT: 예약자가 존재
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest leaveSeatReq = new StringRequest(Request.Method.PUT, ROOT + LEAVESEAT
                                        + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                        + "&ChairNum=" + seatInfos.get(position).getChairNo(), leaveListener, errorListener);
                                q.add(leaveSeatReq);
                            }
                        });
                    } else {
                        // DELETE: 예약자가 없음
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest leaveSeatReq = new StringRequest(Request.Method.DELETE, ROOT + LEAVESEAT
                                        + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                        + "&ChairNum=" + seatInfos.get(position).getChairNo(), leaveListener, errorListener);
                                q.add(leaveSeatReq);
                            }
                        });
                    }
                } else {
                    if (seatInfos.get(position).isReserved()) {
                        builder.setMessage("이미 예약된 좌석입니다.");
                    } else if (seatInfos.get(position).isOccupied()) {
                        builder.setMessage("예약하시겠습니까?");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest reserveSeatReq = new StringRequest(Request.Method.PUT, ROOT + RESERVESEAT
                                        + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                        + "&ChairNum=" + seatInfos.get(position).getChairNo()
                                        + "&_id=" + AppProperties.userID, reservationListener, errorListener);
                                q.add(reserveSeatReq);
                            }
                        });
                    } else {
                        builder.setMessage("착석하시겠습니까?");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest conquerReq = new StringRequest(Request.Method.POST, ROOT + CONQUERSEAT, conquerListener, errorListener) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("TrainNum", seatInfos.get(position).getTrainNo());
                                        params.put("ChairNum", new Integer(seatInfos.get(position).getChairNo()).toString());
                                        params.put("_id", userID.toString());
                                        params.put("Dest", AppProperties.myDestination);
                                        return params;
                                    }
                                };
                                q.add(conquerReq);
                            }
                        });
                    }
                }

                builder.setNegativeButton("취소", null);
                builder.create().show();
            }
        });

        TextView destinationRight = (TextView) v.findViewById(R.id.seatDestinationRight);
        TextView seatStateRight = (TextView) v.findViewById(R.id.seatStateRight);
        destinationRight.setText(seatInfos.get(position).getDestination2());
        seatStateRight.setText(
                seatInfos.get(position).isReserved2() == true ? "예약됨" :
                        seatInfos.get(position).isOccupied2() != true ? "빈좌석" : "점유됨"
        );

        Button rightBtn = (Button) v.findViewById(R.id.seatBtnRight);
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                tempNum = seatInfos.get(position).getChairNo2();

                if (seatInfos.get(position).isReserved2() && AppProperties.userID.toString().equals(seatInfos.get(position).getReservedUserID2())) {
                    // 예약 취소 루틴
                    builder.setMessage("예약을 취소하시겠습니까?");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StringRequest unReserveReq = new StringRequest(Request.Method.DELETE, ROOT + UNRESERVESEAT
                                    + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                    + "&ChairNum=" + seatInfos.get(position).getChairNo2(), unReservationListener, errorListener);
                            q.add(unReserveReq);
                        }
                    });
                } else if (seatInfos.get(position).isOccupied2() && AppProperties.userID.toString().equals(seatInfos.get(position).getOccupiedUserID2())) {
                    // 착석 취소 루틴
                    builder.setMessage("좌석을 떠나시겠습니까?");
                    if (seatInfos.get(position).isReserved2()) {
                        // PUT: 예약자가 존재
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest leaveSeatReq = new StringRequest(Request.Method.PUT, ROOT + LEAVESEAT
                                        + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                        + "&ChairNum=" + seatInfos.get(position).getChairNo2(), leaveListener, errorListener);
                                q.add(leaveSeatReq);
                            }
                        });
                    } else {
                        // DELETE: 예약자가 없음
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest leaveSeatReq = new StringRequest(Request.Method.DELETE, ROOT + LEAVESEAT
                                        + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                        + "&ChairNum=" + seatInfos.get(position).getChairNo2(), leaveListener, errorListener);
                                q.add(leaveSeatReq);
                            }
                        });
                    }
                } else {
                    if (seatInfos.get(position).isReserved2()) {
                        builder.setMessage("이미 예약된 좌석입니다.");
                    } else if (seatInfos.get(position).isOccupied2()) {
                        builder.setMessage("예약하시겠습니까?");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest reserveSeatReq = new StringRequest(Request.Method.PUT, ROOT + RESERVESEAT
                                        + "?TrainNum=" + seatInfos.get(position).getTrainNo()
                                        + "&ChairNum=" + seatInfos.get(position).getChairNo2()
                                        + "&_id=" + AppProperties.userID, reservationListener, errorListener);
                                q.add(reserveSeatReq);
                            }
                        });
                    } else {
                        builder.setMessage("착석하시겠습니까?");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest conquerReq = new StringRequest(Request.Method.POST, ROOT + CONQUERSEAT, conquerListener, errorListener) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("TrainNum", seatInfos.get(position).getTrainNo());
                                        params.put("ChairNum", new Integer(seatInfos.get(position).getChairNo2()).toString());
                                        params.put("_id", userID.toString());
                                        params.put("Dest", AppProperties.myDestination);
                                        return params;
                                    }
                                };
                                q.add(conquerReq);
                            }
                        });
                    }
                }

                builder.setNegativeButton("취소", null);
                builder.create().show();
            }
        });

        return v;
    }

    Response.Listener leaveListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject json = new JSONObject(response);
                if (json.getBoolean("success") == false) {
                    Toast.makeText(mContext, "서버 오류발생! (ErrorCode: " + json.getString("message") + ")", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String msg = json.getString("message");
                    Toast.makeText(mContext, "이용해주셔서 감사합니다!", Toast.LENGTH_SHORT).show();
                    ((TrainActivity) mContext).listViewRefresh();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Response.Listener conquerListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject json = new JSONObject(response);
                if (json.getBoolean("success") == false) {
                    Toast.makeText(mContext, "착석등록에 실패하였습니다! (ErrorCode: " + json.getString("message") + ")", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(mContext, "착석하였습니다!", Toast.LENGTH_SHORT).show();
                    ((TrainActivity) mContext).listViewRefresh();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Response.Listener unReservationListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject json = new JSONObject(response);
                if (json.getBoolean("success") == false) {
                    Toast.makeText(mContext, "서버 오류발생! (ErrorCode: " + json.getString("message") + ")", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(mContext, "예약을 취소 하였습니다!", Toast.LENGTH_SHORT).show();
                    ((TrainActivity) mContext).listViewRefresh();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Response.Listener reservationListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject json = new JSONObject(response);
                if (json.getBoolean("success") == false) {
                    Toast.makeText(mContext, "예약에 실패하였습니다! (ErrorCode: " + json.getString("message") + ")", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(mContext, "예약하였습니다!", Toast.LENGTH_SHORT).show();
                    ((TrainActivity) mContext).listViewRefresh();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.err.println(error);
            Toast.makeText(mContext, "통신오류. 네트워크 상태를 확인해주세요!", Toast.LENGTH_SHORT).show();
        }
    };
}
