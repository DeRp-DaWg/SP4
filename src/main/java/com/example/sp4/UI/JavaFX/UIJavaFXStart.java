package com.example.sp4.UI.JavaFX;

import com.example.sp4.Survey;
import com.example.sp4.UI.UIStart;

public class UIJavaFXStart extends UIJavaFX implements UIStart {
    private String IOType;

    @Override
    public Survey UIShowStart(Survey[] surveys) {
        return null;
    }

    public String getIOType() {
        return IOType;
    }
}
