package com.device42.client.services;

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;

import com.device42.client.model.Room;
import com.device42.client.parser.BasicRoomsJsonParser;
import com.device42.client.services.parameters.RoomParameters;

public class RoomsRestClient extends AbstractAsynchronousRestClient {
    private BasicRoomsJsonParser roomsJsonParser = new BasicRoomsJsonParser();
    
    private static final String ALL_ROOMS_PATH = "/api/1.0/rooms/";

    RoomsRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<Room> getRooms(RoomParameters roomParameters) {
        return get(ALL_ROOMS_PATH, roomsJsonParser, roomParameters);
    }
    
    public List<Room> getAllRooms(RoomParameters roomParameters) {
        return getAll(ALL_ROOMS_PATH, roomsJsonParser, roomParameters);
    }
}
