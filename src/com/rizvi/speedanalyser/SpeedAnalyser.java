package com.rizvi.speedanalyser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
//import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

import com.google.gson.Gson;
//import com.rizvi.speedanalyser.model.Result;
import com.rizvi.speedanalyser.model.SearchResponse;

public class SpeedAnalyser extends Activity{
	
	String url ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        
        final EditText et = (EditText) findViewById(R.id.enter); 
        final TextView tv = new TextView(this);
        
        Button go = (Button) findViewById(R.id.go); 
        go.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SpeedAnalyser.this,TestResult.class);
				String site = et.getText().toString();
				
				url = "https://www.googleapis.com/pagespeedonline/v1/runPagespeed?url="+site+"&strategy=mobile&pp=1&key=AIzaSyBxLiJc-5mebsZ8DN6UFto6x36PZhHs84k";
						

		        i.putExtra("url", url);
		        startActivity(i);
		        
		        //tv.setText(response.title+"\n"+response.score+"\n"+response.kind);
		        
		        //setContentView(tv);
		        
		        //Toast.makeText(this, response.title, Toast.LENGTH_SHORT).show();
		        
		        //List<Result> results = response.results;
		        
		        //for (Result result : results) {
		        	//Toast.makeText(this, response.kind, Toast.LENGTH_SHORT).show();
				//}
				
				
			}
		});
        
        
        
    }
   
}