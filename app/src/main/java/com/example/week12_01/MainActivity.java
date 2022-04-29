package com.example.week12_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Gettir(View v){
        //Toast.makeText(this,"Geliyor",Toast.LENGTH_LONG).show();

        GetJson getir = new GetJson();
        try {
            String myJson = getir.execute("https://api.openweathermap.org/data/2.5/weather?q=Kastamonu&appid=152aaeaa0ad73248f0ed30cf1a70cbf1&lang=tr&units=metric").get();
            Log.d("MyJSON",myJson);

            JSONObject obj = new JSONObject(myJson);
            String weather = obj.getString("weather");
            String main = obj.getString("main");
            JSONObject mw = new JSONObject(main);
            JSONArray wa = new JSONArray(weather);

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

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}