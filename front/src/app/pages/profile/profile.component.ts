import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, map, of, take } from 'rxjs';
import { Subject } from 'src/app/core/interfaces/subject.interface';
import { User } from 'src/app/core/interfaces/user.interface';
import { AuthService } from 'src/app/core/services/auth.service';
import { SubjectService } from 'src/app/core/services/subject.service';
import { SubscriptionService } from 'src/app/core/services/subscription.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  userInfos: User = {
    username: '',
    name: '',
    id: 0,
    created_at: '',
    updated_at: ''
  }
  username: string = '';
  name: string = '';
  password: string = '';
  emailValid: boolean = false;
  nameValid: boolean = false;
  passwordValid: boolean = false;
  passwordRules: string[] = [];
  subjects$: Observable<Subject[]> | undefined;

  constructor(
    private authService: AuthService,
    private subjectService: SubjectService,
    private subscriptionService: SubscriptionService,
    private router: Router
  ) { }
  ngOnInit(): void {
    this.authService.getCurrentUser()
      .pipe(take(1))
      .subscribe(
        (userData) => {
          this.userInfos.username = userData.username;
          this.userInfos.name = userData.name;
          this.username = userData.username;
          this.name = userData.name;
        },
        (error) => {
          console.error('Erreur d\'authentification', error);
        }
      );

    this.subjectService.getSubjectsBySubscriptions()
      .pipe(take(1))
      .subscribe(
        (subjects) => {
          this.subjects$ = of(subjects);
        },
        (error) => {
          console.error('Erreur lors de la récupération des sujets par abonnements', error);
        }
      );
  }

  onSubmit(registerForm: NgForm): void {
    if (this.emailValid || this.nameValid || this.passwordValid) {

      const username = this.userInfos.username === this.username ? undefined : this.username
      const name = this.userInfos.name === this.name ? undefined : this.name

      const credentials = {
        username: username ? username : undefined,
        name: name ? name : undefined,
        password: this.password ? this.password : undefined
      };

      this.authService.updateUser(credentials)
        .pipe(take(1))
        .subscribe(
          () => {
            this.router.navigate(['/profile']);
            alert("Votre profil à été mis à jour !");
            this.emailValid = false;
            this.nameValid = false;
            this.passwordValid = false;
          },
          (error) => {
            alert("Essayer avec un autre email");
            console.error('Erreur d\'authentification', error);
          }
        );
    } else {
      alert("Vous devez modifier ou remplir au minimum un champ pour modifier les informations !");
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
      this.passwordRules.push('Le mot de passe doit contenir au moins un chiffre');
    }

    if (!regexLowercase.test(this.password)) {
      this.passwordRules.push('Le mot de passe doit contenir au moins une lettre minuscule');
    }

    if (!regexUppercase.test(this.password)) {
      this.passwordRules.push('Le mot de passe doit contenir au moins une lettre majuscule');
    }

    if (!regexSpecialChar.test(this.password)) {
      this.passwordRules.push('Le mot de passe doit contenir au moins un caractère spécial parmi : !@#$%^&*()-+');
    }

    this.passwordValid = this.passwordRules.length === 0;
  }

  onSubscribeClick(subject: Subject): void {
    this.subscriptionService.deleteSubscription(subject.id)
      .pipe(take(1))
      .subscribe(
        () => {
          subject.subscribed = false;
          this.subjects$ = this.subjects$?.pipe(
            map(subjects => subjects.filter(s => s.id !== subject.id))
          );
        },
        (error) => {
          console.error('Erreur lors du désabonnement', error);
        }
      );
  }

}
