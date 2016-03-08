package com.device42.client.services;

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;

import com.device42.client.model.Service;
import com.device42.client.parser.BasicServicesJsonParser;
import com.device42.client.services.parameters.ServiceParameters;

public class ServicesRestClient extends AbstractAsynchronousRestClient {
    private BasicServicesJsonParser servicesJsonParser = new BasicServicesJsonParser();
    
    private static final String ALL_SERVICES_PATH = "/api/1.0/services/";

    ServicesRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<Service> getServices(ServiceParameters serviceParameters) {
        return get(ALL_SERVICES_PATH, servicesJsonParser, serviceParameters);
    }
    public List<Service> getAllServices(ServiceParameters serviceParameters) {
        return getAll(ALL_SERVICES_PATH, servicesJsonParser, serviceParameters);
    }
}
