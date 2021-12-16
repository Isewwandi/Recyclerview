package com.techleadintl.android.user;

import android.app.Application;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {

    private static List<model> modelsList = new ArrayList<model>();
    private static int nextId = 100;

    public MyApplication(){
        fillModelList();
    }

    private void fillModelList() {

        model p0 = new model(0,"ishara","ishara@gmail.com","15","hello");
        model p1 = new model(1,"ish","ishara@gmail.com","15","hello");
//        model p2 = new model(2,"sew","ishara@gmail.com","15","hello");
//        model p3 = new model(3,"ishara","ishara@gmail.com","15","hello");

        modelsList.addAll(Arrays.asList(new model[] {p0,p1}));

    }



    public static List<model> getModelsList() {
        return modelsList;
    }

    public static void setModelsList(List<model> modelsList) {
        MyApplication.modelsList = modelsList;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        MyApplication.nextId = nextId;
    }
}
