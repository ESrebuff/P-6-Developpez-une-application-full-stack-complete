import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  onSubmit(loginForm: NgForm): void {
    const credentials = {
      username: this.username,
      password: this.password
    };
    console.log(credentials)

    this.authService.login(credentials).subscribe(
      (response) => {
        // Gérer la réponse réussie (par exemple, rediriger l'utilisateur)
        console.log('Authentification réussie', response);
      },
      (error) => {
        // Gérer l'erreur d'authentification (par exemple, afficher un message d'erreur)
        console.error('Erreur d\'authentification', error);
      }
    );
  }
  
  ngOnInit(): void {
  }

}
