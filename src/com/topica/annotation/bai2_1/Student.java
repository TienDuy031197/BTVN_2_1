package com.topica.annotation.bai2_1;

import java.lang.reflect.Field;

public class Student {

	private String name;

	private int age;

	private String address;

	@setValueStaticFinal(updateValueString = "Data update")
	public static final String data = "Data";

	@setValueStaticFinal(updateValueInteger = 4)
	public static final int ages = 2;

	@setValueStaticFinal(updateValueString = "new day data update")
	public static final String newData = "new day";

	public static String getDataReflectionSt(String d) {
		try {
			final Field fld = Student.class.getDeclaredField(d);
			return (String) fld.get(null);
		} catch (NoSuchFieldException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	public static Integer getDataReflectionIn(String d) {
		try {
			final Field fld = Student.class.getDeclaredField(d);
			return (Integer) fld.get(null);
		} catch (NoSuchFieldException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	public static String getData() {
		return data;
	}

	public static Integer getAges() {
		return ages;
	}

	public static String getNewData() {
		return newData;
	}

	public Student(String name, Integer age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
