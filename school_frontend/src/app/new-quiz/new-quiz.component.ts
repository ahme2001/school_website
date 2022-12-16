import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-new-quiz',
  templateUrl: './new-quiz.component.html',
  styleUrls: ['./new-quiz.component.css']
})
export class NewQuizComponent implements OnInit {
  isQuestion = false
  endDate = ""
  QuizTitle =""
  question = ""
  ch1 = ""
  ch2 = ""
  ch3 = ""
  ch4 = ""
  sol = ""
  displayAlert = "None"
  chosenClass = ""
  alert = ""
  questions = [
    {
      "question": "",
      "choice_1": "",
      "choice_2": "",
      "choice_3": "",
      "choice_4": "",
      "solution": "",
    }]
  temp = {
    "question": "",
    "choice_1": "",
    "choice_2": "",
    "choice_3": "",
    "choice_4": "",
    "solution": "",
  }
  classes = [
    {
      "Id": "",
      "Name": ""
    }
  ]
  constructor(private _router: Router, private http: HttpClient) {
    let id = localStorage.getItem("Id")
    //  this.sendRequestClass(id)
    let counter = 0
    for (let i = 0; i < this.class.Class_Id.length; i++) {
      let c = {
        "Id": "",
        "Name": ""
      }
      c.Id = this.class.Class_Id[counter]
      c.Name = this.class.Name[counter]
      this.classes.push(c)
      counter++
    }
  }
  answer = ""
  sendRequestClass(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
          this.class = JSON.parse(this.answer)
          console.log(this.class);
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  sendRequestSubmit(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  addQuestion() {
    if (this.question == "" || this.ch1 == "" || this.ch2 == "" || this.ch3 == "" || this.ch4 == "" || this.sol == "") {
      this.displayAlert = "block"
      this.alert = "All field are required"
    }
    else if (this.sol != "1" && this.sol != "2" && this.sol != "3" && this.sol != "4") {
      this.displayAlert = "block"
      this.alert = "In solution field you should write 1 or 2 or 3 or 4"
    }
    else {
      this.temp.question = this.question;
      this.temp.choice_1 = this.ch1;
      this.temp.choice_2 = this.ch2;
      this.temp.choice_3 = this.ch3;
      this.temp.choice_4 = this.ch4;
      this.temp.solution = this.sol;
      this.questions.push(this.temp)
      this.isQuestion = true
      this.clear()
      this.displayAlert = "None"
    }
  }

  t = {
    "class": "",
    "endDate": "",
    "Qname":"",
    "quetions": [],
    "choice1": [],
    "choice2": [],
    "choice3": [],
    "choice4": [],
    "solution": []
  }
  class = {
    "Class_Id": [],
    "Name": []
  }
  submit() {
    if (this.questions.length != 0) {
      let counter = 0;
      if (this.endDate == "") {
        this.displayAlert = "block"
        this.alert = "Write the end date"
      }
      else {
        this.t.class = this.chosenClass
        this.t.endDate = this.endDate
        for (let i = 0; i < this.questions.length; i++) {
          this.t.quetions[counter] = this.questions[counter].question
          this.t.choice1[counter] = this.questions[counter].choice_1
          this.t.choice2[counter] = this.questions[counter].choice_2
          this.t.choice3[counter] = this.questions[counter].choice_3
          this.t.choice4[counter] = this.questions[counter].choice_4
          this.t.solution[counter] = this.questions[counter].solution
          counter++;
        }
        console.log(JSON.stringify(this.t));
        // this.sendRequestSubmit(JSON.stringify(this.t))
        this.questions = [
          {
            "question": "",
            "choice_1": "",
            "choice_2": "",
            "choice_3": "",
            "choice_4": "",
            "solution": "",
          }]
        this.displayAlert = "None"
      }
    }
    else {
      this.displayAlert = "block"
      this.alert = "You can't submit empty quiz"
    }

  }
  clear() {
    this.temp = {
      "question": "",
      "choice_1": "",
      "choice_2": "",
      "choice_3": "",
      "choice_4": "",
      "solution": "",
    }
    this.question = ""
    this.ch1 = ""
    this.ch2 = ""
    this.ch3 = ""
    this.ch4 = ""
    this.sol = ""
    console.log(JSON.stringify(this.questions));
    this.class = {
      "Class_Id": [],
      "Name": []
    }
  }
  ngOnInit(): void {
  }


}
