package com.example.sp4.IO;

import com.example.sp4.Survey;

import java.io.*;

public class IOFile implements IO {
    
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
            FileOutputStream file = new FileOutputStream("surveys/"+survey.getName()+".ser");
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
