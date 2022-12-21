import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogInComponent } from './log-in/log-in.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ProfileComponent } from './profile/profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StaffComponent } from './staff/staff.component';
import { HttpClientModule } from '@angular/common/http';
import { NewQuizComponent } from './new-quiz/new-quiz.component';
import { QuizComponent } from './quiz/quiz.component';
import { SetExamTableComponent } from './set-exam-table/set-exam-table.component';
import { ShowExamTableComponent } from './show-exam-table/show-exam-table.component';
import { SetScheduleComponent } from './set-schedule/set-schedule.component';
import { ShowScheduleComponent } from './show-schedule/show-schedule.component';


@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    SidebarComponent,
    ProfileComponent,
    StaffComponent,
    NewQuizComponent,
    QuizComponent,
    SetExamTableComponent,
    ShowExamTableComponent,
    SetScheduleComponent,
    ShowScheduleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
