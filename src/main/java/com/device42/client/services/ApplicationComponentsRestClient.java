package com.device42.client.services;

import com.device42.client.model.ApplicationComponent;
import com.device42.client.parser.BasicApplicationComponentParser;
import com.device42.client.parser.BasicApplicationComponentsParser;
import com.device42.client.services.parameters.ApplicationComponentParameters;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;

public class ApplicationComponentsRestClient extends AbstractAsynchronousRestClient {
    private BasicApplicationComponentParser basicApplicationComponentParser=new BasicApplicationComponentParser();
    private BasicApplicationComponentsParser basicApplicationComponentsParser=new BasicApplicationComponentsParser();

    private static final String APPLICATION_COMPONENT_ALL_PATH = "/api/1.0/appcomps/";

    ApplicationComponentsRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public ApplicationComponent getApplicationComponentById(long applicationComponentId) {
        return get(String.format("/api/1.0/appcomps/%d/", applicationComponentId), basicApplicationComponentParser);
    }

    public List<ApplicationComponent> getApplicationComponents(ApplicationComponentParameters applicationComponentParameters) {
        return get(APPLICATION_COMPONENT_ALL_PATH, basicApplicationComponentsParser, applicationComponentParameters);
    }

    public List<ApplicationComponent> getAllApplicationComponents(ApplicationComponentParameters applicationComponentParameters) {
        return getAll(APPLICATION_COMPONENT_ALL_PATH, basicApplicationComponentsParser, applicationComponentParameters);
    }

    public int createOrUpdateApplicationComponent(ApplicationComponent applicationComponent) throws IOException {

        return createOrUpdate(APPLICATION_COMPONENT_ALL_PATH, applicationComponent);

    }

}
