Create Schema SCHOOL;
Use SCHOOL;

CREATE TABLE PERSON ( 
Id nCHAR(14) NOT NULL,
Address nvarchar(40) NOT NULL,
Phone nCHAR(12),
Name nvarchar(30) NOT NULL,
National_Id nchar(14) NOT NULL,
Sex nvarchar(6) NOT NULL ,
Password nvarchar(20) NOT NULL ,
PRIMARY KEY (Id) ,
CONSTRAINT PERSON_UN UNIQUE KEY (National_Id)
); 

CREATE TABLE TERM ( 
Term_Id nCHAR(14) NOT NULL,
Name nvarchar(20) NOT NULL,
PRIMARY KEY (Term_Id)
 ); 

CREATE TABLE TEACHER (
Teacher_Id nCHAR(14) NOT NULL,
Experience nvarchar(10) ,
Sub nvarchar(15) NOT NULL ,
PRIMARY KEY (Teacher_Id) , 
Foreign Key ( Teacher_Id) REFERENCES PERSON (Id) on delete cascade on update cascade 
); 

CREATE TABLE CLASS (
Class_Id nCHAR(14) NOT NULL,
Name nVARCHAR(40) NOT NULL,
Manger_Id nCHAR(14) NOT NULL,
PRIMARY KEY (Class_Id) , 
Foreign Key ( Manger_Id) REFERENCES TEACHER (Teacher_Id) on delete cascade on update cascade
 );
 
CREATE TABLE Teach (
Class_Id nCHAR(14) NOT NULL,
Teacher_Id nCHAR(14) NOT NULL,
primary key(Class_Id , Teacher_Id),
Foreign Key ( Teacher_Id) REFERENCES TEACHER (Teacher_Id) on delete cascade on update cascade,
Foreign Key ( Class_Id) REFERENCES CLASS (Class_Id) on delete cascade on update cascade
 );
 
CREATE TABLE PARENT (
P_id nchar(14) NOT NULL ,
Jop nvarchar(20) ,
PRIMARY KEY (P_id) ,
foreign Key ( P_id) REFERENCES PERSON (Id) on delete cascade on update cascade
);

CREATE TABLE STAFF (
S_id nchar(14) NOT NULL ,
Jop nvarchar(20) NOT NULL , 
PRIMARY KEY (S_id) ,
foreign Key ( S_id) REFERENCES PERSON (Id) on delete cascade on update cascade
);

CREATE TABLE STUDENT (
St_Id nCHAR(14) NOT NULL,
Class_Id nCHAR(14) NOT NULL,
St_Term_Id nchar(14) NOT NULL,
Curr_Term_Id nCHAR(14) NOT NULL,
P_id char(14) NOT NULL ,
PRIMARY KEY (St_Id) ,
Foreign Key ( St_Id) REFERENCES PERSON (Id) on delete cascade on update cascade); 

CREATE TABLE Fees(
fees int not null,
year int ,
primary key(year)
);

CREATE TABLE SUB (
Sub_Id nchar(14) NOT NULL ,
Name nvarchar(30) NOT NULL,
Max_grade int NOT NULL ,
Min_grade int NOT NULL ,
exam_grade int ,
In_grade boolean ,
Exam_Date nvarchar(40) ,  
PRIMARY KEY (Sub_Id)
);

CREATE TABLE ENROLMENT (
Sub_Id nchar(14) NOT NULL ,
St_Id nchar(14) NOT NULL ,
Term_Id nchar(14) NOT NULL ,
Date_Flag boolean , 
Year_Works int ,
Exam_grade int , 
Grade_Flag boolean,
state boolean ,
PRIMARY KEY (Sub_Id , St_Id , Term_Id) ,
foreign Key ( Sub_Id) REFERENCES SUB (Sub_Id) on delete cascade on update cascade ,
foreign Key ( St_Id) REFERENCES STUDENT (St_Id) on delete cascade on update cascade ,
foreign Key ( Term_Id) REFERENCES TERM (Term_Id) on delete cascade on update cascade
);

CREATE TABLE QUIZ (
Quiz_Id nchar(14) NOT NULL ,
name nvarchar(1000) not null,
endTime int not null,
Class_Id nchar(14) NOT NULL ,
PRIMARY KEY (Quiz_Id , Class_Id) ,
foreign Key ( Class_Id) REFERENCES CLASS (Class_Id) on delete cascade on update cascade
);

CREATE TABLE Question(
Quiz_Id nchar(14) NOT NULL ,
Question_Id nchar(14) NOT NULL ,
Question varchar(10000) not null,
choice1 varchar(1000) not null,
choice2 varchar(1000) not null,
choice3 varchar(1000) not null,
choice4 varchar(1000) not null,
Solution int NOT NULL ,
primary key(Quiz_Id,Question_Id),
foreign Key ( Quiz_Id) REFERENCES Quiz (Quiz_Id) on delete cascade on update cascade
);

CREATE TABLE DO_QUIZ (
Quiz_Id nchar(14) NOT NULL ,
St_Id nchar(14) NOT NULL ,
grade int ,
SubmissionTime date,
PRIMARY KEY (Quiz_Id , St_Id) ,
foreign Key ( St_Id) REFERENCES STUDENT (St_Id) on delete cascade on update cascade ,
foreign Key ( Quiz_Id) REFERENCES QUIZ (Quiz_Id) on delete cascade on update cascade
);

CREATE TABLE CLASS_DIALY_TABLE (
Class_Id nchar(14) NOT NULL ,
Term_Id nchar(14) NOT NULL ,
Day nvarchar(10) NOT NULL ,
Lec_1  nvarchar(30) , 
Lec_2  nvarchar(30) ,
Lec_3  nvarchar(30) ,
Lec_4  nvarchar(30) ,
Lec_5  nvarchar(30) , 
Lec_6  nvarchar(30) , 
PRIMARY KEY (Class_Id , Term_Id,Day) ,
foreign Key ( Class_Id) REFERENCES CLASS (Class_Id) on delete cascade on update cascade ,
foreign Key ( Term_Id) REFERENCES TERM (Term_Id) on delete cascade on update cascade
);

CREATE TABLE TEACHER_DIALY_TABLE (
Teacher_Id nchar(14) NOT NULL ,
Day varchar(10) NOT NULL ,
Lec_1  nvarchar(30) , 
Lec_2  nvarchar(30) ,
Lec_3  nvarchar(30) ,
Lec_4  nvarchar(30) ,
Lec_5  nvarchar(30) , 
Lec_6  nvarchar(30) , 
PRIMARY KEY (Teacher_Id,Day) ,
Foreign Key ( Teacher_Id) REFERENCES TEACHER (Teacher_Id) on delete cascade on update cascade
);
