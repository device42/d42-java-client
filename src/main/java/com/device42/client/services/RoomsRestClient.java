package com.device42.client.services;

import com.device42.client.model.Room;
import com.device42.client.parser.BasicRoomsJsonParser;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomsRestClient extends AbstractAsynchronousRestClient {
    private BasicRoomsJsonParser roomsJsonParser = new BasicRoomsJsonParser();

    RoomsRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<Room> getRooms() {
        return get("/api/1.0/rooms/", roomsJsonParser);
    }

    public List<Room> getRoomsByBuildingId(long buildingId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("building_id", Long.toString(buildingId));
        return get("/api/1.0/rooms/", roomsJsonParser, parameters);
    }
}
