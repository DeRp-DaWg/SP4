package com.example.sp4.IO;

import com.example.sp4.Survey;

import java.io.*;
import java.util.ArrayList;

public class IOFile implements IO {
    
    @Override
    public ArrayList<Survey> read() throws Exception {
        File folder = new File("surveys/");
        File[] filesInFolder = folder.listFiles();
        String[] fileNames = new String[filesInFolder.length];
        for (int i = 0; i < fileNames.length; i++) {
            String fileName = filesInFolder[i].getName();
            fileName = fileName.split("\\.")[0];
            fileNames[i] = fileName;
        }
        ArrayList<Survey> surveys = new ArrayList<>(filesInFolder.length);
        for (int i = 0; i < fileNames.length; i++) {
            surveys.add(read(fileNames[i]));
        }
        return surveys;
    }
    
    @Override
    public Survey read(String name) throws Exception {
        Survey survey = null;
        try {
            FileInputStream file = new FileInputStream("surveys/"+name+".ser");
            ObjectInputStream in = new ObjectInputStream(file);
    
            survey = (Survey) in.readObject();
    
            in.close();
            file.close();
    
            System.out.println("Survey has been deserialized");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return survey;
    }
    
    @Override
    public void save(Survey survey) {
        try {
            File folder = new File("surveys/");
            File[] filesInFolder = folder.listFiles();
            
            FileOutputStream file = new FileOutputStream("surveys/Survey"+filesInFolder.length+".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
        
            out.writeObject(survey);
        
            out.close();
            file.close();
        
            System.out.println("Survey has been serialized");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void remove(Survey survey) {
        File file = new File("surveys/" + survey.getSurveyTitle() + ".ser");
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    
    @Override
    public void update(Survey survey) {
        try {
            FileOutputStream file = new FileOutputStream("surveys/Survey"+survey.getId()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
        
            out.writeObject(survey);
        
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
