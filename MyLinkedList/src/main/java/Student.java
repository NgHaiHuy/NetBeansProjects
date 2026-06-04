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
    double mark;

    public Student() {
    }

    public Student(String sid, String name, double mark) {
        this.sid = sid;
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{sid='" + sid + "', name='" + name + "', mark='" + mark + "'}";
    }

    // Getters and Setters
    public String getSid() { return sid; }
    public void setSid(String sid) { this.sid = sid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getMark() { return mark; }
    public void setMark(double mark) { this.mark = mark; }

}
