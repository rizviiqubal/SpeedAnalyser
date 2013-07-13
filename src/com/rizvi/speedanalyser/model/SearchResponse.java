package com.rizvi.speedanalyser.model;

import java.util.List;


//import java.util.List;

//import com.google.gson.annotations.SerializedName;

public class SearchResponse {
	
	
	
	//@SerializedName("kind")
	public String kind;
	
	//@SerializedName("title")
	public String title;
	
	//@SerializedName("id")
	public String id;
	
	public int score;
	
	public List<PageStats> pageStats;
	public static class PageStats {
		
		public String htmlResponseBytes;
		public String cssResponseBytes;
	
	}
	
	
	
	
}
