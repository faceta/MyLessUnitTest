package ru.courses.unit_test;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.CacheResponse;
import java.util.List;


//@WireMockTest(httpPort = 5352)
public class WebTests {
    // Заменяем на @WireMockTest
    WireMockServer wireMokeServer;


    @BeforeEach
    void starter(){
        wireMokeServer = new WireMockServer(5352);
        wireMokeServer.start();

    }

    @AfterEach
    void stopper(){
        wireMokeServer.stop();
    }

    @Test
    public void testRaiting(){
        configureFor("localhost", 5352);
        stubFor(get(urlMatching("/educ\\?sum=([0-9]*)"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("42"))
        );
        Student student = new Student("vasya");
        Assertions.assertEquals(42, student.raiting());
    }

    @Test
    public void testAddMarks(){
        List<Integer> lstMarks = List.of(1, 4, 6 ,3);
        Student student = new Student("vasya");
        configureFor("localhost", 5352);
        for (Integer mark: lstMarks) {
            if (mark >= 2 && mark <= 5) {
                stubFor(get(urlMatching("/checkGrade\\?grade=([0-9]*)"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("true"))
                );
            } else {
                stubFor(get(urlMatching("/checkGrade\\?grade=([0-9]*)"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("false"))
                );
            }
            if (mark == 4) {
                student.addMark(mark);
                Assertions.assertEquals(4, student.getMarks().get(0));
            };
            if (mark == 1){
                try {
                    student.addMark(mark);
                } catch (IllegalArgumentException ex){
                    Assertions.assertEquals(Integer.toString(mark)+" is wrong mark", ex.getMessage());
                }
            }
        }
    }
}
