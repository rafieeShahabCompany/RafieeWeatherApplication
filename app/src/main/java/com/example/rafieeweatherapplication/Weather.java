package com.example.rafieeweatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rafieeweatherapplication.WeatherMap.WeatherMap;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        final EditText edtName = findViewById(R.id.edtName);
        final TextView txtName = findViewById(R.id.txtName);
        final TextView txtTemp = findViewById(R.id.txtTemp);
        final TextView txtMax = findViewById(R.id.txtMax);
        final TextView txtMin = findViewById(R.id.txtMin);
        final TextView txtDes = findViewById(R.id.txtDes);


        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String Name = edtName.getText().toString();
                 txtName.setText(Name);

                 String address = "http://api.openweathermap.org/data/2.5/weather?q="+Name+"&APPID=32c4b53a85e7c06775ac7d0ce0f11d2c";
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(address,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Gson gson = new Gson();
                        WeatherMap weather = gson.fromJson(response.toString(), WeatherMap.class);

                        Double Temp = weather.getMain().getTemp();
                        Double TEMP = Double.valueOf(Temp)-273;
                        Long RoundTemp = Math.round(TEMP);
                        txtTemp.setText(RoundTemp+"℃");

                        Double Max = weather.getMain().getTempMax();
                        Double MAX = Double.valueOf(Max)-273;
                        Long RoundMax = Math.round(MAX);
                        txtMax.setText(RoundMax+"℃");

                        Double Min = weather.getMain().getTempMin();
                        Double MIN = Double.valueOf(Min)-273;
                        Long RoundMin = Math.round(MIN);
                        txtMin.setText(RoundMin+"℃");

                        try {
                            JSONObject object = new JSONObject(response.toString());
                            JSONArray array = new JSONArray(object.getString("weather"));
                            JSONObject Weather = array.getJSONObject(0);
                            String Description = Weather.getString("description");
                            txtDes.setText(Description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RecyclerView recycler = findViewById(R.id.recycler);
                        List<String> list = new ArrayList<>();
                        list.add(Name);
                        RecyclerAdapter adapter = new RecyclerAdapter(list);
                        recycler.setAdapter(adapter);
                        recycler.setLayoutManager(new LinearLayoutManager(Weather.this,RecyclerView.HORIZONTAL,false));


                    }




                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }





                });

            }
        });

    }
}
