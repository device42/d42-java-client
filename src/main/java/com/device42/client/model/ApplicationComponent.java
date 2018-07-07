package com.device42.client.model;

public class ApplicationComponent {

    private long id;
    private String name;
    private String device;
    private String jsonConfig;

    public ApplicationComponent(String name, String device) {
        this.name = name;
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(String jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    @Override
    public String toString() {
        return "ApplicationComponent [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", device='" + device + '\'' +
                ", jsonConfig='" + jsonConfig + '\'' +
                ']';
    }
}
