import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-schedule',
  templateUrl: './show-schedule.component.html',
  styleUrls: ['./show-schedule.component.css']
})
export class ShowScheduleComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) {
    this.type = localStorage.getItem("type");
    this.Person_id.ID = JSON.parse(localStorage.getItem("Id"))
    if(this.type == "student")
    {
      this.URL = "http://localhost:8070/School/student/get/table";
    }else if (this.type == "teacher"){
      this.URL = "http://localhost:8070/School/teacher/get/table";
    }
    var temp = JSON.stringify(this.Person_id);
    this.sendRequestGetTable(temp);
  }

  ngOnInit(): void {
  }

  type = ""
  Person_id = {
    "ID" : ""
  }

  Table = {
    "days":[{
      "Day" : "Saturday",
      "lectures": {
        "lec1" : "",
        "lec2" : "",
        "lec3" : "",
        "lec4" : "",
        "lec5" : "",
        "lec6" : ""
    }
  },
  {
    "Day" : "Sunday",
    "lectures": {
      "lec1" : "",
      "lec2" : "",
      "lec3" : "",
      "lec4" : "",
      "lec5" : "",
      "lec6" : ""
    }
  },
    {
      "Day" : "Monday",
      "lectures": {
        "lec1" : "",
        "lec2" : "",
        "lec3" : "",
        "lec4" : "",
        "lec5" : "",
        "lec6" : ""
    }
  },
  {
    "Day" : "Tuesday",
    "lectures": {
      "lec1" : "",
      "lec2" : "",
      "lec3" : "",
      "lec4" : "",
      "lec5" : "",
      "lec6" : ""
    }
  },
  {
    "Day" : "Wednesday",
    "lectures": {
      "lec1" : "",
      "lec2" : "",
      "lec3" : "",
      "lec4" : "",
      "lec5" : "",
      "lec6" : ""
  }
  },
  {
    "Day" : "Thursday",
    "lectures": {
      "lec1" : "",
      "lec2" : "",
      "lec3" : "",
      "lec4" : "",
      "lec5" : "",
      "lec6" : ""
    }
  }]
  }

  URL : string;
  answer=""
  sendRequestGetTable(x: string) {
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
              this.Table = JSON.parse(this.answer);
            }
        }
          , (error) => {
            console.log(error);
          });
  }
}
