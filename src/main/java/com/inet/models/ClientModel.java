package com.inet.models;

public class ClientModel {
    public String name;
    public String systemName;
    public String systemArch;
    public String systemVersion;

    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "systemName: " + systemName + "\n" +
                "systemArch: " + systemArch + "\n" +
                "systemVersion: " + systemVersion + "\n";
    }
}