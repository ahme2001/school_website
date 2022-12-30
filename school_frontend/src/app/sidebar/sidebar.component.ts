import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  isStudent = false;
  isParent = false;
  isTeacher = false;
  isStaff = false;
  constructor() {
    let x = localStorage.getItem("type")
    if(x.match("student") !== null){
      this.isStudent = true
    }
    else if(x.match("parent") !== null){
      this.isParent = true
    }
    else if(x.match("staff") !== null){
      this.isStaff = true
    }
    else{
      this.isTeacher=true
    }
   }

  ngOnInit(): void {
  }

}
