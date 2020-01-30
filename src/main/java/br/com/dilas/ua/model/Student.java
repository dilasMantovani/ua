package br.com.dilas.ua.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Student {

    private int id;
    private String name;
    public static List<Student> studentList;

    static{
        studentRepository();
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Student( String name){
        this.name = name;
    }
    public Student(){
    }

    public int getId() {
        return id;
    }

    private static void studentRepository(){
        studentList = new ArrayList<>(asList(new Student(1,"Deku"), new Student(2,"Todoroki"), new Student(3,"Bakugo")));
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
