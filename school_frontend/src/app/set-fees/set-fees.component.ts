import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-set-fees',
  templateUrl: './set-fees.component.html',
  styleUrls: ['./set-fees.component.css']
})
export class SetFeesComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) { }

  ngOnInit(): void {
  }

  year = [
    {
      "name": "KG 1",
      "code": "01",
    },
    {
      "name": "KG 2",
      "code": "02",
    },
    {
      "name": "First primary",
      "code": "03",
    },
    {
      "name": "Second primary",
      "code": "04",
    },
    {
      "name": "Third primary",
      "code": "05",
    },
    {
      "name": "Fourth primary",
      "code": "06",
    },{
      "name": "Fifth primary",
      "code": "07",
    },{
      "name": "Sixth primary",
      "code": "08",
    },{
      "name": "First preparatory",
      "code": "09",
    },
    {
      "name": "Second preparatory",
      "code": "10",
    },{
      "name": "Third preparatory",
      "code": "11",
    }
  ]

  feesSetted = {
    "year":"",
    "fees":""
  }

  setFees(){
    if((this.feesSetted.year != "") && (this.feesSetted.fees != "")){
      this.URL = "http://localhost:8070/School/staff/set/fees";
      var temp = JSON.stringify(this.feesSetted);
      console.log(temp)
      this.sendRequestSetFees(temp);
      this.feesSetted = {
        "year":"",
        "fees":""
      }
    }
  }

  URL : string;
  answer=""
  sendRequestSetFees(x: string) {
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

}
