import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quiz-done',
  templateUrl: './quiz-done.component.html',
  styleUrls: ['./quiz-done.component.css']
})
export class QuizDoneComponent implements OnInit {

  constructor(private _router: Router, private http: HttpClient) {
    let id = localStorage.getItem("Id")
    this.sendRequestGetQuizes(id)
  }

  ngOnInit(): void {
  }

  Quizzes = []
  possibleQuizes = {
    "Name" :[],
    "Grade" :[],
    "MaxGrade" :[],
    "Date" :[]
  }
  answer = ""

  sendRequestGetQuizes(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/getMyGrades", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response
          if(this.answer == "Error")
          {
            alert(this.answer);
          }else{
            this.possibleQuizes = JSON.parse(this.answer);
            for(let i=0;i<this.possibleQuizes.Name.length;i++){
              let c = {
                "QName" :"",
                "Grade" :"",
                "MaxGrade" :"",
                "submissionTime" :""
                }
              c.QName = this.possibleQuizes.Name[i]
              c.Grade = this.possibleQuizes.Grade[i]
              c.MaxGrade = this.possibleQuizes.MaxGrade[i]
              c.submissionTime = this.possibleQuizes.Date[i]
              this.Quizzes.push(c)
            }
          }
        }
          , (error) => {
            console.log(error);
          });
    }
  }

}
