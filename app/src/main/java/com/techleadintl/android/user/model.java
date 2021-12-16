package com.techleadintl.android.user;

public class model {

    private int id;
   private String name;
   private String email;
   private String age;
   private String note;

    public model(int id, String name, String email, String age, String note) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.note = note;
    }

    @Override
    public String toString() {
        return "model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
