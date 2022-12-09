import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { ProfileComponent } from './profile/profile.component';
import { StaffComponent } from './staff/staff.component';
import { SetGradesComponent } from './set-grades/set-grades.component';

const routes: Routes = [
  {path:'set_grades',component:SetGradesComponent},
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
