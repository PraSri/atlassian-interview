package com.atlassian.datastructure;

import com.atlassian.datastructure.dto.File;
import com.atlassian.datastructure.dto.FileCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {
  FileSystem fs;

  @BeforeEach
  void setUp() {
    fs = new FileSystem();
    fs.addFileToSystem("file1.txt", 100, Optional.empty());
    fs.addFileToSystem("file2.txt", 200, Optional.of("Collection1"));
    fs.addFileToSystem("file3.txt", 200, Optional.of("Collection1"));
    fs.addFileToSystem("file4.txt", 300, Optional.of("Collection2"));
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getAllFileSize() {
    assertEquals(800, fs.getAllFileSize());
  }

  @Test
  void getTopNCollections() {
    List<FileCollection> ex = fs.getTopNCollections(2);
    System.out.println(ex.get(0).getCollectionName() + " " + ex.get(1).getCollectionName());
  }
}
