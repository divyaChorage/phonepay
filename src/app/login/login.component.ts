import { Component } from '@angular/core';
import { AppComponent } from '../app.component';
import { HttpClient } from '@angular/common/http';
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {



  constructor(public http:HttpClient,public app:AppComponent) {}

    register(){
      this.app.WhatToShow=2;
    }
    mobile:number=0
    pin:number=0;
    login()
    {
      let a=[this.mobile,this.pin];
      let url=this.app.baseUrl+'login';
      console.log(a)
      this.http.post(url,a).subscribe((data:any)=>
      {
        console.log(data)
if(data==null||data==-1)
{
  window.alert('something is wrong');
}
else
{
  
  console.log(this.app.id,"app id")
  console.log(data,"data")
  this.app.id=data;
  this.app.WhatToShow=3;
}
      });
  
      
    }
  
}
