package com.device42.client.parser;

import com.device42.client.model.ApplicationComponent;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BasicApplicationComponentsParser extends AbstractJsonObjectListParser<ApplicationComponent> {
    private final BasicApplicationComponentParser basicApplicationComponentParser=new BasicApplicationComponentParser();
    @Override
    public List<ApplicationComponent> parse(JSONObject json) throws JSONException {
        JSONArray jsonArray = json.getJSONArray("appcomps");
        storeLimits(json);
        List<ApplicationComponent> res = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            res.add(basicApplicationComponentParser.parse(jsonArray.getJSONObject(i)));
        }
        return res;
    }
}
