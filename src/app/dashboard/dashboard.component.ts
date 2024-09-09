import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  show: number = 0; // Initialize with a default value, e.g., show the profile

  // Method to update the 'show' variable based on the button clicked
  whatToShow(componentNumber: number) {
    this.show = componentNumber;
  }

  logout()
  {
    this.show=4;
  }
}
