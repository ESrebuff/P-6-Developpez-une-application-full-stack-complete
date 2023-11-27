import { NgModule } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { ArticlesListComponent } from './pages/articles-list/articles-list.component';
import { ArticleDetailsComponent } from './pages/article-details/article-details.component';
import { ThemesListComponent } from './pages/themes-list/themes-list.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CreateArticleComponent } from './pages/create-article/create-article.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HeaderRegisterComponent } from './components/header-register/header-register.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, ArticlesListComponent, ArticleDetailsComponent, ThemesListComponent, ProfileComponent, CreateArticleComponent, LoginComponent, RegisterComponent, HeaderRegisterComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
