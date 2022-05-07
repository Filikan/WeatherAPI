package com.example.week12_01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText etCity;
    TextView tv,tv2,tv3,tv4,tv5,tv6;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "152aaeaa0ad73248f0ed30cf1a70cbf1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = (EditText) findViewById(R.id.etCity);
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tvDescription);
        tv3 = (TextView) findViewById(R.id.tvTemp);
        tv4 = (TextView) findViewById(R.id.tvFeel);
        tv5 = (TextView) findViewById(R.id.tvHumidity);
        tv6 = (TextView) findViewById(R.id.tvPressure);

    }

    public void Gettir(View v){
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        //String country = etCountry.getText().toString();

        //Toast.makeText(this,"Geliyor",Toast.LENGTH_LONG).show();

        tv.setText(etCity.getText().toString());

        GetJson getir = new GetJson();
        try {
            if (city.equals("")){
                tv.setText("City field can not be empty!");
            }else{
                tempUrl = url + "?q=" + city + "&appid=" + appid + "&lang=tr" + "&units=metric";
                /*String myJson = getir.execute("https://api.openweathermap.org/data/2.5/weather?q=Çatalzeytin" +
                        "&appid=152aaeaa0ad73248f0ed30cf1a70cbf1&lang=tr&units=metric").get();*/
                String myJson = getir.execute(tempUrl).get();
                Log.d("MyJSON",myJson);

                JSONObject obj = new JSONObject(myJson);
                String weather = obj.getString("weather");
                String main = obj.getString("main");
                JSONObject mw = new JSONObject(main);
                JSONArray wa = new JSONArray(weather);

                double temp = mw.getDouble("temp");
                tv3.setText("Temp: "+temp+" °C");

                double feelsLike = mw.getDouble("feels_like");
                tv4.setText("Feels Like: "+feelsLike+" °C");

                int humidity = mw.getInt("humidity");
                tv5.setText("Humidity: "+humidity+"%");

                int pressure = mw.getInt("pressure");
                tv6.setText("Pressure: "+pressure+"hPa");

                String description = mw.getString("main");
                tv2.setText("Description: "+description.toString());

                //String description = mw.getString("description");
                //tv2.setText("Description: "+description.toString());

                for (int i=0;i<wa.length();i++)
                {
                    JSONObject el = wa.getJSONObject(i);
                    Log.d("main",el.getString("main"));
                    Log.d("description",el.getString("description"));
                }

                Log.d("temp",mw.getString("temp"));
                Log.d("feels like",mw.getString("feels_like"));
                Log.d("pressure",mw.getString("pressure"));
                Log.d("humidity",mw.getString("humidity"));
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}