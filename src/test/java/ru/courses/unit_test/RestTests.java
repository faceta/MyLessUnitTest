package ru.courses.unit_test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class RestTests {
    @Test
    public void testNameNotNull(){
        get("http://localhost:8080/getStudent")
                .then()
                .body("name",notNullValue());
    }

    @Test
    public void testGradesNotNull(){
        get("http://localhost:8080/wrongStudent")
                .then()
                .body("grades",notNullValue());
    }

    @Test
    public void testGrades(){
        get("http://localhost:8080/getStudent")
                .then()
                .body("grades", hasItems(2,5));
    }
}
