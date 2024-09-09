// import { HttpClient } from '@angular/common/http';
// import { Component } from '@angular/core';
// import { AppComponent } from 'src/app/app.component';

// @Component({
//   selector: 'app-link-account',
//   templateUrl: './link-account.component.html',
//   styleUrls: ['./link-account.component.css']
// })
// export class LinkAccountComponent {
//   list:any; //nonlinke acc
//   list1:any;  //linked acc


//   constructor(public http:HttpClient,public app:AppComponent) {
//     // linksed accounts
//     let url1=app.baseUrl+"getLinkedAcc/"+app.id;
//     http.get(url1).subscribe((data:any)=>{
//       this.list1=data;
//     })

//     // non linked accounts
//     let url=app.baseUrl+"getnonLinkedAcc/"+app.id;
//     http.get(url).subscribe((data:any)=>{
//       this.list=data;
//       // for(let i=0;i<this.list.length;i++){
//       //     this.list[i].show=0;
//       // }
//     })
//   }
//   linkid!:number;
//   linkaccount(bid:number){
//     for(let i=0;i<this.list.length;i++){
//       console.log(this.list[i])
//       if (this.list[i].id=bid) {
//         this.list[i].show=1;
//       }
//     }
//     let url1=this.app.baseUrl+"getPin/"+this.app.id+"/"+bid;
//     this.http.get(url1).subscribe((data:any)=>{
//     // window.alert(data);
//     this.linkid=data;
//   })
// }
// pin!:number;
// upi!:string;
// otp!:number;

// submitLinking(){
//   let verify={
//     pin:this.pin,
//     upi:this.upi,
//     otp:this.otp,
//     id:this.linkid
//   }

//   let url=this.app.baseUrl+"verfylinking";
//   this.http.post(url,verify).subscribe((data:any)=>{
//     if (data==null || data==-1) {
//       window.alert("something is wrong")
//     } else {
//       window.alert("acount linkd")
//     }
//   })
//   this.list=[];
//   this.list1=[];
  
//   let url1=this.app.baseUrl+"getLinkedAcc/"+this.app.id;
//   this.http.get(url1).subscribe((data:any)=>{
//     this.list1=data;
//   })

//   // non linked accounts
//   let url2=this.app.baseUrl+"getnonLinkedAcc/"+this.app.id;
//   this.http.get(url2).subscribe((data:any)=>{
//     this.list=data;
//     // for(let i=0;i<this.list.length;i++){
//     //     this.list[i].show=0;
//     // }
//   })
    
//   }


// delete(id:number){

// }



  
// }

import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-link-account',
  templateUrl: './link-account.component.html',
  styleUrls: ['./link-account.component.css']
})
export class LinkAccountComponent {
  list: any[] = []; // non-linked accounts
  list1: any[] = []; // linked accounts
  linkid!: number;
  pin!: number;
  upi!: string;
  otp!: number;

  constructor(public http: HttpClient, public app: AppComponent) {
    this.loadAccounts();
  }

  loadAccounts() {
    let url1=this.app.baseUrl+"getLinkedAcc/"+this.app.id;
    this.http.get(url1).subscribe((data: any) => {
      this.list1 = data;
    });

    let url2=this.app.baseUrl+"getnonLinkedAcc/"+this.app.id;
    this.http.get(url2).subscribe((data: any) => {
      this.list = data;
    });
  }

  linkaccount(bid: number) {
    this.list.forEach(item => {
      if (item.id === bid) {
        item.show = 1;
      }
    });
    let url1=this.app.baseUrl+"getPin/"+this.app.id+"/"+bid;
    this.http.get(url1).subscribe((data: any) => {
      console.log(data,"data is  linked")
      this.linkid = data;
    });
  }

  submitLinking() {
    const verify = {
      pin: this.pin,
      upi: this.upi,
      otp: this.otp,
      id: this.linkid
    };

    let url=this.app.baseUrl+"verfylinking";
    this.http.post(url, verify).subscribe((data: any) => {
      if (data == null || data === -1) {
        window.alert("Something is wrong");
      } else {
        window.alert("Account linked");
        this.loadAccounts(); // Reload accounts after successful linking
      }
    });
  }

  delete(id: number) {
console.log(id)
    let url=this.app.baseUrl+"deletelinking/"+id;
    this.http.delete(url).subscribe((data: any) => {

      if (data == 0 || data === -1) {
        window.alert("Something is wrong");
      } else {
        window.alert("unlink accounts");
        this.loadAccounts(); // Reload accounts after successful linking
      }
    });
  }
}



