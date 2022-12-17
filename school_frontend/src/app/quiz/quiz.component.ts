import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { first } from 'rxjs';
@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  value =[]
    constructor(private _router: Router, private http: HttpClient) { 
    let id = localStorage.getItem("Id")
    this.sendRequestGetQuizes(id)
  }
  
  Quizes = [
]
  possibleQuizes = {
    "Id" :[],
    "Name" :[]
  }
  ngOnInit(): void {
  }
  answer = ""
  f1 = true
  f2 = false
  f3 = false
  QuizName =""
  EndDate =""
  sendRequestGetQuizes(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/Quizzes", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response
          this.possibleQuizes = JSON.parse(this.answer);
          for(let i=0;i<this.possibleQuizes.Id.length;i++){
            let c = {
              "Id":"",
              "Name":""
            }
            c.Id = this.possibleQuizes.Id[i]
            c.Name = this.possibleQuizes.Name[i]
            this.Quizes.push(c)
          }
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  sendRequestGetQestions(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/DoQuiz", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response
          this.quiz = JSON.parse(this.answer);
          console.log(this.quiz);
          console.log(this.quiz.choice1.length);
          this.QuizName = this.quiz.Qname
          this.EndDate = this.quiz.endDate
          for(let i=0;i<this.quiz.choice1.length;i++){
            console.log("ehhbf");
            
            let q ={
              "question": "",
              "choice_1": "",
              "choice_2": "",
              "choice_3": "",
              "choice_4": ""
            }
            q.question = this.quiz.questions[i]
            q.choice_1 = this.quiz.choice1[i]
            q.choice_2 = this.quiz.choice2[i]
            q.choice_3 = this.quiz.choice3[i]
            q.choice_4 = this.quiz.choice4[i]
            this.questions.push(q)
          }
          console.log(this.questions.length)
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  sendRequestGetgrade(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response
          this.grade = JSON.parse(this.answer);
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  grade = {
    "grade":24,
    "from" :25
  }
  QuizId =""
  findingQuiz(i:string){
      this.QuizId =i;
      console.log(this.QuizId);
      this.sendRequestGetQestions(this.QuizId)
      this.f1 = false
      this.f2 = true
      
  }
  quiz=  {
    "Qname":"",
    "endDate":"",
    "questions": [],
    "choice1": [],
    "choice2": [],
    "choice3": [],
    "choice4": []
  }

  questions = [
   
  ]
  displayAlert ="None"
  submit(){
    let flag = true
    for(let i=0;i<this.questions.length;i++){
      if(this.value[i] != "1" && this.value[i] != "2" && this.value[i] != "3" && this.value[i] != "4") {
        flag = false
        this.value[i] = 0
      }
      if(this.value.length != this.questions.length){
        flag = false
      }
  }
  if(flag){
    for(let i=0;i<this.questions.length;i++){
      this.value[i] = Number(this.value[i])
    }
    let response = {
      "StudentId":"",
      "QuizId":"",
      "solutions":[]
    }
    response.solutions = this.value
    response.StudentId = localStorage.getItem("id")
    response.QuizId = this.QuizId
    console.log(this.value);
    // this.sendRequestGetgrade(JSON.stringify(response))
    this.f2 = false
    this.f3 = true
    this.displayAlert = "block"
    this.clear()
  }
  else{
    this.displayAlert = "block"
    
  }
}
 clear(){
  this.displayAlert ="block"
  this.value =[]
 }
}
