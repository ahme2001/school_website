import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-discussion-teacher',
  templateUrl: './discussion-teacher.component.html',
  styleUrls: ['./discussion-teacher.component.css']
})
export class DiscussionTeacherComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) { }
  chosenClass=""
  
  answer = ""
  ngOnInit(): void {
  }
  class = {
    "Id": ["100","110"],
    "Name": ["2/3","2/5"]
  }
  classes = [{
    "id":"100",
    "Name":"2/3"
  },
  {
    "id":"110",
    "Name":"2/5"
  }
]
enter(){
  if(this.chosenClass != ""){
        this.chooseClass = false
        this.showPosts = true
        this.showNewPost = false
        //  this.sendRequestgetPosts(this.chosenClass)
  }
}
  chooseClass = true
  showNewPost = false
  showPosts = false
  displayAlert = "None"
  displayDone ="None"
  post = ""
  newReply = []
  newPost = {
    id: "",
    classId:"",
    content: "",
    date: ""
  }
  posts = [{
    id: "0101000",
    Name: "Michael",
    postId: "4",
    post: "What is your name?",
    date: "25/12/2022",
    number: 2,
    reply: {
      id: ["0101000", "15"],
      name: ["Salama", "3iad"],
      content: ["I fine thank you", "I am not fine"]
    }
  },
  {
    id: "",
    Name: "Michael",
    postId: "6",
    post: "What is your name?",
    date: "25/12/2022",
    number: 2,
    reply: {
      id: ["0101000", "15"],
      name: ["Salama", "3iad"],
      content: ["I fine thank you", "I am not fine"]
    }
  }
  ]
  reply = {
    id: "",
    content: "",
    postid: "",
    date: ""
  }
  counter(i: number) {
    return new Array(i);
  }

  addReply(index:number) {
    if (this.newReply[index] != null) {
      this.reply.id = localStorage.getItem("Id")
      this.reply.content = this.newReply[index]
      const now = new Date();
      this.reply.date = now.toLocaleDateString();
      this.reply.postid = this.posts[index].postId
      // this.sendRequestNewReply(JSON.stringify(this.reply))
      console.log(JSON.stringify(this.reply))
      // this.sendRequestgetPosts(localStorage.getItem("Id"))
      this.newReply = this.counter(this.posts.length)
      // window.location.reload()
    }
  }
  add(){
     this.showNewPost = true
     this.showPosts = false
  }
  cancel(){
    this.showNewPost = false
    this.showPosts = true
 }
  submit() {
    if (this.post == "") {
      this.displayAlert = "block"
      this.displayDone = "None"
    }
    else {
      this.displayAlert = "None"
      this.displayDone = "block"
      
      this.newPost.id = localStorage.getItem("Id")
      this.newPost.content = this.post
      const now = new Date();
      this.newPost.date = now.toLocaleDateString();
      this.newPost.classId = this.chosenClass
      // this.sendRequestNewPost(JSON.stringify(this.newPost))
      console.log(JSON.stringify(this.newPost))
      console.log(this.post);
      this.post = ""
    }
  }
  id = ""
  You(x:string){
    console.log(x);
     this.id = localStorage.getItem("Id")
     console.log(Number(this.id));
     x = "\""+x+"\""
     
    if(this.id === x){
      console.log("trueeeeeee");
      return true
      
    }
    else return false
  }



  // sends class id only
  sendRequestgetPosts(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            this.posts = JSON.parse(this.answer)
            console.log(this.answer);
        }
          , (error) => {
            console.log(error);
          });
    }
  }

  // zwdt 2l class id
  sendRequestNewPost(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            console.log(this.answer);
        }
          , (error) => {
            console.log(error);
          });
    }
  }

  // reply
  sendRequestNewReply(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/login", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            console.log(this.answer);
        }
          , (error) => {
            console.log(error);
          });
    }
  }

}
