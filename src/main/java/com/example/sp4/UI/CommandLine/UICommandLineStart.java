package com.example.sp4.UI.CommandLine;

import com.example.sp4.Survey;
import com.example.sp4.UI.UIStart;

public class UICommandLineStart extends UICommandLine implements UIStart {
    private String IOType;

    @Override
    public Survey UIShowStart() {
        return null;
    }

    public String getIOType() {
        return IOType;
    }
}
