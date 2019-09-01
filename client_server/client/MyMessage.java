package com.example.itaykan.myapplication;

import java.io.Serializable;

/**
 * Created by Itay kan on 9/1/2019.
 */

public class MyMessage implements Serializable {

    private String value;
    private int id;

    public MyMessage() {
        super();
    }

    public MyMessage(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyMessage [id=" + id + ", value=" + value + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}