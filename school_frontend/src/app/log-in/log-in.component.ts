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
      this.http.post("http://localhost:8070/School/expression", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            if(this.answer == "ERROR"){
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
  signIn() {
    console.log(this.password);
    
    // let signin = {
    //   "email": this.emailSignIn,
    //   "password": this.passwordSignIn
    // }
    // let sign = "signin&"+JSON.stringify(signin)
    
    let msg = this.id + "," + this.password
    console.log(msg)
    this.sendRequestSignIn(msg)
    // if(this.id=="michael" && this.password=="123"){
    //     this.displayDone = "block"
    //     this.displayAlert = "None"
    // }
    // else{
    //     this.displayDone = "None"
    //     this.displayAlert = "block"
    // }
    console.log(this.answer)
  }
  enter(){
    if(this.displayDone == "block")
      this._router.navigate(['profile'])
    else
      this.displayAlert = "block"
  }
}
