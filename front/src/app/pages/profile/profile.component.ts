import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  themes = [
    { title: 'Theme 1', author: 'author' },
    { title: 'Theme 2', author: 'author' },
    { title: 'Theme 3', author: 'author' },
    { title: 'Theme 4', author: 'author' },
    { title: 'Theme 5', author: 'author' },
    { title: 'Theme 6', author: 'author' },
    { title: 'Theme 7', author: 'author' },
    { title: 'Theme 8', author: 'author' },
    { title: 'Theme 9', author: 'author' }
  ];
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  async logOut(): Promise<void> {
    await this.authService.logout();
    this.router.navigate(['/']);
  }

}
