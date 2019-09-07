package com.example.rafieeweatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rafieeweatherapplication.ForecastMap.ForecastMap;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txt1 = findViewById(R.id.txt1);
        final TextView txt2 = findViewById(R.id.txt2);
        final TextView txt3 = findViewById(R.id.txt3);
        final TextView txt4 = findViewById(R.id.txt4);
        final TextView txtTemp = findViewById(R.id.txtTemp);
        final TextView txtDate = findViewById(R.id.txtDate);
        TextView txtDesc = findViewById(R.id.txtDesc);

        final DrawerLayout navigation = findViewById(R.id.navigation);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation.openDrawer(Gravity.LEFT);
            }
        });

        Button btnWeatherSearch = findViewById(R.id.btnWeatherSearch);
        btnWeatherSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Weather.class);
                startActivity(i);
            }
        });

        String address = "http://api.openweathermap.org/data/2.5/forecast?q=Tehran&APPID=32c4b53a85e7c06775ac7d0ce0f11d2c";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                ForecastMap forecast = gson.fromJson(response.toString(),ForecastMap.class);

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject index = array.getJSONObject(0);
                    String Date = index.getString("dt_txt");
                    txtDate.setText(Date);
                    JSONObject main = index.getJSONObject("main");
                    String Temp = main.getString("temp");
                    Double TEMP = Double.valueOf(Temp)-273;
                    Long RoundTemp = Math.round(TEMP);
                    txtTemp.setText(RoundTemp+"℃");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject index = array.getJSONObject(6);
                    String Date = index.getString("dt_txt");
                    JSONObject main = index.getJSONObject("main");
                    String Temp = main.getString("temp");
                    Double TEMP = Double.valueOf(Temp)-273;
                    Long RoundTemp = Math.round(TEMP);
                    txt1.setText(Date+"           "+RoundTemp+"℃");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject index = array.getJSONObject(14);
                    String Date = index.getString("dt_txt");
                    JSONObject main = index.getJSONObject("main");
                    String Temp = main.getString("temp");
                    Double TEMP = Double.valueOf(Temp)-273;
                    Long RoundTemp = Math.round(TEMP);
                    txt2.setText(Date+"           "+RoundTemp+"℃");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject index = array.getJSONObject(22);
                    String Date = index.getString("dt_txt");
                    JSONObject main = index.getJSONObject("main");
                    String Temp = main.getString("temp");
                    Double TEMP = Double.valueOf(Temp)-273;
                    Long RoundTemp = Math.round(TEMP);
                    txt3.setText(Date+"           "+RoundTemp+"℃");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject index = array.getJSONObject(30);
                    String Date = index.getString("dt_txt");
                    JSONObject main = index.getJSONObject("main");
                    String Temp = main.getString("temp");
                    Double TEMP = Double.valueOf(Temp)-273;
                    Long RoundTemp = Math.round(TEMP);
                    txt4.setText(Date+"           "+RoundTemp+"℃");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
