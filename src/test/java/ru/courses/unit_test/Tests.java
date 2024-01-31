package ru.courses.unit_test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(EducationExtention.class)
public class Tests {
    //@BeforeEach - Запуск перед каждым тестом
    //@AfterEach - запуск после каждого теста
//    @BeforeEach
//    public void createFile(){
//        System.out.println("create file");
//    }
//
//    @AfterEach
//    public void deleteFile(){
//        System.out.println("delete file");
//    }
    /*
    @Test
    public void testRaiting(){
        Student student = new Student("vasya");
        student.addMark(4);
        student.setRepo(new StudentRepositoryMock());
        Assertions.assertEquals(10, student.raiting());
    }
*/
//    @Test
//    public void testRaiting(){
//        Student student = new Student("vasya");
//        student.addMark(4);
//        StudentRepo repo = Mockito.mock(StudentRepo.class);
//        Mockito.when(repo.getRaintingForGradeSum(Mockito.anyInt()))
//                        .thenReturn(10);
//
//        student.setRepo(repo);
//        Assertions.assertEquals(10, student.raiting());
//    }

    @Test
    public void testGetMarks(){
        Student student = new Student("vasya");
        List<Integer> marks;
        marks = student.getMarks();
        marks.add(99);
        Assertions.assertNotEquals(marks, student.getMarks());
        Assertions.assertAll();
    }

    @RepeatedTest(value = 4, name = "Корректные оценки добавляются в список оценок")
    public void marksInRange(RepetitionInfo repetitionInfo){
        Student stud = new Student("vasia");
        int num = repetitionInfo.getCurrentRepetition()+1;
        stud.addMark(num);
        Assertions.assertEquals(stud.getMarks().get(0),num);
    }

    /*
    @Test
    @DisplayName("Добавление неверных оценок кидает исключение")
    public void marksNotInRange(){
        List<Integer> lst = List.of(0, 1, 6, 7);
        Student stud = new Student("vasia");
        Assertions.assertThrows(IllegalArgumentException.class, () -> stud.addMark(lst.get(0)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> stud.addMark(lst.get(1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> stud.addMark(lst.get(2)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> stud.addMark(lst.get(3)));
    }
    */

    @ParameterizedTest(name = "Добавление неверных оценок кидает исключение")
    @MethodSource("ru.courses.unit_test.MarksGenerator#ints")
    public void marksNotInRange(int x){
        List<Integer> lst = List.of(0, 1, 6, 7);
        Student stud = new Student("vasia");
        Assertions.assertThrows(IllegalArgumentException.class, () -> stud.addMark(x));

    }
}

class EducationExtention implements BeforeEachCallback{
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        //код здесь вызывается перед каждым методом класса Test (из-за анатации @ExtendWith(EducationExtention.class))
        System.out.println("test extention");
    }
}


