package com.atlassian.code;

public class Application {

  /***
   * Problem Title: Rate Limiter
   *
   * Problem Description:
   *
   * Imagine we are building an application that is used by many different customers.
   * We want to avoid one customer being able to overload the system by sending too
   * many requests, so we enforce a per-customer rate limit.
   * The rate limit is defined as:
   *
   * “Each customer can make X requests per Y seconds
   *
   * ”*/

  /**
   * // Perform rate limiting logic for provided customer ID. Return true if the // request is
   * allowed, and false if it is not. boolean rateLimit(int customerId)
   */

  /**
   * - The total size of all files stored; and
   *
   * <p>- The top N collections (by file size) where N can be a user-defined value
   *
   * <p>file1.txt (size: 100)
   * file2.txt (size: 200) in collection "collection1"
   * file3.txt (size:200) in collection "collection1"
   *
   * file4.txt (size: 300) in collection "collection2"
   * file5.txt
   * (size: 10)
   *
   */
  public static String get() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    System.out.println(get());
  }
}
