package com.device42.client.parser;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public abstract class AbstractJsonObjectListParser<T> implements JsonObjectListParser<T>{

	private int count;
	private int limit; 
	

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	protected void storeLimits(JSONObject json) {
		try {
			limit = json.getInt(LIMIT_TAG);
		} catch (JSONException e) {
			//limit not found
		}
		try {
			count = json.getInt(TOTAL_COUNT_TAG);
		} catch (JSONException e) {
			//total count not found
		}
	}
	
}
