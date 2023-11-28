import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-private',
  templateUrl: './header-private.component.html',
  styleUrls: ['./header-private.component.scss']
})
export class HeaderPrivateComponent implements OnInit {
  @Input() activePage!: string;

  isElementVisible: boolean = false;

  toggleVisibility(): void {
    this.isElementVisible = !this.isElementVisible;
  }
  constructor() { }

  ngOnInit(): void {
  }

}
