import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  private sub: Subscription |undefined;

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(loginForm: NgForm): void {
    const credentials = {
      username: this.username,
      password: this.password
    };

    this.sub = this.authService.login(credentials).subscribe(
      () => {
        this.router.navigate(['/profile']);
      },
      (error) => {
        console.error('Erreur d\'authentification', error);
        alert("Un problème s'est produit !");
      }
    );
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
  }

}
