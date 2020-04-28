import { Component } from '@angular/core';

@Component({
  selector: 'app',
  template: `<div class="row" style="padding:1em;">
                  <div class="col-sm-3">
                    <users>List of Users</users>
                  </div>
                  <div class="col-sm-6">
                    <todo-list>Todo List</todo-list>
                  </div>
                  <div class="col-sm-3">
                    <log>Action Log</log>
                  </div>
              </div>`,
})
export class PpalComponent  {}
