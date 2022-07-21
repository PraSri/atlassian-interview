package com.atlassian.datastructure.dto;

public class File {
    private String fileName;
    private int size;
    private String collectionName;

    public File(String fileName, int size) {
        this.fileName = fileName;
        this.size = size;
    }

    public File(String fileName, int size, String collectionName) {
        this.fileName = fileName;
        this.size = size;
        this.collectionName = collectionName;
    }
}
