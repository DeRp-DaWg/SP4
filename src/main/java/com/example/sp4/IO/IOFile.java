package com.example.sp4.IO;

import com.example.sp4.Survey;

import java.io.*;

public class IOFile implements IO {
    
    @Override
    public Survey[] read() throws Exception {
        File folder = new File("surveys/");
        File[] filesInFolder = folder.listFiles();
        String[] fileNames = new String[filesInFolder.length];
        for (int i = 0; i < fileNames.length; i++) {
            String fileName = filesInFolder[i].getName();
            fileName = fileName.split("\\.")[0];
            fileNames[i] = fileName;
        }
        Survey[] surveys = new Survey[filesInFolder.length];
        for (int i = 0; i < fileNames.length; i++) {
            surveys[i] = read(fileNames[i]);
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
            FileOutputStream file = new FileOutputStream("surveys/"+survey.getSurveyTitle()+".ser");
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
}
