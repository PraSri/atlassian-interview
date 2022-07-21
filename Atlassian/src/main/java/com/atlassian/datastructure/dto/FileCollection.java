package com.atlassian.datastructure.dto;

import java.util.List;

public class FileCollection {

    private String collectionName;
    private List<File> files;
    // assuming sum of files size will not overflow
    private int size;

    public FileCollection(String collectionName, List<File> files, int size) {
        this.collectionName = collectionName;
        this.files = files;
        this.size = size;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public List<File> getFiles() {
        return files;
    }

    public int getSize() {
        return size;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
