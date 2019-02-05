package com.venkat.enity;

import java.io.InputStream;
import java.io.Serializable;

public class Resume implements Serializable {

    private int id;
    private String name;
    private InputStream content;

    public Resume(){}

    public Resume(String name, InputStream content) {
        this.name = name;
        this.content = content;
    }

    public Resume(int id, String name, InputStream content) {
        this(name, content);
        this.id = id;
    }

    public Resume(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InputStream getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }
}
