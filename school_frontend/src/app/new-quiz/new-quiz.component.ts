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
    ]
  temp = {
    "question": "",
    "choice_1": "",
    "choice_2": "",
    "choice_3": "",
    "choice_4": "",
    "solution": "",
  }
  classes = [
  ]
  class = {
    "Id": [],
    "Name": []
  }
  constructor(private _router: Router, private http: HttpClient) {
    let id = localStorage.getItem("Id").replace("\"","").replace("\"","")
    console.log(id)
    this.teacherid = id;
    this.sendRequestClass(id)
  }
  teacherid = ""
  answer = ""
  sendRequestClass(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/getInfo", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
          this.class = JSON.parse(this.answer)
          console.log(this.answer);
          for (let i = 0; i < this.class.Id.length; i++) {
            let c = {
              "Id": "",
              "Name": ""
            }
            c.Id = this.class.Id[i]
            c.Name = this.class.Name[i]
            this.classes.push(c)
          }
          console.log(this.classes);

        }
          , (error) => {
            console.log(error);
          });
    }
  }
  sendRequestSubmit(x: string) {
    console.log(x);

    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/setQuiz", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
          console.log(this.answer);

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
    "classId": "",
    "teacher_Id" : "",
    "endDate": "",
    "Qname":"",
    "questions": [],
    "choice1": [],
    "choice2": [],
    "choice3": [],
    "choice4": [],
    "answers": []
  }

  submit() {
    if (this.questions.length != 0) {
      let counter = 0;
      if (this.endDate == "") {
        this.displayAlert = "block"
        this.alert = "Write the end date"
      }
      else {
        // console.log(this.chosenClass);

        this.t.classId = this.chosenClass
        this.t.endDate = this.endDate
        this.t.Qname = this.QuizTitle
        for (let i = 0; i < this.questions.length; i++) {
          this.t.questions[i] = this.questions[i].question
          this.t.choice1[i] = this.questions[i].choice_1
          this.t.choice2[i] = this.questions[i].choice_2
          this.t.choice3[i] = this.questions[i].choice_3
          this.t.choice4[i] = this.questions[i].choice_4
          this.t.answers[i] = this.questions[i].solution
          counter++;
        }
        this.t.teacher_Id = this.teacherid.replace("\"","").replace("\"","");
        this.sendRequestSubmit(JSON.stringify(this.t))
        this.questions = [
        ]
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
      "Id": [],
      "Name": []
    }

  }
  ngOnInit(): void {
  }


}
