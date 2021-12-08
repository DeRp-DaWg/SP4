package com.example.sp4.UI;

import com.example.sp4.Survey;
import com.example.sp4.UI.CommandLine.UICommandLineStart;
import com.example.sp4.UI.CommandLine.UICommandLineAnswer;
import com.example.sp4.UI.CommandLine.UICommandLineCreate;

public class UI {
    private UIStart uiStart = new UICommandLineStart();
    private UIAnswer uiAnswer = new UICommandLineAnswer();
    private UICreate uiCreate = new UICommandLineCreate();

    /*
    public void showCMD() {
        uiStart = new UICommandLineStart();
        uiAnswer = new UICommandLineAnswer();
        uiCreate = new UICommandLineCreate();
    }
    public void showGUI() {
        uiStart = new UIJavaFXStart();
        uiAnswer = new UIJavaFXAnswer();
        uiCreate = new UIJavaFXCreate();
    }
    */
    public Survey start(Survey[] surveys) {
        return uiStart.UIShowStart(surveys);
    }

    public Survey answer(Survey survey) {
        return uiAnswer.UIShowAnswer(survey);
    }

    public Survey create() {
        return uiCreate.UIShowCreate();
    }

    public String getIOType() {
        return uiStart.getIOType();
    }
    
    
    //Starter med at vise uiStart.
    /*
    public Survey start() {
        Survey survey = uiStart.UIShowStart();
        if (survey == null) {
            survey = uiCreate.UIShowCreate();
        }
        if (survey != null) {
            uiAnswer.UIShowAnswer(survey);
        }
        return survey;
    }
    */
}
