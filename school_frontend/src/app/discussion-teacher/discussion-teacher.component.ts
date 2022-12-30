import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-discussion-teacher',
  templateUrl: './discussion-teacher.component.html',
  styleUrls: ['./discussion-teacher.component.css']
})
export class DiscussionTeacherComponent implements OnInit {

  constructor(private _router:Router,private http :HttpClient) { 
    this.sendRequestClass(localStorage.getItem("Id"))
  }
  chosenClass=""
  
  answer = ""
  ngOnInit(): void {
  }
  class = {
    "Id": ["100","110"],
    "Name": ["2/3","2/5"]
  }
  classes = [
]
enter(){
  console.log(this.chosenClass);

  if(this.chosenClass != ""){
        this.chooseClass = false
        this.showPosts = true
        this.showNewPost = false
        let i = {
          classId:this.chosenClass
        }
         this.sendRequestgetPosts(JSON.stringify(i))
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
  posts = [
  //   {
  //   id: "0101000",
  //   Name: "Michael",
  //   postId: "4",
  //   post: "What is your name?",
  //   date: "25/12/2022",
  //   number: 2,
  //   reply: {
  //     id: ["0101000", "15"],
  //     name: ["Salama", "3iad"],
  //     content: ["I fine thank you", "I am not fine"]
  //   }
  // },
  // {
  //   id: "",
  //   Name: "Michael",
  //   postId: "6",
  //   post: "What is your name?",
  //   date: "25/12/2022",
  //   number: 2,
  //   reply: {
  //     id: ["0101000", "15"],
  //     name: ["Salama", "3iad"],
  //     content: ["I fine thank you", "I am not fine"]
  //   }
  // }
  ]
  reply = {
    id: "",
    classId:"",
    content: "",
    parentPost: "",
    date: ""
  }
  counter(i: number) {
    return new Array(i);
  }
 
  sleep = (ms) => new Promise(r => setTimeout(r, ms));
  async addReply(index:number) {
    if (this.newReply[index] != null) {
      this.reply.id = localStorage.getItem("Id")
      this.reply.content = this.newReply[index]
      const now = new Date();
      this.reply.date = now.toLocaleDateString();
      this.reply.parentPost = this.posts[index].postId
      this.reply.classId = this.chosenClass
      console.log(JSON.stringify(this.reply));
      this.sendRequestNewReply(JSON.stringify(this.reply))
      console.log(JSON.stringify(this.reply))
      let i = {
        classId:this.chosenClass
      }
      
      await this.sleep(500);
      this.sendRequestgetPosts(JSON.stringify(i))
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
    let i = {
      classId:this.chosenClass
    }
    this.sendRequestgetPosts(JSON.stringify(i))
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
      this.sendRequestNewPost(JSON.stringify(this.newPost))
      console.log(JSON.stringify(this.newPost))
      console.log(this.post);
      this.post = ""
    }
  }
  id = ""
  You(x:string){
    // console.log(x);
     this.id = localStorage.getItem("Id")
     
     x = "\""+x+"\""
     
    if(this.id === x){
      // console.log("trueeeeeee");
      return true
      
    }
    else return false
  }


  replies =""


  // sends class id only
  sendRequestgetPosts(x: string) {
    this.posts=[]
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/discussion/show", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
            this.answer = response;
            let temp = JSON.parse(this.answer)
            for(let i=0;i<temp.length;i++){
              let x =  {
                  id: "",
                  Name: "",
                  postId: "",
                  post: "",
                  date: "",
                  number: 0,
                  reply: {
                    id: [],
                    name: [],
                    content: []
                  }
                }
                x.id = temp[i].id
                x.Name = temp[i].Name
                x.postId = temp[i].postId
                x.post = temp[i].post
                x.date = temp[i].date
                x.number = temp[i].number
                x.reply = JSON.parse(temp[i].reply)
                this.posts.push(x)
            }

            // this.reply = JSON.parse(this.posts.)
            // console.log(this.answer);
            console.log(this.posts);
            
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
      this.http.post("http://localhost:8070/School/discussion/post", x,
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
      this.http.post("http://localhost:8070/School/discussion/post", x,
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

  sendRequestClass(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8070/School/discussion/classes", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
          this.class = JSON.parse(this.answer)
          console.log(this.answer);
          for (let i = 0; i < this.class.Id.length; i++) {
            let c = {
              "Id": "",
              "Name": ""
            }
            c.Id = this.class.Id[i]
            c.Name = this.class.Name[i]
            this.classes.push(c)
          }
          console.log(this.classes);
        }
          , (error) => {
            console.log(error);
          });
    }
  }

}
