/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class Student {

    String sid;
    String name;
    Double mark;

    public Student() {
    }

    public Student(String sid, String name, Double mark) {
        this.sid = sid;
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" + "sid=" + sid + ", name=" + name + ", mark=" + mark + '}';
    }

}
