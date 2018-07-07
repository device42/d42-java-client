package com.device42.client.services;

import com.device42.client.model.ApplicationComponent;
import com.device42.client.parser.BasicErrorJsonParser;
import com.device42.client.parser.JsonObjectListParser;
import com.device42.client.parser.JsonObjectParser;
import com.device42.client.services.parameters.EmptyInputParameters;
import com.device42.client.services.parameters.InputLimitParameters;
import com.device42.client.services.parameters.InputParameters;
import com.device42.client.util.Device42ClientException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.AuthCache;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class AbstractAsynchronousRestClient implements Closeable {
    

    private final HttpHost targetHost;
    private final CloseableHttpClient httpClient;
    private final HttpClientContext clientContext;

    protected AbstractAsynchronousRestClient(String baseUrl, CloseableHttpClient httpClient) {
        this.targetHost = HttpHost.create(baseUrl);
        this.httpClient = httpClient;
        this.clientContext = createClientContext();
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    private HttpClientContext createClientContext() {
        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());
        HttpClientContext clientContext = HttpClientContext.create();
        clientContext.setAuthCache(authCache);
        return clientContext;
    }

    protected <T> T get(String path, JsonObjectParser<T> parser) {
        return get(path, parser, new EmptyInputParameters());
    }

    
    protected <T> T get(String path, JsonObjectParser<T> parser, InputParameters inputParameters) {
        RequestBuilder requestBuilder = RequestBuilder.get().setUri(path);

        for (Map.Entry<String, String> entry : inputParameters.parametersMap().entrySet()) {
            requestBuilder.addParameter(entry.getKey(), entry.getValue());
        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(targetHost, requestBuilder.build(), clientContext);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 300) {
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
                return parser.parse(jsonObject);
            } else {
                String errorMessage = "";
                try {
                    JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
                    errorMessage = new BasicErrorJsonParser().parse(jsonObject).getMessage();
                } catch (JSONException ex) {
                    errorMessage = statusLine.getStatusCode() + ": " + statusLine.getReasonPhrase();
                }
                
                throw new Device42ClientException("Unexpected response status: " + errorMessage);
            }
        } catch (IOException ex) {
            
            throw new Device42ClientException("Error to call REST API " + ex.getMessage(), ex);
        } catch (JSONException ex) {
            
            throw new Device42ClientException("JSON error " + ex.getMessage(), ex);
        }
    }
    protected <T> List<T> getAll(String path, JsonObjectListParser<T> parser) {
    	return getAll(path, parser, new EmptyInputParameters());
    }
    protected <T> List<T> getAll(String path, JsonObjectListParser<T> parser, InputLimitParameters inputParameters) {
    	List<T> result = get(path, parser, inputParameters);
    	if (result != null && parser.getLimit()> 0 && parser.getCount() > 0 && parser.getCount() > result.size()) {
    		for (int offset = parser.getLimit(); offset < parser.getCount(); offset += parser.getLimit()) {
    			inputParameters.addLimit(parser.getLimit());
    			inputParameters.addOffset(offset);
    			List<T> partialResult = get(path, parser, inputParameters);
    			result.addAll(partialResult);
    		}
    	}
    	return result;
    }

    protected int createOrUpdate(String path, Object obj) throws IOException {

        if (obj instanceof ApplicationComponent) {
            RequestBuilder requestBuilder = RequestBuilder.post().setUri(path);
            List <NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("name", ((ApplicationComponent) obj).getName()));
            nvps.add(new BasicNameValuePair("device", ((ApplicationComponent) obj).getDevice()));
            nvps.add(new BasicNameValuePair("json", ((ApplicationComponent) obj).getJsonConfig()));
            requestBuilder.setEntity(new UrlEncodedFormEntity(nvps));
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(targetHost, requestBuilder.build(), clientContext);
                StatusLine statusLine = httpResponse.getStatusLine();
                if (statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 300) {
                    statusLine.getStatusCode();
                } else {
                    String errorMessage = "";
                    try {
                        JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
                        errorMessage = new BasicErrorJsonParser().parse(jsonObject).getMessage();
                    } catch (JSONException ex) {
                        errorMessage = statusLine.getStatusCode() + ": " + statusLine.getReasonPhrase();
                    }

                    throw new Device42ClientException("Unexpected response status: " + errorMessage);
                }
            } catch (IOException ex) {

                throw new Device42ClientException("Error to call REST API " + ex.getMessage(), ex);
            }


        }
        return 0;
    }
    
}
