package com.example.sp4.UI;

import com.example.sp4.Survey;

public class UI {
    private UIStart uiStart;
    private UIAnswer uiAnswer;
    private UICreate uiCreate;

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
        return uiStart.UIShowStart();
    }

    public Survey answer(Survey survey) {
        uiAnswer.UIShowAnswer(survey);
        return null;
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
