import { Component } from '@angular/core';
import { UserService } from './users.service';
import { User } from './user';

@Component({
  selector: 'users',
  template: `<div class="card card-success">
    <div class="card-header">
      Logged Users
    </div>
    <div class="card-body">
      <div *ngFor="let user of userlist">
        {{ user.name }}
      </div>
    </div>
  </div> `,
  providers: [UserService],
})
export class UsersComponent {
  interval: any;
  userlist: User[];
  constructor(private userService: UserService) {
    this.update();
    this.interval = setInterval(() => {
      this.update();
    }, 5000);
  }

  update() {
    this.userService.getUsers().then((userlist) => {
      this.userlist = userlist;
    });
  }
}
