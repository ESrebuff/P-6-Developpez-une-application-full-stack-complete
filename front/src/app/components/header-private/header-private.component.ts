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

    if (this.isElementVisible) {
      document.body.classList.add('no-scroll');
    } else {
      document.body.classList.remove('no-scroll');
    }
  }
  constructor() { }

  ngOnInit(): void {
  }

}
