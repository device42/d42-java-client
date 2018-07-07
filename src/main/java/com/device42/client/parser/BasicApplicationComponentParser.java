package com.device42.client.parser;

import com.device42.client.model.ApplicationComponent;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class BasicApplicationComponentParser implements JsonObjectParser<ApplicationComponent>  {
    @Override
    public ApplicationComponent parse(JSONObject json) throws JSONException {
        final String name=json.getString("name");
        final String deviceName=json.has("device") ? json.getString("device") : null;
        final long id=json.getLong("appcomp_id");

        ApplicationComponent applicationComponent=new ApplicationComponent(name,deviceName);
        applicationComponent.setId(id);

        return applicationComponent;
    }
}
