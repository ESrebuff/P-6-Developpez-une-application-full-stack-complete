import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  
  onSubmit(loginForm: NgForm): void {
    const credentials = {
      username: this.username,
      password: this.password
    };

    this.authService.login(credentials)
      .pipe(take(1))
      .subscribe(
        () => {
          this.router.navigate(['/profile']);
        },
        (error) => {
          console.error('Erreur d\'authentification', error);
          alert("Un problème s'est produit !");
        }
      );
  }

}
