


import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-link-account',
  templateUrl: './link-account.component.html',
  styleUrls: ['./link-account.component.css']
})
export class LinkAccountComponent implements OnInit {
  list: any[] = []; // Non-linked accounts
  list1: any[] = []; // Linked accounts
  linkid!: number;
  pin!: number;
  upi!: string;
  otp!: number;

  constructor(public http: HttpClient, public app: AppComponent) {}

  ngOnInit() {
    this.loadAccounts();
  }

  loadAccounts() {
    const linkedAccounts = localStorage.getItem('linkedAccounts');
    const nonLinkedAccounts = localStorage.getItem('nonLinkedAccounts');

    if (linkedAccounts && nonLinkedAccounts) {
      this.list1 = JSON.parse(linkedAccounts);
      this.list = JSON.parse(nonLinkedAccounts);
    } else {
      this.fetchAccounts();
    }
  }

  fetchAccounts() {
    let url1 = this.app.baseUrl + "getLinkedAcc/" + this.app.id;
    this.http.get(url1).subscribe((data: any) => {
      this.list1 = data;
      localStorage.setItem('linkedAccounts', JSON.stringify(this.list1));
    });

    let url2 = this.app.baseUrl + "getnonLinkedAcc/" + this.app.id;
    this.http.get(url2).subscribe((data: any) => {
      this.list = data;
      localStorage.setItem('nonLinkedAccounts', JSON.stringify(this.list));
    });
  }

  linkaccount(bid: number) {
    this.list.forEach(item => {
      if (item.id === bid) {
        item.show = 1;
      }
    });

    let url1 = this.app.baseUrl + "getPin/" + this.app.id + "/" + bid;
    this.http.get(url1).subscribe((data: any) => {
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

    let url = this.app.baseUrl + "verfylinking";
    this.http.post(url, verify).subscribe((data: any) => {
      if (data == null || data === -1) {
        window.alert("Something is wrong");
      } else {
        window.alert("Account linked");
        this.updateAccounts(); // Refresh data after linking
      }
    });
  }

  updateAccounts() {
    let url1 = this.app.baseUrl + "getLinkedAcc/" + this.app.id;
    this.http.get(url1).subscribe((data: any) => {
      this.list1 = data;
      localStorage.setItem('linkedAccounts', JSON.stringify(this.list1));
    });

    let url2 = this.app.baseUrl + "getnonLinkedAcc/" + this.app.id;
    this.http.get(url2).subscribe((data: any) => {
      this.list = data;
      localStorage.setItem('nonLinkedAccounts', JSON.stringify(this.list));
    });
  }

  delete(id: number) {
    let url = this.app.baseUrl + "deletelinking/" + id;
    this.http.delete(url).subscribe((data: any) => {
      if (data == 0 || data === -1) {
        window.alert("Something is wrong");
      } else {
        window.alert("Unlink account");
        this.updateAccounts(); // Refresh data after unlinking
      }
    });
  }

  unLink(bankid: number) {
    let url = this.app.baseUrl + "unlinkBankAccount/" + bankid;
    this.http.delete(url).subscribe((data: any) => {
      if (data == null || data === -1) {
        window.alert("Something is wrong");
      } else {
        const unlinkedAccount = this.list1.find((item) => item.id === bankid);
        this.list1 = this.list1.filter((item) => item.id !== bankid);
        if (unlinkedAccount) {
          this.list = [...this.list, unlinkedAccount];
        }
        localStorage.setItem('linkedAccounts', JSON.stringify(this.list1));
        localStorage.setItem('nonLinkedAccounts', JSON.stringify(this.list));
      }
    });
  }
}






