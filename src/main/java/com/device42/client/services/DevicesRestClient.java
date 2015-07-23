package com.device42.client.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;

import com.device42.client.model.Device;
import com.device42.client.parser.BasicDeviceJsonParser;
import com.device42.client.parser.BasicDevicesJsonParser;

public class DevicesRestClient extends AbstractAsynchronousRestClient {
    private BasicDeviceJsonParser deviceJsonParser = new BasicDeviceJsonParser();
    private BasicDevicesJsonParser devicesJsonParser = new BasicDevicesJsonParser();

    DevicesRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public Device getDeviceById(long deviceId) {
        return get(String.format("/api/1.0/devices/id/%d/", deviceId), deviceJsonParser);
    }

    public Device getDeviceByName(String deviceName) {
        return get(String.format("/api/1.0/devices/name/%s/", deviceName), deviceJsonParser);
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
