package com.example.phonenumberauth;

public class RandomObj {

    String id;
    String string1;
    String string2;

    public RandomObj(String id, String string1, String string2) {
        this.id = id;
        this.string1 = string1;
        this.string2 = string2;
    }

    public String getId() {
        return id;
    }

    public String getString1() {
        return string1;
    }

    public String getString2() {
        return string2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }
}
