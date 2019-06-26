package com.topica.annotation.bai2_1;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Student student = new Student("Duy", 22, "Soc Son");
		Annotation1.setValueStaticFinal(student);
		System.out.println("Value after change of variable data: " + Student.getDataReflectionSt("data"));
		System.out.println("Value after change of variable ages: " + Student.getDataReflectionIn("ages"));

	}
}
