package com.example.springboot;

public class TestDB{
  private long my_int;

  public TestDB(long my_int) {
    this.my_int = my_int;
  }

  @Override
  public String toString() {
    return String.format(
        "Test[my_int=%d]",
        my_int);
  }

}
