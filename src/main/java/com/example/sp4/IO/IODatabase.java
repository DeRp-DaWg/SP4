package com.example.sp4.IO;

import com.example.sp4.Survey;

import java.sql.*;
import java.util.ArrayList;

/*
Klik for at se databasens sql: https://ghostbin.com/wp8op
 */

public class IODatabase implements IO{
    private Connection conn = null;
    // database URL
    final String DB_URL = "jdbc:mysql://localhost/Test";

    //  Database credentials
    private final String USER = "root";
    private String PASS = "test";

    private String getAnswer1 = null;
    private String getAnswer2 = null;
    private String getAnswer3 = null;
    private String getAnswer4 = null;
    private String getAnswer5 = null;
    private String titleOfSurvey = null; //name
    private String descriptionOfSurvey = null;
    private ArrayList<String> questionTitle = new ArrayList<>();

    @Override
    public Survey read(String titleOfSurvey) {
        int id = getIdOfSurvey(titleOfSurvey);
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement st = conn.createStatement();

            /* Få variablerne til survey table */

            String sql = "select * from survey where id = " + id;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                titleOfSurvey = rs.getString("titleOfSurvey");
                descriptionOfSurvey = rs.getString("descriptionOfSurvey");
            }


            /* Få variablerne til question table */

            String sql1 = "select * from question where survey_id = " + id;
            rs = st.executeQuery(sql1);

            while (rs.next()){
                questionTitle.add(rs.getString("questionTitle"));
            }

            /* Få variablerne til answer table */

            String sql2 = "select * from answer where question_id = " + id;
            rs = st.executeQuery(sql2);

            while (rs.next()){
                getAnswer1 = rs.getString("answer1");
                getAnswer2 = rs.getString("answer2");
                getAnswer3 = rs.getString("answer3");
                getAnswer4 = rs.getString("answer4");
                getAnswer5 = rs.getString("answer5");
            }

            System.out.println(titleOfSurvey + "\n" +
                    descriptionOfSurvey + "\n" + questionTitle + "\n" + getAnswer1 + "\n" +
                    getAnswer2 + "\n" +
                    getAnswer3 + "\n" +
                    getAnswer4 + "\n" +
                    getAnswer5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getIdOfSurvey(String titleOfSurvey){
        int myId = -1;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement st = conn.createStatement();

            /* Få variablerne til survey table */

            String sql = "select id from survey where lower(titleOfSurvey) = \"" + titleOfSurvey.toLowerCase() + "\"";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                myId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myId;
    }

    @Override
    public void save(Survey survey) {
        //Laves når survey er færdig
    }
    /*
    public void uploadToQuestion(String questionTitle, String questionDescription, String email){
        //Question table contains: id(auto increments), questionTitle, questionDescription, email
        String sql = "INSERT INTO question (questionTitle, questionDescription, email)"
                + "VALUES (?, ?, ?)";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, questionTitle);
            pstmt.setString(2, questionDescription);
            pstmt.setString(3, email);

            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadAnswers(ArrayList<String> getAnswers){
        //question_id, answer1, answer2, answer3, answer4, answer5
        String sql = "INSERT INTO answer (question_id, answer1, answer2, answer3, answer4, answer5)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, 1);

            int counter = 2;
            for (String i : getAnswers) {
                pstmt.setString(counter, i);
                counter++;
            }
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     */
}
