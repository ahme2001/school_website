import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-discussion-teacher',
  templateUrl: './discussion-teacher.component.html',
  styleUrls: ['./discussion-teacher.component.css']
})
export class DiscussionTeacherComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) { }
  chosenClass=""
  chooseClass = true
  answer = ""
  ngOnInit(): void {
  }
  class = {
    "Id": ["100","110"],
    "Name": ["2/3","2/5"]
  }
  classes = [{
    "id":"100",
    "Name":"2/3"
  },
  {
    "id":"110",
    "Name":"2/5"
  }
]
enter(){
  if(this.chosenClass != ""){
         this.sendRequestgetPosts(this.chosenClass)
  }
}


sendRequestClass(x: string) {
  if (x != '') {
    const headers = new HttpHeaders({ 'Content-Type': "application/text" })
    this.http.post("http://localhost:8070/School/getInfo", x,
      { headers: headers, responseType: 'text' })
      .subscribe(response => {
        this.answer = response;
        this.class = JSON.parse(this.answer)
        console.log(this.answer);
        for (let i = 0; i < this.class.Id.length; i++) {
          let c = {
            "Id": "",
            "Name": ""
          }
          c.Id = this.class.Id[i]
          c.Name = this.class.Name[i]
          // this.classes.push(c)
        }
        console.log(this.classes);
      }
        , (error) => {
          console.log(error);
        });
  }
}
sendRequestgetPosts(x: string) {
  if (x != '') {
    const headers = new HttpHeaders({ 'Content-Type': "application/text" })
    this.http.post("http://localhost:8070/School/login", x,
      { headers: headers, responseType: 'text' })
      .subscribe(response => {
          this.answer = response;
         // this.posts = JSON.parse(this.answer)
          console.log(this.answer);
      }
        , (error) => {
          console.log(error);
        });
  }
}
}
