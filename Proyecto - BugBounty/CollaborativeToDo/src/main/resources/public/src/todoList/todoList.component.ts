import { Component, AfterViewChecked  } from '@angular/core';
import { UserService } from '../users/users.service';
import { TodoService } from './todo.service';
import { Todo } from './todo';
import { User } from '../users/user';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

declare var jQuery: any;

@Component({
    selector: 'todo-list',
    templateUrl: 'src/todoList/todoList.component.html',
    providers: [TodoService, UserService]
})

export class TodoListComponent implements AfterViewChecked {
    todos: Todo[];
    user: User;
    todoContent: String;
    interval: any;
    constructor(private todoService: TodoService, private router: Router, private userService: UserService) { };

    ngOnInit() {
        this.userService.getCurrentUser().then(user => {
            this.checkUser(user)
        });
    }

    public checkUser(user: User): void {
        this.user = user;
        if (!this.user.id) {
            this.router.navigate(["/login"]);
        } else {
            this.update();
            this.interval = setInterval(() => {
                this.update();
            }, 10000);
        }
    }

    update() {
        this.todoService.getTodos().then(todos => {
            this.todos = todos;
        });
    }

    ngAfterViewChecked() {
        jQuery(".delete-todo-button").tooltip();
    }

    nuevoTodo() {
        this.todoContent = "";
        jQuery("#addTodo").modal('show');
        setTimeout(function() {
          jQuery("#todoContent").focus();
        }, 500);
    }

    guardarTodo() {
        this.todoService.saveTodo(this.todoContent).then(() => {
            jQuery("#addTodo").modal('hide');
            this.update();
        });
    }

    borrarTodo(todo: Todo){
      this.todoService.deleteTodo(todo).then(() => {
          this.update();
      });
    }
}
