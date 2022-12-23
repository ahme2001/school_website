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
    this.URL = "http://localhost:8070/School/parent/get/all/children";
    this.sendRequestShowFees(JSON.stringify(this.Person_id),1)
  }

  Person_id = {
    "id":""
  }

  ngOnInit(): void {
  }

  children = [
    {
      "Id":"",
      "name": ""
    }
  ]
  selected={
    "id":""
  }

  fees = "1500";
  displayfees = "none"

  URL : string;
  answer=""
  sendRequestShowFees(x: string,y: number) {
    const headers = new HttpHeaders({ 'Content-Type': "application/text" })
    this.http.post(this.URL, x,
      { headers: headers, responseType: 'text' })
      .subscribe(response => {
          this.answer = response;
          if(this.answer == "ERROR"||this.answer =="false"){
            alert(this.answer)
          }
          else{
            if(y==1){
              this.children = JSON.parse(this.answer)
            }else{
              this.fees = JSON.parse(this.answer);
              this.displayfees = "block";
            }
          }
      }
        , (error) => {
          console.log(error);
        });
  }

  showFees(){
    if(this.selected.id != ""){
      this.URL = "http://localhost:8070/School/parent/get/child/fees";
      this.sendRequestShowFees(JSON.stringify(this.Person_id),2);
    }
  }
}
