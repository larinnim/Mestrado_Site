package com.example.larin.teste;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by larin on 6/26/2017.
 */

public class ThirdFragment extends Fragment {
    View myview;
    ListView lv;
    InputStream is = null;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    JSONParser jsonParser = new JSONParser();
    JSONObject json;
    TextView mydata;
    ImageView imagem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            HttpClient httpClient = new DefaultHttpClient();
            //Specify the url and the name of the php file that we are going to use as a parameter to the HTTPOST method
            HttpPost httpPost = new HttpPost("http://192.168.0.5/connection.php");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch (Exception e){System.out.println("Exception 1 caught");}

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"), 8);
            //Create a StringBuilder object to hold the data
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine())!= null)
                sb.append(line+"\n");
            //Use the toString() method to get the data in the result
            result = sb.toString();
            is.close();
            //Let's check the data by printing the result in the logcat
            System.out.println("<--------- Here is my data ----------->");
            System.out.println(result);

        }catch (Exception e ){System.out.println("Exception 2 caught");}
         /*  The result now contains the data in the form of json. Let's inflate in the form o textview*/

        try{
            JSONArray jArray = new JSONArray(result); //Create Json array
            int count = jArray.length();
            for (int i = 0; i<count; i++)
            {
                //Create a json object to extract the data
                JSONObject json_data = jArray.getJSONObject(i);
                String posicao_response = json_data.getString("Posicao");
                System.out.println("<--------- Here is my data 2222  ----------->");
                System.out.println(posicao_response);
                myview = inflater.inflate(R.layout.home, container, false);
                mydata = (TextView)myview.findViewById(R.id.textView2);
                imagem = (ImageView)myview.findViewById(R.id.imageView2);
                mydata.setText(posicao_response);
                if(posicao_response.equals("Em Pe")){
                imagem.setImageResource(R.drawable.empe);
            }
                if(posicao_response.equals("Deitado")){
                    imagem.setImageResource(R.drawable.lying);
                }
                if(posicao_response.equals("Sentado")){
                    imagem.setImageResource(R.drawable.sitting);
                }
                if(posicao_response.equals("Andando")){
                    imagem.setImageResource(R.drawable.walking);
                }
                if(posicao_response.equals("Caiu")){
                    imagem.setImageResource(R.drawable.cai);
                }
            }
        }   catch (Exception e) {System.out.println("Exception 3 caught");}
        return myview;

    }
}

