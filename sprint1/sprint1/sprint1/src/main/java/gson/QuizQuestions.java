package gson;

import java.util.Arrays;
import java.util.Date;

public class QuizQuestions {
    private String classId ;
    private String endDate;
    private String Qname;
    private String[] questions;
    private String[] choice1 ;
    private String[] choice2 ;
    private String[] choice3 ;
    private String[] choice4 ;
    private int[] answers ;

    public int[] getAnswers() {
        return answers;
    }

    public String[] getChoice1() {
        return choice1;
    }

    public String getClassId() {
        return classId;
    }

    public String getEndDate() {
        return endDate;
    }

    public String[] getChoice2() {
        return choice2;
    }

    public String[] getChoice3() {
        return choice3;
    }

    public String[] getChoice4() {
        return choice4;
    }

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public void setAnswers(int[] answers) {
        this.answers = answers;
    }

    public void setChoice1(String[] choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String[] choice2) {
        this.choice2 = choice2;
    }

    public void setChoice3(String[] choice3) {
        this.choice3 = choice3;
    }

    public void setChoice4(String[] choice4) {
        this.choice4 = choice4;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setQname(String qname) {
        Qname = qname;
    }

    public String getQname() {
        return Qname;
    }

    public String ToString() {
        return "\"" + this.classId + "\"," +
                "\"" + this.endDate + "\"," +
                "\"" + this.Qname + "\"," +
                "\"" + Arrays.toString(this.questions) + "\""+
        "\"" + Arrays.toString(this.choice1) + "\""+
        "\"" + Arrays.toString(this.choice2) + "\""+
        "\"" + Arrays.toString(this.choice3) + "\""+
        "\"" + Arrays.toString(this.choice4) + "\"";

    }
}
