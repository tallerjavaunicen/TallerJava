import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Log } from './log';


@Injectable()
export class LogService {
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private logUrl = 'api/log'; // URL to web api
  constructor(private http: HttpClient) {}
  getLogs(): Promise<Log[]> {
    return this.http
      .get(this.logUrl)
      .toPromise()
      .then((response) => response as Log[])
      .catch(this.handleError);
  }
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
