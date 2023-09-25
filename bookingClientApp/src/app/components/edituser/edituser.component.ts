import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth-service.service';
import {User} from "../../model/user.model";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {
  user: any = {}; // Initialize user object with empty values

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
      this.user = this.authService.getLoggedInUser();
  }

  onSubmit() {
    // Handle form submission and update user details
    // You can call a service method to update the user details here
    // Example: this.authService.updateUserDetails(this.user);
  }
}
