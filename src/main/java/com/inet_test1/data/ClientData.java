package com.inet_test1.data;

public class ClientData {
    public String name;
    public String systemName;
    public String systemArch;
    public String systemVersion;

    public ClientData(
            String name,
            String systemName,
            String systemArch,
            String systemVersion) {
        this.name = name;
        this.systemName = systemName;
        this.systemArch = systemArch;
        this.systemVersion = systemVersion;
    }
}