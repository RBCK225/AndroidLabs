package com.example.robert.androidlabs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "Weather App";

    private ImageView weatherImage;
    private TextView currentTemp;
    private TextView minTemp;
    private TextView maxTemp;
    private ProgressBar weatherProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        weatherImage = (ImageView)findViewById(R.id.weatherImage);
        currentTemp = (TextView) findViewById(R.id.textView4);
        minTemp = (TextView) findViewById(R.id.textView5);
        maxTemp = (TextView) findViewById(R.id.textView6);

        weatherProgress = (ProgressBar) findViewById(R.id.progressBar);
        weatherProgress.setVisibility(View.VISIBLE);

        new ForecastQuery().execute();
    }

    private class ForecastQuery extends AsyncTask<String, Integer, String>{

        public String minTempValue;
        public String maxTempValue;
        public String currentTempValue;
        public  String currentWeather;
        public Bitmap bitmap;

        @Override
        protected String doInBackground(String ...args) {

            InputStream iStream;

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000); //in milliseconds
                conn.setConnectTimeout(15000); //in millisenconds
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                Log.d(ACTIVITY_NAME, "connecting with weather api..");
                conn.connect();

                Log.d(ACTIVITY_NAME, "reading");
                iStream = conn.getInputStream();

                Log.d(ACTIVITY_NAME, "stream: " + iStream);

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(iStream, null);
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType != XmlPullParser.START_TAG) {
                        eventType = xpp.next();
                        continue;
                    } else {
                        if (xpp.getName().equals("temperature")) {
                            currentTempValue = xpp.getAttributeValue(null, "value");
                            publishProgress(25);
                            minTempValue = xpp.getAttributeValue(null, "min");
                            publishProgress(50);
                            maxTempValue = xpp.getAttributeValue(null, "max");
                            publishProgress(75);
                        } else if (xpp.getName().equals("weather")) {
                            currentWeather = xpp.getAttributeValue(null, "icon");
                        }
                        eventType = xpp.next();
                    }
                }
                conn.disconnect();

                if(fileExist(currentWeather + ".png")){
                    Log.i(ACTIVITY_NAME, "Weather image exists");
                    File file = getBaseContext().getFileStreamPath(currentWeather + ".png");
                    FileInputStream fis = new FileInputStream(file);
                    bitmap = BitmapFactory.decodeStream(fis);
                }else {
                    Log.i(ACTIVITY_NAME, "Weather image does not exist");

                    URL imageLink = new URL("http://openweathermap.org/img/w/" + currentWeather + ".png");
                    conn = (HttpURLConnection) imageLink.openConnection();
                    conn.connect();
                    iStream = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(iStream);

                    FileOutputStream fileOut = openFileOutput(currentWeather + ".png", Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOut);
                    fileOut.flush();
                    fileOut.close();
                    conn.disconnect();
                }
                publishProgress(100);

            }
            catch(FileNotFoundException fne){
                Log.e(ACTIVITY_NAME, fne.getMessage());
            }
            catch (XmlPullParserException parserException){
                Log.e(ACTIVITY_NAME, parserException.getMessage());
            }
            catch(IOException e){
                Log.e(ACTIVITY_NAME, e.getMessage());
            }

            return null;
        }

        public void onProgressUpdate(Integer ...f){
            weatherProgress.setVisibility(View.VISIBLE);
            weatherProgress.setProgress(f[0]);
        }

        public void onPostExecute(String s){
            weatherProgress.setVisibility(View.INVISIBLE);
            currentTemp.setText("Current: " + currentTempValue + "C");
            minTemp.setText("Min: " + minTempValue + "C");
            maxTemp.setText("Max: " + maxTempValue + "C");
            weatherImage.setImageBitmap(bitmap);
        }
        public boolean fileExist(String name){
            File file = getBaseContext().getFileStreamPath(name);
            return file.exists();
        }

    }


}
