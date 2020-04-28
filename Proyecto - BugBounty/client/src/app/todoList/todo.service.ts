import { Injectable } from '@angular/core';

import { Todo } from './todo';
import { HttpClient } from '@angular/common/http';
@Injectable()
export class TodoService {
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private todoURL = 'api/todo'; // URL to web api
  constructor(private http: HttpClient) {}
  getTodos(): Promise<Todo[]> {
    return this.http
      .get(this.todoURL)
      .toPromise()
      .then((response) => response as Todo[])
      .catch(this.handleError);
  }

  saveTodo(todoContent: String): Promise<Todo> {
    var todo: Todo = new Todo();
    todo.content = todoContent;
    return this.http
      .post(this.todoURL, todo)
      .toPromise()
      .then((response) => response as Todo)
      .catch(this.handleError);
  }

  deleteTodo(todo: Todo): Promise<void> {
    return this.http
      .delete(`${this.todoURL}/${todo.id}`)
      .toPromise()
      .then(() => {})
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
