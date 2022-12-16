import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { ProfileComponent } from './profile/profile.component';
import { StaffComponent } from './staff/staff.component';
import { ShowExamTableComponent } from './show-exam-table/show-exam-table.component';
import { SetExamTableComponent } from './set-exam-table/set-exam-table.component';

const routes: Routes = [
  {path:'set_examsTable',component:SetExamTableComponent},
  {path:'show_examTable',component:ShowExamTableComponent},
  {path:'create_account',component:StaffComponent},
  {path:'profile',component:ProfileComponent},
  {path:'log',component:LogInComponent},
  {path:'',component:LogInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
