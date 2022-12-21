import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { NewQuizComponent } from './new-quiz/new-quiz.component';
import { ProfileComponent } from './profile/profile.component';
import { QuizComponent } from './quiz/quiz.component';
import { StaffComponent } from './staff/staff.component';
import { ShowExamTableComponent } from './show-exam-table/show-exam-table.component';
import { SetExamTableComponent } from './set-exam-table/set-exam-table.component';
import { SetScheduleComponent } from './set-schedule/set-schedule.component';
import { ShowScheduleComponent } from './show-schedule/show-schedule.component';

const routes: Routes = [
  {path:'set_examsTable',component:SetExamTableComponent},
  {path:'show_examTable',component:ShowExamTableComponent},
  {path:'show_schedule',component:ShowScheduleComponent},
  {path:'set_schedule',component:SetScheduleComponent},
  {path:'create_account',component:StaffComponent},
  {path:'profile',component:ProfileComponent},
  {path:'log',component:LogInComponent},
  {path:'new_Quiz',component:NewQuizComponent},
  {path:'Quiz',component:QuizComponent},
  {path:'',component:LogInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
