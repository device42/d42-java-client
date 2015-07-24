package com.device42.client.services.parameters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.device42.client.util.Device42ClientException;

public class DeviceParameters implements InputParameters {
    public static class DeviceParametersBuilder {
        private static final String[] DEFAULT_COLUMNS = {
                "uuid", "service_level", "rack", "device_id", "name", "asset_no",
                "type", "manufacturer", "serial_no", "virtual_host_name", "hw_model"
        };

        private List<String> columns;
        private String type;
        private String serviceLevel;
        private long roomId;
        private long rackId;
        private long limit;
        private long offset;

        public DeviceParametersBuilder() {
            this.columns = Arrays.asList(DEFAULT_COLUMNS);
        }

        public DeviceParametersBuilder(List<String> columns) {
            if (columns == null || columns.isEmpty()) {
                throw new Device42ClientException("List device's columns must not be null or empty.");
            }
            this.columns = columns;
        }

        public DeviceParameters build() {
            return new DeviceParameters(columns, type, serviceLevel, roomId, rackId, limit, offset);
        }

        public DeviceParametersBuilder limit(long limit) {
            this.limit = limit;
            return this;
        }

        public DeviceParametersBuilder offset(long offset) {
            this.offset = offset;
            return this;
        }

        public DeviceParametersBuilder rackId(long rackId) {
            this.rackId = rackId;
            return this;
        }

        public DeviceParametersBuilder roomId(long roomId) {
            this.roomId = roomId;
            return this;
        }

        public DeviceParametersBuilder serviceLevel(String serviceLevel) {
            this.serviceLevel = serviceLevel;
            return this;
        }

        public DeviceParametersBuilder type(String type) {
            this.type = type;
            return this;
        }
    }

    private List<String> columns;
    private String type;
    private String serviceLevel;
    private long roomId;
    private long rackId;
    private long limit;
    private long offset;

    private DeviceParameters(
            List<String> columns,
            String type,
            String serviceLevel,
            long roomId,
            long rackId,
            long limit,
            long offset) {
        this.columns = columns;
        this.type = type;
        this.serviceLevel = serviceLevel;
        this.roomId = roomId;
        this.rackId = rackId;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public Map<String, String> parametersMap() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("include_cols", StringUtils.join(columns, ","));
        if (StringUtils.isNotBlank(serviceLevel)) {
            parameters.put("service_level", serviceLevel);
        }
        if (StringUtils.isNotBlank(type)) {
            parameters.put("type", type);
        }
        if (roomId > 0) {
            parameters.put("room_id", Long.toString(roomId));
        }
        if (rackId > 0) {
            parameters.put("rack_id", Long.toString(rackId));
        }
        if (limit > 0) {
            parameters.put("limit", Long.toString(limit));
        }
        if (offset > 0) {
            parameters.put("offset", Long.toString(offset));
        }
        return parameters;
    }
}
