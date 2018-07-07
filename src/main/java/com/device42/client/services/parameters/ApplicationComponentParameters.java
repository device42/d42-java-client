package com.device42.client.services.parameters;

import java.util.HashMap;
import java.util.Map;

public class ApplicationComponentParameters extends AbstractInputLimitParameters {

    public static class ApplicationComponentParametersBuilder {
        private long deviceId;

        public ApplicationComponentParametersBuilder() {}

        public ApplicationComponentParametersBuilder deviceId(long deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public ApplicationComponentParameters build() {
            return new ApplicationComponentParameters(deviceId);
        }
    }

    private long deviceId;

    public ApplicationComponentParameters(long deviceId) {
        this.deviceId=deviceId;
    }

    @Override
    public Map<String, String> parametersMap() {
        Map<String, String> parameters = new HashMap<>();
        if (deviceId > 0) {
            parameters.put("device_id", Long.toString(deviceId));
        }
        addLimits(parameters);
        return parameters;
    }
}
