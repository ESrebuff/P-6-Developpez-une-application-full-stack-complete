import { Component, OnInit, Input } from '@angular/core';
import { Subject } from 'src/app/core/interfaces/subject.interface';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss']
})
export class ThemeComponent implements OnInit {
  @Input() subject!: Subject;
  constructor() { }

  ngOnInit(): void {
  }

}
