import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-grades',
  templateUrl: './show-grades.component.html',
  styleUrls: ['./show-grades.component.css']
})
export class ShowGradesComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) {
    this.type = localStorage.getItem("type");
    this.Person_id.id = JSON.parse(localStorage.getItem("Id"))
    //
    // this.URL = "http://localhost:8070/School/create/student";
    // this.sendRequestShowGrades(id,1)
  }
  ngOnInit(): void {
  }

  type = ""
  Person_id = {
    "id" : ""
  }

  grades = [
    {
      "subject": "Arabic",
      "grade": 0,
    }
  ]
  
  term ={
      "startyear" : "",
      "currentyear" : ""
  }

  selectyear ={
    "id" : "",
    "term" : ""
  }


  class_selection = "1";
  displayTable = "none";


  URL : string;
  answer=""
  sendRequestShowGrades(x: string,y: number) {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(this.URL, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            console.log(this.answer);
            if(this.answer == "ERROR"||this.answer =="false"){
              alert(this.answer)
            }
            else{
              if(y==1){
                this.term = JSON.parse(this.answer);
              }else{
                this.grades = JSON.parse(this.answer);
                this.displayTable = "block";
              }
            }
        }
          , (error) => {
            console.log(error);
          });
  }

  change_selection(){
    this.displayTable = "block";
    this.URL  = "http://localhost:8070/School/create/student";
    // this.sendRequestSetGrades(this.class_selection,2);
  }
}
