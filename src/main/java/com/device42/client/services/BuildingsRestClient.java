package com.device42.client.services;

import com.device42.client.model.Building;
import com.device42.client.parser.BasicBuildingsJsonParser;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public class BuildingsRestClient extends AbstractAsynchronousRestClient {
    private BasicBuildingsJsonParser buildingsJsonParser = new BasicBuildingsJsonParser();
    private static final String BUILDINGS_PATH = "/api/1.0/buildings/";
    BuildingsRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<Building> getBuildings() {
        List<Building> buildings = get(BUILDINGS_PATH, buildingsJsonParser);
        return buildings;
    }
    
    public List<Building> getAllBuildings() {
    	List<Building> buildings = getAll(BUILDINGS_PATH, buildingsJsonParser);
        return buildings;
    }
}
