package com.device42.client.services;

import com.device42.client.model.Service;
import com.device42.client.parser.BasicServicesJsonParser;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicesRestClient extends AbstractAsynchronousRestClient {
    private BasicServicesJsonParser servicesJsonParser = new BasicServicesJsonParser();

    ServicesRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<Service> getServices() {
        return get("/api/1.0/services/", servicesJsonParser);
    }

    public List<Service> getServicesById(long id) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("service_id", Long.toString(id));
        return get("/api/1.0/services/", servicesJsonParser, parameters);
    }
}
