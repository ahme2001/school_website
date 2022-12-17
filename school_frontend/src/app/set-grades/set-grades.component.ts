import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-set-grades',
  templateUrl: './set-grades.component.html',
  styleUrls: ['./set-grades.component.css']
})
export class SetGradesComponent implements OnInit {

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
  SubjectSelection = "";
  Student = {
    "subject":"",
    "students": []
  }

  subject=[{"name":"Arabic","code":"01"},{"name":"English","code":"02"},{"name":"French","code":"03"},{"name":"Science","code":"04"},
  {"name":"Math","code":"05"},{"name":"Social studies","code":"06"},{"name":"Algebra","code":"07"},{"name":"geometry","code":"08"},
  {"name":"activities","code":"09"}]
  displaytable = "none"


  URL : string;
  answer=""
  sendRequestSetGrades(x: string,y: number) {
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
                this.Student.students = [];
                var temp = JSON.parse(this.answer);
                for(let i = 0; i < temp.length; i++){
                  this.Student.students.push(JSON.parse(temp[i]))
                }
                this.displaytable = "block";
              }else{
                alert(this.answer);
                this.displaytable = "none";
                this.Student.students = []
              }
            }
        }
          , (error) => {
            console.log(error);
          });
  }

  submit(){
    if(this.SubjectSelection != ""){
      this.Student.subject = this.YearSelection + this.TermSelection + this.SubjectSelection
      var temp = JSON.stringify(this.Student);
      console.log(temp)
      this.URL  = "http://localhost:8070/School/grades/save";
      this.sendRequestSetGrades(temp,2);
    }
  }

  getStudent(){
    if((this.YearSelection != "")&&(this.TermSelection != "")){
      var yearId = this.YearSelection + this.TermSelection
      this.URL = "http://localhost:8070/School/grades/IDs";
      this.sendRequestSetGrades(yearId,1)
    }
  }

}
