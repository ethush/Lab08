package com.developer.lab08;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by developer on 26/02/2015.
 */
public class JsonRequest extends AsyncTask {
    @Override
    protected String doInBackground(Object[] params) {
        String data = "";

        HttpURLConnection urlconnection = null;
        InputStream inputStream = null;
        BufferedReader br;

        try {
            URL url = new URL("http://www.s22.mx/administracion/json.php");
            urlconnection = (HttpURLConnection)url.openConnection();
            urlconnection.connect();

            inputStream = urlconnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            String linea;

            while((linea = br.readLine()) != null) {
                sb.append(linea + "\n");
            }

            br.close();
            data = sb.toString();
        }
        catch(IOException|NullPointerException ex){
            ex.printStackTrace();
        }
        finally{
            try {
                inputStream.close();
                urlconnection.disconnect();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }

        }

        return data;

    }
}
