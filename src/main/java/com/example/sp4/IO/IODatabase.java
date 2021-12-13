package com.example.sp4.IO;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;

import java.sql.*;
import java.util.*;

/*
Klik for at se databasens sql: https://ghostbin.com/cehZs
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
    private HashMap<String, String> questionTitleAndDescription = new HashMap<>();

    /*
    @Override
    public Survey[] read() throws Exception {
        String surveyTitle = "";
        String surveyDescription = "";
        String questionName = "";
        String questionDescription = "";

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement st = conn.createStatement();

        // Get survey table
        String sql = "select * from survey";
        ResultSet rs = st.executeQuery(sql);

        List<Survey> surveys = new ArrayList<Survey>(); //hellig

        while (rs.next()) {
            surveyTitle = rs.getString("titleOfSurvey");
            surveyDescription = rs.getString("descriptionOfSurvey");

            System.out.println(surveyTitle);

            //Get question table
            String sql1 = "select * from question";
            rs =  st.executeQuery(sql1);

            while (rs.next()){
                questionName = rs.getString("questionTitle");
                questionDescription = rs.getString("questionDescription");


                //Get answer table
                String sql2 = "select * from answer";
                rs =  st.executeQuery(sql2);
                while (rs.next()){
                    String answer1 = rs.getString("answer1");
                    String answer2 = rs.getString("answer2");
                    String answer3 = rs.getString("answer3");
                    String answer4 = rs.getString("answer4");
                    String answer5 = rs.getString("answer5");
                    String[] answers = {answer1, answer2, answer3, answer4, answer5};

                    MultipleChoice multipleChoice = new MultipleChoice(questionName, questionDescription, answers);
                    ArrayList<Question> questions = new ArrayList<>();

                    questions.add(multipleChoice);
                    Survey survey = new Survey(surveyTitle, surveyDescription, questions);

                    surveys.add(survey);
                }
            }
        }

        Survey[] convertSurveys = new Survey[surveys.size()];
        convertSurveys = surveys.toArray(convertSurveys);

        return convertSurveys;
    }*/

    @Override
    public ArrayList<Survey> read() {
        ArrayList<Survey> surveys = new ArrayList<>();
        for(int i = 0; i <= sizeOfTable("survey"); i++){
            surveys.add(read(String.valueOf(i)));
        }
        return surveys;
    }

    public boolean isNumber(String myString){
        try{
            int x = Integer.parseInt(myString);
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }

    @Override
    public Survey read(String titleOfSurvey) {
        int id = -1;
        if(isNumber(titleOfSurvey) == true){
            id = Integer.parseInt(titleOfSurvey);
        }else{
            id = getIdOfSurvey(titleOfSurvey);
        }
        Survey survey = null;
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

            String questionTitle = "";
            String questionDescription = "";
            while (rs.next()){
                questionTitle = rs.getString("questionTitle");
                questionDescription = rs.getString("questionDescription");
                questionTitleAndDescription.put(questionTitle, questionDescription);
            }

            /* Få variablerne til answer table */

            ArrayList<Question> questions = new ArrayList<>();
            HashMap<String, String> hashmapAnswers = new HashMap<>();

            String sql2 = "select * from answer where question_id = " + id;
            rs = st.executeQuery(sql2);

            while (rs.next()){
                getAnswer1 = rs.getString("answer1");
                getAnswer2 = rs.getString("answer2");
                getAnswer3 = rs.getString("answer3");
                getAnswer4 = rs.getString("answer4");
                getAnswer5 = rs.getString("answer5");
                hashmapAnswers.put(questionTitle.toLowerCase(Locale.ROOT), getAnswer1);
                hashmapAnswers.put(questionTitle.toLowerCase(Locale.ROOT), getAnswer2);
                hashmapAnswers.put(questionTitle.toLowerCase(Locale.ROOT), getAnswer3);
                hashmapAnswers.put(questionTitle.toLowerCase(Locale.ROOT), getAnswer4);
                hashmapAnswers.put(questionTitle.toLowerCase(Locale.ROOT), getAnswer5);
            }

            String[] convertAnswers = {};
            for(Map.Entry<String, String> i : questionTitleAndDescription.entrySet()){
                ArrayList<String> testerArray = new ArrayList<>();
                for(Map.Entry<String, String> b : hashmapAnswers.entrySet()){
                    if(b.getKey().toLowerCase(Locale.ROOT).equals(i.getKey().toLowerCase(Locale.ROOT))){
                        testerArray.add(b.getValue());
                        convertAnswers = new String[testerArray.size()];
                        convertAnswers = testerArray.toArray(convertAnswers);
                    }
                }

                MultipleChoice multipleChoice = new MultipleChoice(i.getKey(), i.getValue(), convertAnswers);
                questions.add(multipleChoice);
            }

            /* */

            survey = new Survey(titleOfSurvey, descriptionOfSurvey, questions, 0);
            survey.setFromDB(true);
            /* */

            System.out.println(titleOfSurvey + "\n" +
                    descriptionOfSurvey + "\n" + questionTitleAndDescription);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return survey;
    }

    public void deleteSurvey(String titleOfSurvey){
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            String sql = "DELETE FROM survey WHERE id = " + getIdOfSurvey(titleOfSurvey);
            st.execute(sql);
        }catch (SQLException e){
            System.out.println(e);
        }
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

    public int sizeOfTable(String tableName){
        int size = -1;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from " + tableName);
            size = 0;
            while (rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public void save(Survey survey) {
        //Laves når survey er færdig
    }

    @Override
    public void remove(Survey survey) {
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
