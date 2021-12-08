package com.example.sp4;

import com.example.sp4.IO.IO;
import com.example.sp4.IO.IODatabase;
import com.example.sp4.IO.IOFile;
import com.example.sp4.UI.UI;

public class Controller {
    UI ui = new UI();
    IO io = new IOFile();
    Survey survey;

    //TODO: Find en måde at vælge command line eller GUI med JavaFX måske inden man starter programmet.
    //      Det kunne gøres med argumenter fra Main.
    public Controller() {
        programLoop();
    }

    private void programLoop() {
        while (true) {
            //TODO: Gør så man kan skifte IO og genindlæs så de viste surveys med den type af IO.
            //Vis start vinduet/dialoget. Derefter få fat i typen af IO som bliver specificeret i vinduet/dialoget i UIStart.
            Survey[] surveys = null;
            try {
                surveys = io.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
            survey = ui.start(surveys);
            switch (ui.getIOType()) {
                case "file":
                    io = new IOFile();
                    break;
                case "db":
                    io = new IODatabase();
                    break;
            }

            //Hvis survey ikke er null, så skal den survey besvares. Hvis den er null så er det fordi brugeren vil lave en.
            if (survey != null) {
                survey = ui.answer(survey);
            }
            else {
                survey = ui.create();
            }
            io.save(survey);
        }
    }
}
