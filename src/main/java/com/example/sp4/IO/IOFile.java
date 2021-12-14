package com.example.sp4.IO;

import com.example.sp4.Comparators.SurveyFileComparator;
import com.example.sp4.Survey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
            
            survey.setId(Long.parseLong(name.split("Survey")[1]));
            System.out.println(survey.getId());
    
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
            survey.setId(filesInFolder.length);
            
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
    public void remove(ArrayList<Survey> surveys, Survey survey) {
        File folder = new File("surveys/");
        File[] filesInFolder = folder.listFiles();
        String[] fileNames = new String[filesInFolder.length];
        for (int i = 0; i < filesInFolder.length; i++) {
            String fileName = filesInFolder[i].getName();
            fileName = fileName.split("\\.")[0];
            fileNames[i] = fileName;
        }
        Arrays.sort(fileNames, new SurveyFileComparator());
        for (String str : fileNames) {
            System.out.print(str);
        }
        System.out.println();
        int index = (int) survey.getId();
        String[] fileNamesToBeChanged;
        try {
            fileNamesToBeChanged = Arrays.copyOfRange(fileNames, index+1, fileNames.length);
        }
        catch (Exception e) {
            fileNamesToBeChanged = new String[0];
        }
        File file = new File("surveys/Survey" + survey.getId() + ".ser");
        if (file.delete()) {
            for (int i = 0; i < fileNamesToBeChanged.length; i++) {
                String fileName = "surveys/"+fileNamesToBeChanged[i]+".ser";
                int newSurveyInt = Integer.parseInt(fileNamesToBeChanged[i].split("Survey")[1]);
                surveys.get(newSurveyInt).setId(newSurveyInt-1);
                String newFileName = "surveys/Survey" + (newSurveyInt-1) + ".ser";
                System.out.println(newFileName);
                File f = new File(fileName);
                f.renameTo(new File (newFileName));
            }
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
