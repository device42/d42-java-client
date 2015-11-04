package com.device42.client.model;

public class Part {
    private final long id;
    private final String description;
    private final String assignment;
    private final String serialNo;
    private final int count;

    public Part(
            long id,
            String description,
            String assignment,
            String serialNo,
            int count) {
        this.id = id;
        this.description = description;
        this.assignment = assignment;
        this.serialNo = serialNo;
        this.count = count;
    }

    public String getAssignment() {
        return assignment;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    @Override
    public String toString() {
        return "Part [id=" + id + ", description=" + description + ", assignment=" + assignment + ", serialNo="
                + serialNo + ", count=" + count + "]";
    }
}
