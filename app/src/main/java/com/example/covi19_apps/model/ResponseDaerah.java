package com.example.covi19_apps.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDaerah{

	@SerializedName("results")
	private List<ResultsItem> results;

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}