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
import { QuizGradeComponent } from './quiz-grade/quiz-grade.component';
import { QuizDoneComponent } from './quiz-done/quiz-done.component';

const routes: Routes = [
  {path:'quiz_grade',component:QuizGradeComponent},
  {path:'quiz_done',component:QuizDoneComponent},
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
  {path:'',component:LogInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
