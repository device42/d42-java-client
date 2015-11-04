package com.device42.client.parser;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.device42.client.model.PDU;

public class BasicPDUJsonParser implements JsonObjectParser<PDU> {
    @Override
    public PDU parse(JSONObject json) throws JSONException {
        final long id = json.getLong("pdu_id");
        final String name = json.getString("name");
        final String notes = json.getString("notes");
        return new PDU(id, name, notes);
    }
}
