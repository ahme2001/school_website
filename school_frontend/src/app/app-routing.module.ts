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
import { SetGradesComponent } from './set-grades/set-grades.component';
import { ShowGradesComponent } from './show-grades/show-grades.component';
<<<<<<< HEAD
import { DiscussionStudentComponent } from './discussion-student/discussion-student.component';
import { DiscussionTeacherComponent } from './discussion-teacher/discussion-teacher.component';
=======
import { ShowFeesComponent } from './show-fees/show-fees.component';
import { SetFeesComponent } from './set-fees/set-fees.component';

>>>>>>> phase_3

const routes: Routes = [
  {path:'set_fees',component:SetFeesComponent},
  {path:'show_fees',component:ShowFeesComponent},
  {path:'show_grades',component:ShowGradesComponent},
  {path:'set_grades',component:SetGradesComponent},
  {path:'set_examsTable',component:SetExamTableComponent},
  {path:'show_examTable',component:ShowExamTableComponent},
  {path:'show_schedule',component:ShowScheduleComponent},
  {path:'set_schedule',component:SetScheduleComponent},
  {path:'create_account',component:StaffComponent},
  {path:'profile',component:ProfileComponent},
  {path:'log',component:LogInComponent},
  {path:'new_Quiz',component:NewQuizComponent},
  {path:'Quiz',component:QuizComponent},
  {path:'DiscussionS',component:DiscussionStudentComponent},
  {path:'DiscussionT',component:DiscussionTeacherComponent},
  {path:'',component:LogInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
