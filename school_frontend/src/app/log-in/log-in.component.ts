import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) {}

  ngOnInit(): void {
  }

  answer=""
  sendRequestSignIn(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            console.log(this.answer);
            
            if(this.answer == "ERROR"||this.answer =="false"){
              this.displayAlert = "block"
              this.displayDone = "None"
            }
            else{
              this.displayDone = "block"
              this.displayAlert = "None"
            }
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  res:string=""
  id:string=""
  password:string = ""
  displayAlert: string = "None"
  displayDone: string = "None"
  account = {
    "id":"",
    "pass":"",
  };
  signIn() {

    this.account.id = this.id
    this.account.pass = this.password;
    // let msg = this.id + "," + this.password
    let msg = JSON.stringify(this.account)
    console.log(msg)
    this.sendRequestSignIn(msg)
  }
  enter(){
    localStorage.setItem("Id", JSON.stringify(this.id))
    if(this.id[2]=='0')
      localStorage.setItem("type", JSON.stringify("student"))
    else if(this.id[2]=='1')
      localStorage.setItem("type", JSON.stringify("parent"))
    else if(this.id[2]=='2')
      localStorage.setItem("type", JSON.stringify("teacher"))
    else if(this.id[2]=='3')
      localStorage.setItem("type", JSON.stringify("staff"))
    if(this.displayDone == "block")
      this._router.navigate(['profile'])
    else
      this.displayAlert = "block"

    console.log(JSON.parse(localStorage.getItem("Id")))
  }
}
