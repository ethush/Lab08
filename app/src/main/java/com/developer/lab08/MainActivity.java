package com.developer.lab08;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public ArrayList<Ubicaciones> items = new ArrayList<Ubicaciones>();

    String TAG = "JSON OPERATIONS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        /*
        * HTTP request and get JSON object to populate listview
        * */
        JsonRequest jsonrequest = new JsonRequest();
        String request = null;
        try {
            jsonrequest.execute();
            request = (String) jsonrequest.get();

        }
        catch (Exception ex ){
            ex.printStackTrace();
        }

        Log.d("JSON REQUEST", request);

        try {
            JSONArray json = new JSONArray(request);
            Log.e(TAG, "JSON objects lenght "+String.valueOf(json.length()));
            for(int i=0; i< json.length();i++) {
                JSONObject item = json.getJSONObject(i);
                Log.e(TAG,"JSONObject at position " + String.valueOf(i)+ " name value is:" +item.getString("nombre"));

                Ubicaciones ubicacion = new Ubicaciones();
                ubicacion.setUbicacion(item.getString("nombre"));
                ubicacion.setDetalles(item.getString("horario"));
                items.add(ubicacion);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /* Simple way to populate a Listview
         * Declarating an array of strings */
        /*String[] nombres = new String[5];
        nombres[0] = "La choperia";
        nombres[1] = "Dessestresse";
        nombres[2] = "La costumbre";
        nombres[3] = "La mezcaloteca";
        nombres[4] = "La galera";
        */

        /*
        * Second way:
        * Declaring a object that contains a manipulable object
        * by example class Ubicaciones, second uses a BaseAdapter to fill an array of items
        * as show lines below
        * */
        /*
        // Item1
        Ubicaciones ubicacion = new Ubicaciones();
        ubicacion.setUbicacion("La choperia");
        items.add(ubicacion);
        //Item 2
        ubicacion = new Ubicaciones();
        ubicacion.setUbicacion("Dessestresse");
        items.add(ubicacion);
        //Item 3
        ubicacion = new Ubicaciones();
        ubicacion.setUbicacion("La Costumbre");
        items.add(ubicacion);
        //Item 4
        ubicacion = new Ubicaciones();
        ubicacion.setUbicacion("La Mezcaloteca");
        items.add(ubicacion);
        //Item 5
        ubicacion = new Ubicaciones();
        ubicacion.setUbicacion("La Galera");
        items.add(ubicacion);
        */
        //Set and fill the adapter
        final myAdapter adapter = new myAdapter(getApplicationContext(),items);

        // Simple way using array string
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.ubicacion, nombres);

        //And set to list view
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),items.get(position).getUbicacion() + " selected.",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


