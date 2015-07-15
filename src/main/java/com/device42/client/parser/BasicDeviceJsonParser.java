package com.device42.client.parser;

import com.device42.client.model.Device;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class BasicDeviceJsonParser implements JsonObjectParser<Device> {
    @Override
    public Device parse(JSONObject json) throws JSONException {
        final long id = json.getLong("device_id");
        final String name = json.getString("name");
        final String hardwareModel = (json.has("hw_model")) ? json.getString("hw_model") : "";
        final String serialNo = (json.has("serial_no")) ? json.getString("serial_no") : "";
        final String assetNo = (json.has("asset_no")) ? json.getString("asset_no") : "";
        return new Device(id, name, hardwareModel, serialNo, assetNo);
    }
}
