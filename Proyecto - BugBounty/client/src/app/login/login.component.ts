import { Component, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../users/users.service';
import { User } from '../users/user';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app',
  template: `<div
    style="min-height: 100%;min-height: 100vh;display: flex;align-items: center;justify-content: center;"
  >
    <div class="login" style="max-width:300px;">
      <div class="card card-success">
        <div class="card-header">
          Ingrese un nombre de usuario
        </div>
        <div class="card-body">
          <form #loginForm="ngForm" (ngSubmit)="login()">
            <div class="input-group">
              <input
                #loginInput
                class="form-control"
                [(ngModel)]="nombreUsuario"
                placeholder="Nombre de usuario"
                ng-model="password"
                name="username"
                id="username"
                required
              />
              <span class="input-group-btn">
                <button class="btn btn-primary" type="submit">
                  <svg
                    class="bi bi-check"
                    width="1em"
                    height="1em"
                    viewBox="0 0 16 16"
                    fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M13.854 3.646a.5.5 0 010 .708l-7 7a.5.5 0 01-.708 0l-3.5-3.5a.5.5 0 11.708-.708L6.5 10.293l6.646-6.647a.5.5 0 01.708 0z"
                      clip-rule="evenodd"
                    />
                  </svg>
                </button>
              </span>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>`,
  providers: [UserService],
})
export class LoginComponent {
  @ViewChild('loginInput')
  private _inputElement: ElementRef;
  nombreUsuario: String;
  user: User;
  constructor(private router: Router, private userService: UserService) {
    userService.getCurrentUser().then((user) => {
      this.checkUser(user);
    });
  }

  public checkUser(user: User): void {
    this.user = user;
    if (this.user && this.user.id != null) {
      this.router.navigate(['/todos']);
    }
  }

  login(): void {
    this.userService
      .login(this.nombreUsuario)
      .then((user) => this.checkUser(user));
  }

  ngAfterViewInit(): void {
    this._inputElement.nativeElement.focus();
  }
}
