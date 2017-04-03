import { Component, ViewChild, ElementRef} from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../users/users.service';
import { User } from '../users/user';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app',
    template: `<div style="min-height: 100%;min-height: 100vh;display: flex;align-items: center;justify-content: center;">
                <div class="login" style="max-width:300px;">
                    <div class="panel panel-success">
                      <div class="panel-heading">
                        <h3 class="panel-title">Ingrese un nombre de usuario</h3>
                      </div>
                      <div class="panel-body">
                        <form #loginForm="ngForm" (ngSubmit)="login()">
                          <div class="input-group">
                              <input #loginInput class="form-control" [(ngModel)]="nombreUsuario" placeholder="Nombre de usuario" ng-model="password" name="username" id="username" required>
                              <span class="input-group-btn">
                                <button class="btn btn-primary" type="submit">
                                  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </button>
                              </span>
                          </div>
                        </form>
                      </div>
                    </div>
                </div>
              </div>`,
    providers: [UserService]
})
export class LoginComponent {
    @ViewChild("loginInput")
    private _inputElement: ElementRef;
    nombreUsuario: String;
    user: User;
    constructor(private router: Router,
        private userService: UserService) {
        userService.getCurrentUser().then(user => {
            this.checkUser(user)
        });
    }

    public checkUser(user:User): void{
      this.user = user;
      if (this.user.id) {
          this.router.navigate(["/todos"]);
      }
    }

    login(): void {
      this.userService.login(this.nombreUsuario).then(user => this.checkUser(user));
    }

    ngAfterViewInit(): void {
       this._inputElement.nativeElement.focus();
   }
}
