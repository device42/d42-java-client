package com.device42.client.services.parameters;


import java.util.HashMap;
import java.util.Map;

public class EmptyInputParameters extends AbstractInputLimitParameters {
    public EmptyInputParameters() {}

    @Override
    public Map<String, String> parametersMap() {
    	Map<String, String> parameters = new HashMap<>();
    	addLimits(parameters);
        return parameters;
    }
}
