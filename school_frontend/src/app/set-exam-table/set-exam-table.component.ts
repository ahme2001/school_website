import { JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-set-exam-table',
  templateUrl: './set-exam-table.component.html',
  styleUrls: ['./set-exam-table.component.css']
})
export class SetExamTableComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) { }
  ngOnInit(): void {
  }

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
  term = [
    {
      "name":"1"
    },{
      "name":"2"
    },{
      "name":"3"
    }
  ]

  YearSelection = "";
  TermSelection = "";

  examTable =  []

  URL : string;
  answer=""
  sendRequestSetExamTable(x: string,y: number) {
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
              if(y == 1){
                this.examTable = JSON.parse(this.answer)
                this.divdisplay = "block"
              }else{
                alert(this.answer)
                this.examTable = []
                this.divdisplay = "none"
                this.YearSelection = "";
                this.TermSelection = "";
              }
            }
        }
          , (error) => {
            console.log(error);
          });
  }

  divdisplay = "none"

  getSubject(){
    var yearId = {
      "code" : this.YearSelection + this.TermSelection
    }
    console.log(JSON.stringify(yearId));
    this.URL = "http://localhost:8070/School/get/all-subjects";
    // this.sendRequestSetExamTable(JSON.stringify(yearId),1)
  }

  submitTable(){
    console.log(JSON.stringify(this.examTable))
    this.URL = "http://localhost:8070/School/set/exam-table";
    // this.sendRequestSetExamTable(JSON.stringify(this.examTable),2)
  }

}
