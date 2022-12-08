import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  isStudent = false
  isTeacher = false
  isStaff = false
  profile = {
    "Name":"Michael Samir Azmy",
    "National_Id":"3010101012",
    "Phone":"01286265623",
    "Job":"",
    "Address":"",
    "Sex":""
  };
  constructor(private _router:Router,private http :HttpClient) { 
    
    let x = JSON.parse(localStorage.getItem("type"))
    // this.sendRequestSignIn(x)
    // this.profile = JSON.parse(this.answer)
    if(x =="student") this.isStudent = true
    else if(x=="teacher") this.isTeacher = true
    else this.isStaff = true
    console.log(x);
  }
  answer =""
  sendRequestSignIn(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
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
  
  findingType(){
    
  }
  ngOnInit(): void {
  }

}
