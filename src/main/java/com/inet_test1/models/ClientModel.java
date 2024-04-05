package com.inet_test1.models;

public class ClientModel {
    String name;
    String systemName;
    String systemArch;
    String systemVersion;

    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "systemName: " + systemName + "\n" +
                "systemArch: " + systemArch + "\n" +
                "systemVersion: " + systemVersion + "\n";
    }
}