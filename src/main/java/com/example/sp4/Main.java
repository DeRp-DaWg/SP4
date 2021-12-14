package com.example.sp4;

import com.example.sp4.IO.IODatabase;
import com.example.sp4.UI.JavaFX.UIJavaFXMain;

public class Main {
    public static void main(String[] args) throws Exception {
        //Survey survey = new Survey();

        IODatabase ioDatabase = new IODatabase();
        //System.out.println(ioDatabase.read("age survey"));
        //System.out.println(ioDatabase.getIdOfSurvey("Age survey"));
        //ioDatabase.read();
        UIJavaFXMain uiJavaFXMain = new UIJavaFXMain();
        uiJavaFXMain.launchButton();

        //Controller controller = new Controller();
      //Anders test
       // HelloApplication helloApplication = new HelloApplication();
       // helloApplication.hello = "Oh No";
    }
}
