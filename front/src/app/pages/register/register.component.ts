import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  username: string = '';
  name: string = '';
  password: string = '';
  emailValid: boolean = false;
  nameValid: boolean = false;
  passwordValid: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(registerForm: NgForm): void {
    if (this.emailValid && this.nameValid && this.passwordValid) {
      const credentials = {
        username: this.username,
        name: this.name,
        password: this.password
      };

      this.authService.register(credentials)
      .pipe(take(1))
      .subscribe(
        () => {
          this.router.navigate(['/profile']);
        },
        (error) => {
          console.error('Erreur d\'authentification', error);
        }
      );
    } else {
        console.error('Erreur d\'authentification');
        alert("Un problème s'est produit !");
    }
  }

  validateEmail(): void {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    this.emailValid = emailRegex.test(this.username);
  }

  validateName(): void {
    this.nameValid = this.name.trim().length > 0;
  }

  validPassword(): void {
    const regexDigit = /\d/;
    const regexLowercase = /[a-z]/;
    const regexUppercase = /[A-Z]/;
    const regexSpecialChar = /[!@#$%^&*()-+=]/;

    this.passwordValid = (
      this.password.length >= 8 &&
      regexDigit.test(this.password) &&
      regexLowercase.test(this.password) &&
      regexUppercase.test(this.password) &&
      regexSpecialChar.test(this.password)
    );
  }

}
