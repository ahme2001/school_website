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
  parent = {
    "Name":"",
    "National_Id":"",
    "Phone":"",
    "Job":"",
    "Address":"",
    "Sex":""
  };

  ngOnInit(): void {
    this.StudentForm = new FormGroup({
      Name: new FormControl(null,Validators.required),
      Phone: new FormControl(null,[Validators.minLength(11),Validators.maxLength(11)]),
      National_Id: new FormControl(null,[Validators.required,Validators.minLength(14),Validators.maxLength(14)]),
      Address: new FormControl(null),
      birth_date: new FormControl(null,Validators.required),
      Sex: new FormControl(null,Validators.required),
      Class_Id: new FormControl(null,Validators.required),
      St_Term_Id: new FormControl(null,Validators.required),
      Curr_Term_Id: new FormControl(null,Validators.required),
      parent_name: new FormControl(null,Validators.required),
      parent_national_id: new FormControl(null,[Validators.required,Validators.minLength(14),Validators.maxLength(14)]),
      parent_phone: new FormControl(null,[Validators.required,Validators.minLength(11),Validators.maxLength(11)]),
      parent_job: new FormControl(null,Validators.required),
      parent_sex: new FormControl(null,Validators.required),
    });
    this.TeacherForm = new FormGroup({
      Name: new FormControl(null,Validators.required),
      Phone: new FormControl(null,[Validators.minLength(11),Validators.maxLength(11)]),
      National_Id: new FormControl(null,[Validators.required,Validators.minLength(14),Validators.maxLength(14)]),
      Address: new FormControl(null),
      birth_date: new FormControl(null,Validators.required),
      Sex: new FormControl(null,Validators.required),
      Sub: new FormControl(null,Validators.required),
      Experience: new FormControl(null,Validators.required)
    });
    this.StaffForm = new FormGroup({
      Name: new FormControl(null,Validators.required),
      Phone: new FormControl(null,[Validators.minLength(11),Validators.maxLength(11)]),
      National_Id: new FormControl(null,[Validators.required,Validators.minLength(14),Validators.maxLength(14)]),
      Address: new FormControl(null),
      birth_date: new FormControl(null,Validators.required),
      Sex: new FormControl(null,Validators.required),
      Job: new FormControl(null,Validators.required)
    });
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
  sleep = (ms) => new Promise(r => setTimeout(r, ms));

  async onSubmit(){
    this.submitted = true;
    if(this.selection == "Student"){
      this.parent.Name = this.StudentForm.value.parent_name
      this.parent.National_Id = this.StudentForm.value.parent_national_id
      this.parent.Job = this.StudentForm.value.parent_job
      this.parent.Phone = this.StudentForm.value.parent_phone
      this.parent.Sex = this.StudentForm.value.parent_sex
      this.parent.Address = this.StudentForm.value.Address
      this.URL = "http://localhost:8070/School/create/parent";
      var temp = JSON.stringify(this.parent);
      this.sendRequestCreateAccount(temp);
      var temp = JSON.stringify(this.StudentForm.value);
      await this.sleep(1000);
      this.URL = "http://localhost:8070/School/create/student";
      this.sendRequestCreateAccount(temp);
      this.StudentForm.reset();
    }else if (this.selection == "Teacher"){
      var temp = JSON.stringify(this.TeacherForm.value);
      this.URL = "http://localhost:8070/School/create/teacher";
      this.sendRequestCreateAccount(temp);
      this.TeacherForm.reset();
    }else{
      var temp = JSON.stringify(this.StaffForm.value);
      this.URL = "http://localhost:8070/School/create/staff";
      this.sendRequestCreateAccount(temp);
      this.StaffForm.reset();
    }
    this.submitted = false
  }

  /* ===================================================================================== */
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
      this.sendRequestCreateAccount(temp);
      this.feesSetted = {
        "year":"",
        "fees":""
      }
    }
  }
}
