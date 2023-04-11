package com.bosonit.formacion.block6simplecontrollers;

public class Persona {

    String name, location, age;

    public Persona() {
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
