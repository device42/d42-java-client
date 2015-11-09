package com.device42.client.parser;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.device42.client.model.Part;

public class BasicPartJsonParser implements JsonObjectParser<Part> {
    @Override
    public Part parse(JSONObject json) throws JSONException {
        final long id = json.getLong("part_id");
        final String description = json.getString("description");
        final String assignment = json.getString("assignment");
        final String serialNo = json.getString("serial_no");
        final int count = json.getInt("count");
        JSONObject partModel = json.getJSONObject("partmodel");
        final String modelName = (partModel != null) ? partModel.getString("name") : "";
        return new Part(id, modelName, description, assignment, serialNo, count);
    }
}
