import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  username: string = '';
  name: string = '';
  password: string = '';
  emailValid: boolean = false;
  nameValid: boolean = false;
  passwordValid: boolean = false;
  passwordRules: string[] = [];
  private subs: Subscription[] = [];

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
    this.subs.push(
      this.authService.getCurrentUser().subscribe(
        (userData) => {
          this.username = userData.username;
          this.name = userData.name;
        },
        (error) => {
          console.error('Erreur d\'authentification', error);
        }
      )
    );
  }

  onSubmit(registerForm: NgForm): void {
    if (this.emailValid && this.nameValid && this.passwordValid) {
      const credentials = {
        username: this.username,
        name: this.name,
        password: this.password
      };

      this.subs.push(this.authService.register(credentials).subscribe(
        () => {
          this.router.navigate(['/profile']);
        },
        (error) => {
          console.error('Erreur d\'authentification', error);
        }
      ));
    } else {
      console.error('Erreur d\'authentification');
      alert("Un problème s'est produit !");
    }
  }

  async logOut(): Promise<void> {
    await this.authService.logout();
    this.router.navigate(['/']);
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

    this.passwordRules = [];

    if (this.password.length < 8) {
      this.passwordRules.push('Le mot de passe doit avoir au minimum 8 caractères');
    }

    if (!regexDigit.test(this.password)) {
      this.passwordRules.push('Il doit contenir au moins un chiffre');
    }

    if (!regexLowercase.test(this.password)) {
      this.passwordRules.push('Il doit contenir au moins une lettre minuscule');
    }

    if (!regexUppercase.test(this.password)) {
      this.passwordRules.push('Il doit contenir au moins une lettre majuscule');
    }

    if (!regexSpecialChar.test(this.password)) {
      this.passwordRules.push('Il doit contenir au moins un caractère spécial parmi : !@#$%^&*()-+');
    }

    this.passwordValid = this.passwordRules.length === 0;
  }
}
