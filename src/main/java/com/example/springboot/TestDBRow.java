package com.example.springboot;

public class TestDBRow{
	private int my_int;

	public TestDBRow(int my_int) {
		this.my_int = my_int;
	}

	@Override
	public String toString() {
		return String.format(
				"Test[my_int=%d]",
				my_int);
	}

	public int my_int() {
		return this.my_int;
	}
}
