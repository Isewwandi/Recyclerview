package com.techleadintl.android.user;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {

    private static List<Model> modelsList = new ArrayList<Model>();
    private static int nextId = 100;
    public MyApplication(){
        fillModelList();
    }
    private void fillModelList() {

        Model p0 = new Model(0,"ishara","ishara@gmail.com","15","hello");
        Model p1 = new Model(1,"sewwandi","sewwandi@gmail.com","22","hello");
        modelsList.addAll(Arrays.asList(new Model[] {p0,p1}));
    }
    public static List<Model> getModelsList() {
        return modelsList;
    }

    public static void setModelsList(List<Model> modelsList) {
        MyApplication.modelsList = modelsList;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        MyApplication.nextId = nextId;
    }
}
