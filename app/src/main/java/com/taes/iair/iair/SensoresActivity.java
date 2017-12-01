package com.taes.iair.iair;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SensoresActivity extends AppCompatActivity implements SensorEventListener, LocationListener {

    final String TAG = "GPS";
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 0;
    LocationManager locationManager;
    Location loc;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;

    private SensorManager mSensorManager;
    private Sensor pressure = null;
    private Sensor temperature = null;
    private Sensor humidity = null;

    TextView txtHumidade ;
    TextView txtPressure;
    TextView txtTemperature;
    TextView txtLocation;

    Float humidade;
    Float pressao;
    Float temperatura;
    String local="";
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        txtHumidade = (TextView) findViewById(R.id.txtHum);
        txtPressure = (TextView)findViewById(R.id.txtPress);
        txtTemperature = (TextView)findViewById(R.id.txtTemp);
        txtLocation = (TextView) findViewById(R.id.textViewLocation);
        

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        final SharedPreferences prefs = this.getSharedPreferences("username", Context.MODE_PRIVATE);
        username = prefs.getString("username","N/A");
        //Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        verificarPresencaSensores();

        final Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    username = prefs.getString("username", "N/A");
                    if (txtLocation.getText().toString()=="N/A"){
                        Toast.makeText(SensoresActivity.this, "Erro ao identificar as coordenadas do local!", Toast.LENGTH_SHORT).show();
                    }else {
                        if (username.isEmpty()||username=="N/A") {
                            Toast.makeText(SensoresActivity.this, "Tem de definir um Username no Menu de Opções para poder publicar!", Toast.LENGTH_SHORT).show();
                        } else {
                            inserir(local);
                        }
                    }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_username_sensores, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_username){
            Intent it = new Intent(this, UsernameActivity.class);
            startActivity(it);
        }

        if (item.getItemId()==R.id.item_retroceder){
            setResult(RESULT_OK);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void inserir(String local){
        
        
        RequestQueue queue = Volley.newRequestQueue(SensoresActivity.this);

        String url=null;
        String localEditado = local.replace(" District","");

        switch (localEditado){
            case "Aveiro":
                url="https://api.thingspeak.com/update?api_key=7BO8XWET38ZI4409";
                break;
            case "Beja":
                url="https://api.thingspeak.com/update?api_key=8B3DOEMM72JXVSYF";
                break;
            case "Braga":
                url="https://api.thingspeak.com/update?api_key=6DBFN10XZZGJ9LGX";
                break;
            case "Bragança":
                url="https://api.thingspeak.com/update?api_key=LYHTHTR3XC2AM81R";
                break;
            case "Castelo Branco":
                url="https://api.thingspeak.com/update?api_key=S563HKMA2NRPYHEA";
                break;
            case "Coimbra":
                url="https://api.thingspeak.com/update?api_key=PHN6KUIHALPP8ZPV";
                break;
            case "Évora":
                url="https://api.thingspeak.com/update?api_key=CA99MQY18CHPIG4F";
                break;
            case "Faro":
                url="https://api.thingspeak.com/update?api_key=2IOYZBBB6M56O3EL";
                break;
            case "Guarda":
                url="https://api.thingspeak.com/update?api_key=39FOLF56WD2QTAEI";
                break;
            case "Leiria":
                url="https://api.thingspeak.com/update?api_key=K7XNKM5IS38EYMZQ";
                break;
            case "Lisboa":
                url="https://api.thingspeak.com/update?api_key=RBJC34V1T0TWCGII";
                break;
            case "Portalegre":
                url="https://api.thingspeak.com/update?api_key=QUX66JERJ9KJMMRL";
                break;
            case "Porto":
                url="https://api.thingspeak.com/update?api_key=MTC7VIH57WY8BX0D";
                break;
            case "Santarém":
                url="https://api.thingspeak.com/update?api_key=06F21KX1YRSPP1T6";
                break;
            case "Setúbal":
                url="https://api.thingspeak.com/update?api_key=IRO2XLLDLL6K1HT4";
                break;
            case "Viana do Castelo":
                url="https://api.thingspeak.com/update?api_key=SX81TQHF0O4OJB8G";
                break;
            case "Vila Real":
                url="https://api.thingspeak.com/update?api_key=XRRQQGAD15D9PU0E";
                break;
            case "Viseu":
                url="https://api.thingspeak.com/update?api_key=Z5FHMA0E5W3YWPLI";
                break;
            default:
                Toast.makeText(this, "Erro ao identificar o Local!", Toast.LENGTH_SHORT).show();
                break;
        }




        url +=" &field1=" +username+
                "&field2="+pressao.toString()
                +"&field3="+temperatura.toString()
                +"&field4="+humidade.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(SensoresActivity.this, "Publicado com sucesso! Nº registo : "+response, Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SensoresActivity.this, "Erro a inserir", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }


    public void verificarPresencaSensores(){
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            pressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        } else {
            Toast.makeText(this, "Nao existe sensor de Pressão Atmosférica no seu dispositivo", Toast.LENGTH_SHORT).show();
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            temperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        } else {
            Toast.makeText(this, "Não existe sensor de Temperatura Ambiente no seu dispositivo", Toast.LENGTH_SHORT).show();
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        } else {
            Toast.makeText(this, "Não existe sensor de Humidade no seu dispositivo", Toast.LENGTH_SHORT).show();
        }

        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        if (!isGPS && !isNetwork) {
            Log.d(TAG, "Connection off");
            showSettingsAlert();
            getLastLocation();
        } else {
            Log.d(TAG, "Connection on");
            // check permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    Log.d(TAG, "Permission requests");
                    canGetLocation = false;
                }
            }

            // get location
            getLocation();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, humidity, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
         }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temperatura = event.values[0];
            txtTemperature.setText(String.valueOf(temperatura) + " ºC");
        }
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE){
            pressao = event.values[0];
            txtPressure.setText(String.valueOf(pressao)+" hPa");
        }
        if(event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            humidade = event.values[0];
            txtHumidade.setText(String.valueOf(humidade)+" %");
        }

    }

    public void getCity(double latitude, double longitude) {

        TextView txtLocal = findViewById(R.id.textViewLocation);
        /*----------to get City-Name from coordinates ------------- */
        String cityName = null;
        Geocoder gcd = new Geocoder(getBaseContext(),
                Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
            if(addresses.size()>0){
                cityName = addresses.get(0).getAdminArea();
                if(cityName==null){
                    txtLocal.setText("N/A");
                }else{
                    local=cityName;
                    txtLocal.setText(cityName);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = latitude + "\n" + longitude +
                "\n\nMy Currrent City is: " + cityName;
        //Toast.makeText(SensoresActivity.this, s+addresses.get(0).getLocality(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");
        getCity(location.getLatitude(),location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {
        getLocation();
    }

    @Override
    public void onProviderDisabled(String s) {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    private void getLocation() {
        try {
            if (canGetLocation) {
                Log.d(TAG, "Can get location");
                if (isGPS) {
                    // from GPS
                    Log.d(TAG, "GPS on");
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            getCity(loc.getLatitude(),loc.getLongitude());
                    }
                } else if (isNetwork) {
                    // from Network Provider
                    Log.d(TAG, "NETWORK_PROVIDER on");
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null)
                            getCity(loc.getLatitude(),loc.getLongitude());
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    getCity(loc.getLatitude(),loc.getLongitude());
                }
            } else {
                Log.d(TAG, "Can't get location");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
            Log.d(TAG, provider);
            Log.d(TAG, location == null ? "NO LastLocation" : location.toString());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                Log.d(TAG, "onRequestPermissionsResult");
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(
                                                        new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                } else {
                    Log.d(TAG, "No rejected permissions.");
                    canGetLocation = true;
                    getLocation();
                }
                break;
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
}


