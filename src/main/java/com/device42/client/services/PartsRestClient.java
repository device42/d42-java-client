package com.device42.client.services;

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;

import com.device42.client.model.Part;
import com.device42.client.parser.BasicPartsJsonParser;
import com.device42.client.services.parameters.PartParameters;

public class PartsRestClient extends AbstractAsynchronousRestClient {
    private BasicPartsJsonParser partsJsonParser = new BasicPartsJsonParser();
    private static final String ALL_PARTS_PATH = "/api/1.0/parts/";

    PartsRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<Part> getParts() {
        return getParts(new PartParameters.PartParametersBuilder().build());
    }

    public List<Part> getParts(PartParameters partParameters) {
        return get(ALL_PARTS_PATH, partsJsonParser, partParameters);
    }
    
    public List<Part> getAllParts() {
    	return getAllParts(new PartParameters.PartParametersBuilder().build());
    }
    
    public List<Part> getAllParts(PartParameters partParameters) {
        return getAll(ALL_PARTS_PATH, partsJsonParser, partParameters);
    }
}
