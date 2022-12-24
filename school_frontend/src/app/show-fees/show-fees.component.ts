import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-fees',
  templateUrl: './show-fees.component.html',
  styleUrls: ['./show-fees.component.css']
})
export class ShowFeesComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) {
    this.Person_id.id = JSON.parse(localStorage.getItem("Id"));
    this.URL = "http://localhost:8070/School/parent/get/children/fees";
    this.sendRequestShowFees(JSON.stringify(this.Person_id))
  }
  ngOnInit(): void {
  }

  Person_id = {
    "id":""
  }

  children = [
    {
      "name": "",
      "fees": ""
    }
  ]

  URL : string;
  answer=""
  sendRequestShowFees(x: string) {
    const headers = new HttpHeaders({ 'Content-Type': "application/text" })
    this.http.post(this.URL, x,
      { headers: headers, responseType: 'text' })
      .subscribe(response => {
          this.answer = response;
          if(this.answer == "ERROR"||this.answer =="false"){
            alert(this.answer)
          }
          else{
              this.children = JSON.parse(this.answer)
          }
      }
        , (error) => {
          console.log(error);
        });
  }

}
