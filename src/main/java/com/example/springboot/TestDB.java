package com.example.springboot;

public class TestDB{
  private short my_int;

  public TestDB(short my_int) {
    this.my_int = my_int;
  }

  @Override
  public String toString() {
    return String.format(
        "Test[my_int=%d]",
        my_int);
  }

}
