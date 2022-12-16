import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  value =[]
    constructor(private _router: Router, private http: HttpClient) { 
    let id = localStorage.getItem("Id")
    // this.sendRequestGetQestions(id)
    let q ={
      "question": "",
      "choice_1": "",
      "choice_2": "",
      "choice_3": "",
      "choice_4": ""
    }
    for(let i=0;i<this.quiz.quetions.length;i++){
      q.question = this.quiz.quetions[i]
      q.choice_1 = this.quiz.choice1[i]
      q.choice_2 = this.quiz.choice2[i]
      q.choice_3 = this.quiz.choice3[i]
      q.choice_4 = this.quiz.choice4[i]
    }

  }

  ngOnInit(): void {
  }
  answer = ""
  sendRequestGetQestions(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response
          this.quiz = JSON.parse(this.answer);
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  quiz=  {
    "quetions": [],
    "choice1": [],
    "choice2": [],
    "choice3": [],
    "choice4": [],
    "solution": []
  }

  questions = [
    {
      "question": "What do you do ?",
      "choice_1": "d7k",
      "choice_2": "akl",
      "choice_3": "shorb",
      "choice_4": "l3b",
      "solution": "4",
    },
    {
      "question": "What do you do ?",
      "choice_1": "d7k",
      "choice_2": "akl",
      "choice_3": "shorb",
      "choice_4": "l3b",
      "solution": "4",
    }
  ]
  displayAlert ="None"
  print(){
    
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
    //sendRequest
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
