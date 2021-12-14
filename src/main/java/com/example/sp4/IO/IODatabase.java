package com.example.sp4.IO;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;

import java.sql.*;
import java.util.*;

/*
Klik for at se databasens sql: https://ghostbin.com/cehZs
 */

public class IODatabase implements IO {
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
    private HashMap<String, String> questionTitleAndDescription = new HashMap<>();



    @Override
    public ArrayList<Survey> read() throws Exception {
        ArrayList<Survey> surveys = new ArrayList<>();
        for (int i = 0; i <= sizeOfTable("survey"); i++) {
            surveys.add(read(String.valueOf(i)));
        }
        return surveys;
    }

    public boolean isNumber(String myString) {
        try {
            int x = Integer.parseInt(myString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    private Statement st;
    private Statement st1;
    private Statement st2;
    private ResultSet rs;
    private ResultSet rs1;
    private ResultSet rs2;

    @Override
    public Survey read(String titleOfSurvey) throws Exception {
        int id = -1;
        if (isNumber(titleOfSurvey) == true) {
            id = Integer.parseInt(titleOfSurvey);
        } else {
            id = getIdOfSurvey(titleOfSurvey);
        }
        Survey survey = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            st = conn.createStatement();
            st1 = conn.createStatement();
            st2 = conn.createStatement();

            String questionTitle = "";
            String questionDescription = "Under ombygning";

            ArrayList<Question> questions = new ArrayList<>();

            String sql = "select * from survey where id = "  + id;
            rs = st.executeQuery(sql);
            while(rs.next()){
                titleOfSurvey = rs.getString("titleOfSurvey");
                descriptionOfSurvey = rs.getString("descriptionOfSurvey");

                String sql1 = "select * from question where survey_id = "  + id;
                rs1 = st1.executeQuery(sql1);
                while (rs1.next()){
                    questionTitle = rs1.getString("questionTitle");
                    questionDescription = rs1.getString("questionDescription");
                    int questionID = rs1.getInt("id");

                    //Iterate through each question

                    String sql2 = "select * from answer where question_id = "  + questionID;
                    ResultSet rs2 = st2.executeQuery(sql2);
                    while (rs2.next()){
                        String answer1 = rs2.getString("answer1");
                        String answer2 = rs2.getString("answer2");
                        String answer3 = rs2.getString("answer3");
                        String answer4 = rs2.getString("answer4");
                        String answer5 = rs2.getString("answer5");


                        String[] answerArray = {answer1, answer2, answer3, answer4, answer5};
                        MultipleChoice multipleChoice = new MultipleChoice(questionTitle, questionDescription, answerArray);
                        questions.add(multipleChoice);
                    }
                }
            }

            /* */


            survey = new Survey(titleOfSurvey, descriptionOfSurvey, questions, 0);
            survey.setFromDB(true);
            /* */


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return survey;
    }


    public int getIdOfSurvey(String titleOfSurvey) {
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

    public int sizeOfTable(String tableName) throws Exception {
        int size = -1;
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from " + tableName);
        size = 0;
        while (rs.next()) {
            size = rs.getInt(1);
        }
        return size;
    }

    @Override
    public void save(Survey survey) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Survey
            String sql = "INSERT INTO survey (titleOfSurvey, descriptionOfSurvey)"
                    + "VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, survey.getSurveyTitle());
            pstmt.setString(2, survey.getSurveyDescription());

            pstmt.addBatch();
            pstmt.executeBatch();

            //Question
            sql = "INSERT INTO question (questionTitle, questionDescription, survey_id)"
                    + "VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for(Question i : survey.getQuestions()){
                pstmt.setString(counter, i.getQuestionName());
                counter++;
                pstmt.setString(counter, i.getQuestionDescription());
                counter++;
                pstmt.setInt(counter, getIdOfSurvey(survey.getSurveyTitle()));
            }
            pstmt.addBatch();
            pstmt.executeBatch();



            /*
            //Answer
            sql = "INSERT INTO answer (question_id, answer1, answer2, answer3, answer4, answer5)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            foriegnkey constrainst thingy

            pstmt = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0", Statement.RETURN_GENERATED_KEYS);
            pstmt.addBatch();



            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, 6);
            pstmt.setString(2, "ansewer1");
            pstmt.setString(3, "ansewer1");
            pstmt.setString(4, "ansewer1");
            pstmt.setString(5, "ansewer1");
            pstmt.addBatch();
            int testCounter = 2;

            pstmt = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1", Statement.RETURN_GENERATED_KEYS);
            pstmt.addBatch();


            for(Question i : survey.getQuestions()){
                for (String b : i.getAnswers().keySet()){
                    System.out.println(b);
                    pstmt.setString(testCounter, b);
                    pstmt.addBatch();
                }
            }
            pstmt.executeBatch();
            */


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(ArrayList<Survey> surveys, Survey survey) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            String sql = "DELETE FROM survey WHERE id = " + getIdOfSurvey(survey.getSurveyTitle());
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Survey survey) {

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
