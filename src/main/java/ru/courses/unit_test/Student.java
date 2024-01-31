package ru.courses.unit_test;

import lombok.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



@ToString @EqualsAndHashCode
public class Student {
    @Setter
    private StudentRepo repo;
    @Getter @Setter
    private String name;
    private List<Integer> marks= new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

//    public void addMark(int mark){
//        if (mark < 2 || mark > 5) throw new IllegalArgumentException("wrong mark " + mark);
//        marks.add(mark);
//    }


    public List<Integer> getMarks() {
        List<Integer> marks= new ArrayList<>();
        for(Integer mark: this.marks){
            marks.add(mark);
        }
        return marks;
    }

    @SneakyThrows
    public void addMark(int mark) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:5352/checkGrade?grade="+mark);
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        HttpEntity entity = httpResponse.getEntity();
        if(!Boolean.parseBoolean(EntityUtils.toString(entity))){
            throw new IllegalArgumentException(mark + " is wrong mark");
        }
        marks.add(mark);
    }

    @SneakyThrows
    public int raiting(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:5352/educ?sum="+marks.stream().mapToInt(x->x).sum());
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        HttpEntity entity = httpResponse.getEntity();
        return Integer.parseInt(EntityUtils.toString(entity));
    }

    //    @SneakyThrows
//    public int raiting(){
//        /*Connection connection = DriverManager.getConnection("jdbs:postgresql://localhost/StudentDB");
//        ResultSet rs = connection.createStatement().executeQuery("select and others staff");
//        rs.next();
//        return rs.getInt("pos");*/
//
//        return repo.getRaintingForGradeSum(
//            marks.stream()
//                    .mapToInt(x->x)
//                    .sum()
//        );
//
//    }


}
