import { Component } from '@angular/core';
import { UserService } from '../users/users.service';
import { User } from '../users/user';

@Component({
  selector: 'users',
  template: `<div class="panel panel-success">
  <div class="panel-heading">
    <h3 class="panel-title">Logged Users</h3>
  </div>
  <div class="panel-body">
    <div *ngFor="let user of userlist">
      {{user.name}}
    </div>
  </div>
</div>
`,
  providers: [UserService]
})
export class UsersComponent  {
  interval:any;
  userlist: User[];
  constructor(private userService: UserService) {
    this.update();
    this.interval = setInterval(() => {
      this.update();
    }, 5000);
  };

  update(){
    this.userService.getUsers().then(userlist => {
        this.userlist = userlist;
    });
  }
}
