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

  Student = [
    {
      "id":0,
      "name": "",
      "grade": 0,
    }
  ]
  selection = "1"
  class = [
    {
      "name":"3"
    }
  ]
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
              alert("Wrong class")
            }
            else{
              if(y==1){
                this.Student = JSON.parse(this.answer);
                this.displaytable = "block";
              }else{
                alert(this.answer);
                this.displaytable = "none";
                this.Student = [
                  {
                    "id":0,
                    "name": "",
                    "grade": 0,
                  }
                ]
              }
            }
        }
          , (error) => {
            console.log(error);
          });
  }

  change_selection(){
    this.displaytable = "block";
    this.URL  = "http://localhost:8070/School/create/student";
    // this.sendRequestSetGrades(this.selection,1);
  }

  submit(){
    this.URL  = "http://localhost:8070/School/create/student";
    var temp = JSON.stringify(this.Student);
    // this.sendRequestSetGrades(temp,2);
  }

}
