import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-discussion-student',
  templateUrl: './discussion-student.component.html',
  styleUrls: ['./discussion-student.component.css']
})
export class DiscussionStudentComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) {
      let id = localStorage.getItem("Id")
      // this.sendRequestgetPosts(id)
      this.newReply = this.counter(this.posts.length)
      console.log(this.newReply);
  }
  answer=""
  showNewPost = false
  showPosts = true
  displayAlert = "None"
  displayDone ="None"
  post = ""
  newReply = []
  newPost = {
    id: "",
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
  ngOnInit(): void {
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
