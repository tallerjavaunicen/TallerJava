import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }  from './app.component';
import { UsersComponent } from '../users/users.component';
import { LogComponent } from '../log/log.component';
import { TodoListComponent } from '../todoList/todoList.component';
import { LoginComponent } from '../login/login.component';
import { PpalComponent } from '../ppal/ppal.component';
import { HttpModule }    from '@angular/http';
import { RouterModule }   from '@angular/router'

@NgModule({
  imports:      [ BrowserModule, HttpModule,
    FormsModule,
    RouterModule.forRoot([
        {
          path: '',
          redirectTo: '/login',
          pathMatch: 'full'
        },
        {
          path: 'login',
          component: LoginComponent
        },
        {
          path: 'todos',
          component: PpalComponent
        }
      ])],
  declarations: [ AppComponent, UsersComponent, TodoListComponent, LoginComponent, PpalComponent, LogComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
