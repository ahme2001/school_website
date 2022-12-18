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
    this.Person_id = JSON.parse(localStorage.getItem("Id"));
    this.selectyear.id = JSON.parse(localStorage.getItem("Id"));
    this.URL = "http://localhost:8070/School/grades/range";
    this.sendRequestShowGrades(JSON.stringify(this.Person_id),1)
  }
  ngOnInit(): void {
  }

  Person_id : string;
  year = [
    {
      "name": "First primary",
      "code": "03",
    },
    {
      "name": "Second primary",
      "code": "04",
    },
    {
      "name": "Third primary",
      "code": "05",
    },
    {
      "name": "Fourth primary",
      "code": "06",
    },{
      "name": "Fifth primary",
      "code": "07",
    },{
      "name": "Sixth primary",
      "code": "08",
    },{
      "name": "First preparatory",
      "code": "09",
    },
    {
      "name": "Second preparatory",
      "code": "10",
    },{
      "name": "Third preparatory",
      "code": "11",
    }
  ]
  terms = [
    {
      "name":"1"
    },{
      "name":"2"
    },{
      "name":"3"
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
  termselect = "";
  yearselect = "";
  displayTable = "none";
  selectedYear = []
  grades = []


  URL : string;
  answer=""
  sendRequestShowGrades(x: string,y: number) {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(this.URL, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            if(this.answer == "ERROR"||this.answer =="false"){
              alert(this.answer)
            }
            else{
              if(y==1){
                this.term = JSON.parse(this.answer);
                var begin = Number(this.term.startyear);
                var end = Number(this.term.currentyear);
                for(let i = begin-3; i <= end-3; i++){
                  this.selectedYear.push(this.year[i])
                }
              }else{
                this.grades = []
                var temp = JSON.parse(this.answer);
                for(let i = 0; i < temp.length; i++){
                  this.grades.push(JSON.parse(temp[i]))
                }
                this.displayTable = "block";
              }
            }
        }
          , (error) => {
            console.log(error);
          });
  }

  getgrade(){
    this.selectyear.term = this.yearselect + this.termselect;
    var temp = JSON.stringify(this.selectyear)
    this.URL  = "http://localhost:8070/School/grades/show";
    this.sendRequestShowGrades(temp,2);
  }
}
