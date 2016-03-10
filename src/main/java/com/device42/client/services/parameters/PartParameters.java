package com.device42.client.services.parameters;

import java.util.HashMap;
import java.util.Map;

import com.device42.client.util.StringUtil;


public class PartParameters extends AbstractInputLimitParameters {
    public static class PartParametersBuilder {
        private String type;
        private String device;
        private String room;
        private long partId;
        private long partmodelId;
        private String deviceSerial;

        public PartParametersBuilder() {}

        public PartParameters build() {
            return new PartParameters(type, device, room, partId, partmodelId, deviceSerial);
        }

        public PartParametersBuilder device(String device) {
            this.device = device;
            return this;
        }

        public PartParametersBuilder deviceSerial(String deviceSerial) {
            this.deviceSerial = deviceSerial;
            return this;
        }

        public PartParametersBuilder partId(long partId) {
            this.partId = partId;
            return this;
        }

        public PartParametersBuilder partmodelId(long partmodelId) {
            this.partmodelId = partmodelId;
            return this;
        }

        public PartParametersBuilder room(String room) {
            this.room = room;
            return this;
        }

        public PartParametersBuilder type(String type) {
            this.type = type;
            return this;
        }
    }

    private final String type;
    private final String device;
    private final String room;
    private final long partId;
    private final long partmodelId;
    private final String deviceSerial;

    public PartParameters(
            String type,
            String device,
            String room,
            long partId,
            long partmodelId,
            String deviceSerial) {
        this.type = type;
        this.device = device;
        this.room = room;
        this.partId = partId;
        this.partmodelId = partmodelId;
        this.deviceSerial = deviceSerial;
    }

    @Override
    public Map<String, String> parametersMap() {
        Map<String, String> parameters = new HashMap<>();
        if (partId > 0) {
            parameters.put("part_id", Long.toString(partId));
        }
        if (partmodelId > 0) {
            parameters.put("partmodel_id", Long.toString(partmodelId));
        }
        if (StringUtil.isNotBlank(type)) {
            parameters.put("type", type);
        }
        if (StringUtil.isNotBlank(device)) {
            parameters.put("device", device);
        }
        if (StringUtil.isNotBlank(room)) {
            parameters.put("room", room);
        }
        if (StringUtil.isNotBlank(deviceSerial)) {
            parameters.put("device_serial", deviceSerial);
        }
        addLimits(parameters);
        return parameters;
    }
}
