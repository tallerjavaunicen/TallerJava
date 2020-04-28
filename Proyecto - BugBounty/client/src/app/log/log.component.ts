import { Component } from '@angular/core';
import { LogService } from './log.service';
import { Log } from './log';

@Component({
  selector: 'log',
  template: `<div class="card card-default">
    <div class="card-header">
      Actions Log
    </div>
    <div class="card-body">
      <div *ngFor="let logItem of log">
        {{ logItem.user.name }}: {{ logItem.action }}
      </div>
    </div>
  </div> `,
  providers: [LogService],
})
export class LogComponent {
  interval: any;
  log: Log[];
  constructor(private logService: LogService) {
    this.update();
    this.interval = setInterval(() => {
      this.update();
    }, 5000);
  }

  update() {
    this.logService.getLogs().then((log) => {
      this.log = log;
    });
  }
}
