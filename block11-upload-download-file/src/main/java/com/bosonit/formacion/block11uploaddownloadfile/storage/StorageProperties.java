package com.bosonit.formacion.block11uploaddownloadfile.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties ("storage")
public class StorageProperties {

    private String location = "upload_files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
