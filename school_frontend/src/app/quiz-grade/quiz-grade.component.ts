import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quiz-grade',
  templateUrl: './quiz-grade.component.html',
  styleUrls: ['./quiz-grade.component.css']
})
export class QuizGradeComponent implements OnInit {

  ngOnInit(): void {
  }

  constructor(private _router: Router, private http: HttpClient) {
    let id = localStorage.getItem("Id");
    this.teacherId = id;
    this.sendRequestClass(id)
  }

  teacherId = ""
  classes = [
  ]
  class = {
    "Id": [],
    "Name": []
  }
  chosenClass = ""
  chosenQuiz = ""
  getquiz = false

  answer = ""
  sendRequestClass(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/getInfo", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
          if(this.answer == "Error"){
            alert(this.answer);
          }else{
          this.class = JSON.parse(this.answer)
            for (let i = 0; i < this.class.Id.length; i++) {
              let c = {
                "Id": "",
                "Name": ""
              }
              c.Id = this.class.Id[i]
              c.Name = this.class.Name[i]
              this.classes.push(c)
            }
          }
        }
          , (error) => {
            console.log(error);
          });
    }
  }

  /* ========================================================================== */

  quiz = {
    "QId": [],
    "Name": [],
    "endDate": []
  }
  Quizzes = []

  chooseClass(){
    let str = {
      "teacher_Id"  : this.teacherId.replace("\"","").replace("\"",""),
      "classId" : this.chosenClass
    }
    let temp = JSON.stringify(str);
    this.sendRequestQuiz(temp)
  }
  sendRequestQuiz(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/GetClassQuizzes", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
          if(this.answer == "Error"){
            alert(this.answer);
          }else{
            console.log(this.answer)
            this.quiz = JSON.parse(this.answer)
            console.log(this.quiz)
            for (let i = 0; i < this.quiz.QId.length; i++) {
              let c = {
                "QId": "",
                "QName": "",
                "EndDate": ""
              }
              c.QId = this.quiz.QId[i]
              c.QName = this.quiz.Name[i]
              c.EndDate = this.quiz.endDate[i]
              this.Quizzes.push(c)
            }
            this.getquiz = true
          }
        }
          , (error) => {
            console.log(error);
          });
    }
  }

  /* ============================================================================== */
  getgrade = false
  student = {
    "DoneId" : [],
    "DoneName" : [],
    "Grade" : [],
    "SubmissionTime" : [],
    "LateId" : [],
    "LateName" : []
  }
  StudentsNotDone = []
  StudentsDone = []

  chooseQuiz(){
    this.sendRequestGetGrade(this.chosenQuiz)
  }

  sendRequestGetGrade(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/ShowStudentsGrades", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
          if(this.answer == "Error"){
            alert(this.answer);
          }else{
            this.StudentsDone = [];
            this.StudentsNotDone = []
          this.student = JSON.parse(this.answer)
            for (let i = 0; i < this.student.DoneId.length; i++) {
              let c = {
                "DoneId" : "",
                "DoneName" : "",
                "Grade" : "",
                "SubmissionTime" : ""
              }
              c.DoneId = this.student.DoneId[i]
              c.DoneName = this.student.DoneName[i]
              c.Grade = this.student.Grade[i]
              c.SubmissionTime = this.student.SubmissionTime[i]
              this.StudentsDone.push(c);
            }
            for (let i = 0; i < this.student.LateId.length; i++) {
              let lates = {
                "LateId" : "",
                "LateName" : ""
              }
              lates.LateId = this.student.LateId[i]
              lates.LateName = this.student.LateName[i]
              this.StudentsNotDone.push(lates);
            }
            this.getgrade = true
          }
        }
          , (error) => {
            console.log(error);
          });
    }
  }
}
