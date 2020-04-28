import { AfterViewChecked, Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../users/user';
import { UserService } from '../users/users.service';
import { Todo } from './todo';
import { TodoService } from './todo.service';

declare var jQuery: any;

@Component({
  selector: 'todo-list',
  templateUrl: './todolist.component.html',
  providers: [TodoService, UserService],
})
export class TodoListComponent implements AfterViewChecked {
  todos: Todo[];
  user: User;
  todoContent: String;
  interval: any;
  constructor(
    private todoService: TodoService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.userService.getCurrentUser().then((user) => {
      this.checkUser(user);
    });
  }

  public checkUser(user: User): void {
    this.user = user;
    if (!this.user.id) {
      this.router.navigate(['/login']);
    } else {
      this.update();
      this.interval = setInterval(() => {
        this.update();
      }, 10000);
    }
  }

  update() {
    this.todoService.getTodos().then((todos) => {
      this.todos = todos;
    });
  }

  ngAfterViewChecked() {
    jQuery('.delete-todo-button').tooltip();
  }

  nuevoTodo() {
    this.todoContent = '';
    jQuery('#addTodo').modal('show');
    setTimeout(function () {
      jQuery('#todoContent').focus();
    });
  }

  guardarTodo() {
    this.todoService.saveTodo(this.todoContent).then(() => {
      jQuery('#addTodo').modal('hide');
      this.update();
    });
  }

  borrarTodo(todo: Todo) {
    this.todoService.deleteTodo(todo).then(() => {
      jQuery('.delete-todo-button').tooltip('hide');
      this.update();
    });
  }
}
