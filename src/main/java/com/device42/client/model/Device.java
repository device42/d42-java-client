package com.device42.client.model;

public class Device {
    private final long id;
    private final String name;
    private final String hardwareModel;
    private final String serialNo;
    private final String assetNo;

    public Device(long id, String name, String hardwareModel, String serialNo, String assetNo) {
        this.id = id;
        this.name = name;
        this.hardwareModel = hardwareModel;
        this.serialNo = serialNo;
        this.assetNo = assetNo;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public String getHardwareModel() {
        return hardwareModel;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSerialNo() {
        return serialNo;
    }

    @Override
    public String toString() {
        return "Device [id=" + id + ", name=" + name + ", hardwareModel="
                + hardwareModel + ", serialNo=" + serialNo + ", assetNo="
                + assetNo + "]";
    }
}
