package com.device42.client.services;

import com.device42.client.model.Device;
import com.device42.client.parser.BasicDevicesJsonParser;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DevicesRestClient extends AbstractAsynchronousRestClient {
    private BasicDevicesJsonParser devicesJsonParser = new BasicDevicesJsonParser();

    DevicesRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<Device> getDevices() {
        return get("/api/1.0/devices/all/", devicesJsonParser);
    }

    public List<Device> getDevicesByRackId(long rackId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("rack_id", Long.toString(rackId));
        return get("/api/1.0/devices/all/", devicesJsonParser);
    }

    public List<Device> getDevicesByRoomId(long roomId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("room_id", Long.toString(roomId));
        return get("/api/1.0/devices/all/", devicesJsonParser);
    }
}
