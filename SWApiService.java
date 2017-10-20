package swapi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 김세현 on 2017-04-29.
 */

public interface  SWApiService {//http://swopenapi.seoul.go.kr/api/subway/575173486773656834354f4f5a5768/xml/realtimeStationArrival/0/100/서울
    @GET("api/subway/{token}/json/{apiName}/0/100/{stationName}")
    Call<SubwayInfo> subwayInfos(
            @Path("token")
                    String token,
            @Path("apiName")
                    String apiName,
            @Path("stationName")
                    String stationName
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://swopenapi.seoul.go.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
