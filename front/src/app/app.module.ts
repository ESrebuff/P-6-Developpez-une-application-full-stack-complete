import { NgModule, LOCALE_ID } from '@angular/core';
import * as fr from '@angular/common/locales/fr';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
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
import { HeaderPublicComponent } from './components/header-public/header-public.component';
import { HeaderPrivateComponent } from './components/header-private/header-private.component';
import { ArticleComponent } from './components/article/article.component';
import { ThemeComponent } from './components/theme/theme.component';
import { registerLocaleData } from '@angular/common';
import { httpInterceptorProviders } from './core/interceptors';

@NgModule({
  declarations: [AppComponent, HomeComponent, ArticlesListComponent, ArticleDetailsComponent, ThemesListComponent, ProfileComponent, CreateArticleComponent, LoginComponent, RegisterComponent, HeaderPublicComponent, HeaderPrivateComponent, ArticleComponent, ThemeComponent],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    FormsModule,
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'fr-FR' },
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor() {
    registerLocaleData(fr.default);
  }
}
