import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {


    transactionHistory: any[] = [];
    constructor(private http: HttpClient,private app:AppComponent) { }
  
    ngOnInit(): void {
      this.loadTransactionHistory();
    }
  
    loadTransactionHistory(): void {

      let url1=this.app.baseUrl+"transactionHistory/"+this.app.id;
        this.http.get(url1).subscribe((data: any) => {
          console.log("history___",data)
          this.transactionHistory = data;
        
        },
        error => {
          console.error('Error fetching transaction history', error);
        }
      );
    }
  }