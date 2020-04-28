import { Injectable } from '@angular/core';

import { User } from './user';
import { HttpClient } from '@angular/common/http';
@Injectable()
export class UserService {
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private userUrl = 'api/user'; // URL to web api
  constructor(private http: HttpClient) {}
  getUsers(): Promise<User[]> {
    return this.http
      .get(this.userUrl)
      .toPromise()
      .then((response) => response as User[])
      .catch(this.handleError);
  }

  getCurrentUser(): Promise<User> {
    return this.http
      .get('api/currentuser')
      .toPromise()
      .then((response) => response as User)
      .catch(this.handleError);
  }

  login(userName: String): Promise<User> {
    var user: User = new User();
    user.name = userName;
    return this.http
      .post(this.userUrl, user)
      .toPromise()
      .then((response) => response as User)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
