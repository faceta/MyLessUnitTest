package ru.courses.unit_test;

public interface StudentRepo {
    int getRaintingForGradeSum(int sum);
    long count();
    void delete(Student emtity);
    void deleteAll(Iterable<Student> entities);
    Iterable<Student> findAll();
    Student save(Student entity);
    Iterable<Student> saveAll(Iterable<Student> entities);
}
