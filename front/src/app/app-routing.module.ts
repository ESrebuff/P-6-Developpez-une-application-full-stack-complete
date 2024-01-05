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
import { PrivateGuard } from './core/guards/private.guards';
import { PublicGuard } from './core/guards/public.guards';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [PublicGuard] },
  { path: 'login', component: LoginComponent, canActivate: [PublicGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [PublicGuard] },
  { path: 'articles', component: ArticlesListComponent, canActivate: [PrivateGuard] },
  { path: 'article/:id', component: ArticleDetailsComponent, canActivate: [PrivateGuard] },
  { path: 'themes', component: ThemesListComponent, canActivate: [PrivateGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [PrivateGuard] },
  { path: 'create-article', component: CreateArticleComponent, canActivate: [PrivateGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
