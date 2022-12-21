import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-set-schedule',
  templateUrl: './set-schedule.component.html',
  styleUrls: ['./set-schedule.component.css']
})
export class SetScheduleComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient)  { }

  ngOnInit(): void {
  }

  term = [
    {"name":"1"},
    {"name":"2"},
    {"name":"3"},
    {"name":"4"}
  ]

  StudentTable = {
    "classId": "",
    "termId": "",
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


  TeacherTable = {
    "Teacher_Id": "",
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
  sendRequestSetTable(x: string) {
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
              alert(this.answer)
            }
        }
          , (error) => {
            console.log(error);
          });
  }

  submitStudent(){
    if((this.StudentTable.classId != "") && (this.StudentTable.termId != "")){
      var temp = JSON.stringify(this.StudentTable);
      this.URL = "http://localhost:8070/School/staff/set/student-table";
      this.sendRequestSetTable(temp);
    }
  }



  submitTeacher(){
    if(this.TeacherTable.Teacher_Id != ""){
      var temp = JSON.stringify(this.TeacherTable);
      this.URL = "http://localhost:8070/School/staff/set/teacher-table";
      this.sendRequestSetTable(temp);
    }
  }
}
