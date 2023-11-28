import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-public',
  templateUrl: './header-public.component.html',
  styleUrls: ['./header-public.component.scss']
})
export class HeaderPublicComponent implements OnInit {
  @Input() title!: string;

  constructor() { }

  ngOnInit(): void {
  }

}
