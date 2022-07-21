package com.atlassian.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @DisplayName("Test Hello World")
  @Test
  void testGet() {
    System.out.println("HERE TEST!!!");
    assertEquals("Hello World!", Application.get());
  }
}
