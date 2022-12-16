import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-exam-table',
  templateUrl: './show-exam-table.component.html',
  styleUrls: ['./show-exam-table.component.css']
})
export class ShowExamTableComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) { }

  ngOnInit(): void {
  }


  URL : string;
  answer=""
  sendRequestCreateAccount(x: string) {
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
