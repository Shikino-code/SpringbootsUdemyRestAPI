package com.in28minutes.rest.webservices.restfulwebservices.Versioning;

public class PersonV1 {
    private final String name;

    public PersonV1(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }
}



