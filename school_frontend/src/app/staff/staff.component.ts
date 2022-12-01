import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private _router:Router,private http :HttpClient) { }
  selection = "student";
  displayStudDone = "block"
  displayTeacDone = "none"
  displayStafDone = "none"
  submitted = false;
  person = [{"name":'Student'},{"name":'Teacher'},{"name":'Staff'}];
  StudentForm:FormGroup;
  TeacherForm:FormGroup;
  StaffForm:FormGroup;

  ngOnInit(): void {
    this.StudentForm = new FormGroup({
      select: new FormControl(null),
      name: new FormControl(null,Validators.required),
      phone: new FormControl(null,[Validators.minLength(11),Validators.maxLength(11)]),
      national_id: new FormControl(null,[Validators.required,Validators.minLength(14),Validators.maxLength(14)]),
      address: new FormControl(null),
      birth_date: new FormControl(null,Validators.required),
      gender: new FormControl(null,Validators.required),
      class: new FormControl(null,Validators.required),
      parent_name: new FormControl(null,Validators.required),
      parent_national_id: new FormControl(null,[Validators.required,Validators.minLength(14),Validators.maxLength(14)]),
      parent_job: new FormControl(null,Validators.required)
    });
    this.TeacherForm = new FormGroup({
      select: new FormControl(null),
      name: new FormControl(null,Validators.required),
      phone: new FormControl(null),
      national_id: new FormControl(null,Validators.required),
      address: new FormControl(null),
      birth_date: new FormControl(null,Validators.required),
      gender: new FormControl(null,Validators.required),
      subject: new FormControl(null,Validators.required)
    });
    this.StaffForm = new FormGroup({
      select: new FormControl(null),
      name: new FormControl(null,Validators.required),
      phone: new FormControl(null),
      national_id: new FormControl(null,Validators.required),
      address: new FormControl(null),
      birth_date: new FormControl(null,Validators.required),
      gender: new FormControl(null,Validators.required),
      job: new FormControl(null,Validators.required)
    });
  }

  answer=""
  sendRequestCreateAccount(x: object) {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            console.log(this.answer);
            if(this.answer == "ERROR"||this.answer =="false"){
              alert(this.answer)
            }
            else{
              alert("Succes")
            }
        }
          , (error) => {
            console.log(error);
          });
  }

  get std(){
    return this.StudentForm.controls;
  }

  get tea(){
    return this.TeacherForm.controls;
  }

  get staf(){
    return this.StaffForm.controls;
  }

  change_selection(){
    if(this.selection == "Student"){
      this.displayStudDone = "block"
      this.displayTeacDone = "None"
      this.displayStafDone = "None"
    }else if (this.selection == "Teacher"){
      this.displayStudDone = "None"
      this.displayTeacDone = "block"
      this.displayStafDone = "None"
    }else{
      this.displayStudDone = "None"
      this.displayTeacDone = "None"
      this.displayStafDone = "block"
    }
  }
  onSubmit(){
    this.submitted = true;
    if(this.selection == "Student"){
      console.log(typeof(this.StudentForm.value))
      console.log(this.StudentForm.value)
      this.sendRequestCreateAccount(this.StudentForm.value);
      this.StudentForm.reset();
    }else if (this.selection == "Teacher"){
      console.log(typeof(this.TeacherForm.value))
      console.log(this.TeacherForm.value)
      this.sendRequestCreateAccount(this.TeacherForm.value);
      this.TeacherForm.reset();
    }else{
      console.log(typeof(this.StaffForm.value))
      console.log(this.StaffForm.value)
      this.sendRequestCreateAccount(this.StaffForm.value);
      this.StaffForm.reset();
    }
  }
}
