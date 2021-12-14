package com.example.sp4;

import com.example.sp4.IO.IO;
import com.example.sp4.IO.IODatabase;
import com.example.sp4.IO.IOFile;
import com.example.sp4.UI.CommandLine.UICommandLineStart;
import com.example.sp4.UI.JavaFX.UIJavaFXMain;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            Application.launch(UIJavaFXMain.class);
        }
        else {
            for (String arg : args) {
                if (arg.equals("-cmd")) {
                    UICommandLineStart ui = new UICommandLineStart();
                    IO io = new IOFile();
                    ArrayList<Survey> surveys = io.read();
                    ui.UIShowStart(surveys);
                    for (Survey survey : surveys) {
                        io.save(survey);
                    }
                }
            }
        }
    }
}
