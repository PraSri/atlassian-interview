package com.atlassian.datastructure;

import com.atlassian.datastructure.dto.File;
import com.atlassian.datastructure.dto.FileCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

public class FileSystem {

  private int size;
  private PriorityQueue<FileCollection> collectionsHeap;
//  private Set<FileCollection> isCollectionInHeap;
  private Map<String, File> fileStore;
  private Map<String, FileCollection> collectionStore;

  public FileSystem() {
    this.size = 0;
    collectionsHeap =
        new PriorityQueue<>(
            (a, b) ->
                a.getSize() == b.getSize()
                    ? a.getCollectionName().compareTo(b.getCollectionName())
                    : b.getSize() - a.getSize());
//    isCollectionInHeap = new HashSet<>();
    fileStore = new HashMap<>();
    collectionStore = new HashMap<>();
  }
  
  public int getAllFileSize() {
      return this.size;
  }
  
  public List<FileCollection> getTopNCollections(int n) {
      // invalid n
      if (n<=0) {
          return Collections.emptyList();
      }
      
      int k = n;
      PriorityQueue<FileCollection> tempHeap = new PriorityQueue<>( (a, b) ->
              a.getSize() == b.getSize()
                      ? a.getCollectionName().compareTo(b.getCollectionName())
                      : b.getSize() - a.getSize());
      List<FileCollection> result = new ArrayList<>();
      
      while(!collectionsHeap.isEmpty() && k>0) {
          FileCollection fc = collectionsHeap.poll();
          result.add(fc);
          tempHeap.add(fc);
          k--;
      }

      while (!tempHeap.isEmpty()) {
          collectionsHeap.add(tempHeap.poll());
      }
      
      return result;
  }
  
  public void addFileToSystem(String fileName, int size, Optional<String> collectionName) {
      File file;
      FileCollection collection;
      if(collectionName.isEmpty()) {
          file = new File(fileName, size);
          fileStore.put(fileName, file);
      } else {
          String cName = collectionName.get();
          file = new File(fileName, size, cName);
          fileStore.put(fileName, file);
          if(!collectionStore.containsKey(cName)) {
              List<File> files = new ArrayList<>();
              files.add(file);
              collection = new FileCollection(cName, files, size);
              collectionStore.put(cName, collection);
              collectionsHeap.add(collection);
//              isCollectionInHeap.add(collection);
          } else {
              FileCollection existCollection = collectionStore.get(cName);
              existCollection.setSize(existCollection.getSize() + size);
              List<File> updatedFileList = new ArrayList<>(existCollection.getFiles());
              updatedFileList.add(file);
              existCollection.setFiles(updatedFileList);
              collectionStore.put(cName, existCollection);
              manageQueue(size, cName);
          }
      }
      this.size += size;
  }

    private void manageQueue(int size, String cName) {
        PriorityQueue<FileCollection> tempHeap = new PriorityQueue<>( (a, b) ->
                a.getSize() == b.getSize()
                        ? a.getCollectionName().compareTo(b.getCollectionName())
                        : b.getSize() - a.getSize());
        while(!collectionsHeap.isEmpty()) {
            FileCollection fc = collectionsHeap.poll();
            if(fc.getCollectionName().equals(cName)) {
                fc.setSize(size + fc.getSize());
            }
            tempHeap.add(fc);
        }
        while (!tempHeap.isEmpty()) {
            collectionsHeap.add(tempHeap.poll());
        }
    }


}
