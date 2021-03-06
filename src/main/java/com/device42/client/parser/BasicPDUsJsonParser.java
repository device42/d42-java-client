package com.device42.client.parser;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.device42.client.model.PDU;

public class BasicPDUsJsonParser extends AbstractJsonObjectListParser<PDU> {
    private BasicPDUJsonParser pduJsonParser = new BasicPDUJsonParser();

    @Override
    public List<PDU> parse(JSONObject json) throws JSONException {
        JSONArray jsonArray = json.getJSONArray("pdus");
        storeLimits(json);
        List<PDU> res = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            res.add(pduJsonParser.parse(jsonArray.getJSONObject(i)));
        }
        return res;
    }
}
