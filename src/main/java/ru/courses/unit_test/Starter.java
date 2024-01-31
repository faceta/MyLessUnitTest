package ru.courses.unit_test;

import java.lang.reflect.Method;

public class Starter {
    public static void main(String[] args) {
        Student student = new Student("pete");
        student.addMark(3);
        student.addMark(2);
        student.addMark(5);
        System.out.println(student);

    }
}
