import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ArticlesListComponent } from './pages/articles-list/articles-list.component';
import { ArticleDetailsComponent } from './pages/article-details/article-details.component';
import { ThemesListComponent } from './pages/themes-list/themes-list.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CreateArticleComponent } from './pages/create-article/create-article.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';


// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'articles', component: ArticlesListComponent },
  { path: 'article/:id', component: ArticleDetailsComponent },
  { path: 'themes', component: ThemesListComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'create-article', component: CreateArticleComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
