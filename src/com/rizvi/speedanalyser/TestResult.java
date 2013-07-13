package com.rizvi.speedanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
//import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

//import com.google.gson.Gson;
//import com.rizvi.speedanalyser.model.Result;

//import com.rizvi.speedanalyser.model.SearchResponse;
//import com.rizvi.speedanalyser.model.SearchResponse.PageStats;

public class TestResult extends Activity {
	
	String url ;
	String kind;
	String score;
	String id;
	String htmlResponseBytes;
	String cssResponseBytes;
	String imageResponseBytes;
	String javascriptResponseBytes;
	String otherResponseBytes;
	
	float html,css,image,js,other,total,percentage;
	
	StringBuilder builder = new StringBuilder();
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second); 
        final TextView tv = new TextView(this);
      
        url = getIntent().getExtras().getString("url");
		InputStream source = retrieveStream(url);
        
        //Gson gson = new Gson();
        
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(source));
		
        
        String line;
		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        
        try {
        	JSONObject jsonObj = new JSONObject(builder.toString());
        	kind = jsonObj.getString("kind");
        	id = jsonObj.getString("id");
        	score = jsonObj.getString("score");
        	
         	JSONObject pageStats = jsonObj.getJSONObject("pageStats");
        	html = pageStats.getInt("htmlResponseBytes");
        	css = pageStats.getInt("cssResponseBytes");
        	image = pageStats.getInt("imageResponseBytes");
        	js = pageStats.getInt("javascriptResponseBytes");
        	other = pageStats.getInt("otherResponseBytes");
        	

			Log.i(getClass().getSimpleName(),
					"html|css " + html+"-"+css);
        	
        	//html =Integer.parseInt(htmlResponseBytes); 
        	//css =Integer.parseInt(cssResponseBytes);
        	//image =Integer.parseInt(imageResponseBytes);
        	//js =Integer.parseInt(javascriptResponseBytes);
        	//other =Integer.parseInt(otherResponseBytes);
        	
        	total = html+css+image+js+other;
        	
        	html = (html/total)*100;
        	css = (css/total)*100;
        	image = (image/total)*100;
        	js = (js/total)*100;
        	other = (other/total)*100;
        	
        	
			Log.i(getClass().getSimpleName(),
					"total %"+ total);
			//for (int i = 0; i < jsonArray.length(); i++) {
				
				//Log.i(getClass().getSimpleName(), kind);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        
        //SearchResponse response = gson.fromJson(reader, SearchResponse.class);
        TextView t = new TextView(this); 
       
        t=(TextView)findViewById(R.id.textView1);
        
        
        
        Log.w(getClass().getSimpleName(),"after pageStats object");
   
        	//css = response.pageStats.get(1).cssResponseBytes;
        	//html = pageStats.htmlResponseBytes;
       
        
        t.setText(id);  
        
        WebView wv = new WebView(this);
        
        wv = (WebView)findViewById(R.id.webView1);                   
        wv.loadUrl("https://chart.googleapis.com/chart?cht=gom&chd=t:"+score+"&chs=100x70&chf=c,s,6883ae&chtt=Page+Score+"+score+"&chts=000000,13,r");
        
        WebView wv1 = new WebView(this);
        wv1.setBackgroundColor(Color.parseColor("#000000"));
        
        wv1 = (WebView)findViewById(R.id.webView2);                   
        wv1.loadUrl("https://chart.googleapis.com/chart?cht=p3&chd=t:"+html+","+css+","+image+","+js+","+other+"&chs=300x140&chdl=html|css|image|javascript|other&chco=3366cc|dc3912|ff9900|109618|990099&chf=c,s,6883ae");
        Log.i(getClass().getSimpleName(), "https://chart.googleapis.com/chart?cht=p3&chd=t:"+html+","+css+","+image+","+js+","+other+"&chs=300x140&chdl=html|css|image|javascript|other&chco=3366cc|dc3912|ff9900|109618|990099&chf=c,s,6883ae");
        //tv.setText(response.title+"\n"+response.score+"\n"+response.kind);
        
        //setContentView(tv);
        
    }
    
    private InputStream retrieveStream(String url) {
    	
    	DefaultHttpClient client = new DefaultHttpClient(); 
        
        HttpGet getRequest = new HttpGet(url);
          
        try {
           
           HttpResponse getResponse = client.execute(getRequest);
           final int statusCode = getResponse.getStatusLine().getStatusCode();
           
           if (statusCode != HttpStatus.SC_OK) { 
              Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
              return null;
           }

           HttpEntity getResponseEntity = getResponse.getEntity();
           return getResponseEntity.getContent();
           
        } 
        catch (IOException e) {
           getRequest.abort();
           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        
        return null;
        
     }

	

	
 
}