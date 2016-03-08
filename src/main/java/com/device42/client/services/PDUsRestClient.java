package com.device42.client.services;

import java.util.Iterator;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;

import com.device42.client.model.PDU;
import com.device42.client.parser.BasicPDUsJsonParser;
import com.device42.client.services.parameters.PDUParameters;

public class PDUsRestClient extends AbstractAsynchronousRestClient {
    private BasicPDUsJsonParser pdusJsonParser = new BasicPDUsJsonParser();
    private static final String ALL_PDUS_PATH = "/api/1.0/pdus/";

    PDUsRestClient(String baseUrl, CloseableHttpClient httpClient) {
        super(baseUrl, httpClient);
    }

    public List<PDU> getPDUs() {
        return get(ALL_PDUS_PATH, pdusJsonParser, new PDUParameters.PDUParametersBuilder().build());
    }
    
    public List<PDU> getAllPDUs() {
        return getAll(ALL_PDUS_PATH, pdusJsonParser, new PDUParameters.PDUParametersBuilder().build());
    }

    public List<PDU> getPDUsById(long id) {
        List<PDU> pdus = getPDUs();
        Iterator<PDU> iter = pdus.iterator();
        while (iter.hasNext()) {
            PDU pdu = iter.next();
            if (pdu.getId() != id) {
                iter.remove();
            }
        }
        return pdus;
    }

    public List<PDU> getPDUsByName(String name) {
        List<PDU> pdus = getPDUs();
        Iterator<PDU> iter = pdus.iterator();
        while (iter.hasNext()) {
            PDU pdu = iter.next();
            if (!pdu.getName().equals(name)) {
                iter.remove();
            }
        }
        return pdus;
    }
}
