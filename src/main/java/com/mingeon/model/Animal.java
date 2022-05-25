package com.mingeon.model;

import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal {

    private final String name;
    @Max(10)
    private final int age;

    @JsonCreator
    public Animal(@JsonProperty("name") String name, @JsonProperty("age") int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal [age=" + age + ", name=" + name + "]";
    }

}
