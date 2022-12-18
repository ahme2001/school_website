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
  isParent = false
  student = {
    "Class_Id":"",
    "ST_Term_Id":"",
    "Curr_term_id":"",
    "P_id":"",
    "Address":"",
    "Phone":"",
    "Sex":"",
    "National_Id":"",
    "Name":"",
    "password":""
  };

  parent = {
    "Job":"",
    "Address":"",
    "Phone":"",
    "Sex":"",
    "National_Id":"",    
    "Name":"",
    "password":""
  };

  teacher = {
    "Experience":"",
    "Sub":"",
    "Address":"",
    "Phone":"",
    "Sex":"",
    "National_Id":"",    
    "Name":"",
    "password":""
  };

  staff = {
    "Job":"",
    "Address":"",
    "Phone":"",
    "Sex":"",
    "National_Id":"",
    "Name":"",
    "Password":"",
  }


  constructor(private _router:Router,private http :HttpClient) { 
    let x = JSON.parse(localStorage.getItem("type"))
    if(x =="student") this.isStudent = true
    else if(x=="teacher") this.isTeacher = true
    else if(x=="staff") this.isStaff = true
    else this.isParent = true;
    let id = JSON.parse(localStorage.getItem("Id"))
    // console.log(id)
    this.sendRequestSignIn(id)
  }
  answer =""
  sendRequestSignIn(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/profile", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            if(this.isStudent){
              this.student= JSON.parse(this.answer)
            }
            else if(this.isParent){
              this.parent= JSON.parse(this.answer)
            }
            else if(this.isTeacher){
              this.teacher= JSON.parse(this.answer)
            }
            else{
                this.staff= JSON.parse(this.answer)
            }
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  ngOnInit(): void {
  }

}
