import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-register',
  templateUrl: './header-register.component.html',
  styleUrls: ['./header-register.component.scss']
})
export class HeaderRegisterComponent implements OnInit {
  @Input() title!: string;

  constructor() { }

  ngOnInit(): void {
  }

}
