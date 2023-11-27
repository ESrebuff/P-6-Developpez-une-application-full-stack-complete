import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticlesListComponent } from './articles-list/articles-list.component';
import { ArticleDetailsComponent } from './article-details/article-details.component';
import { ThemesListComponent } from './themes-list/themes-list.component';
import { CreateArticleComponent } from './create-article/create-article.component';
import { PrivateRoutingModule } from './private-routing.module';


@NgModule({
  declarations: [
    ArticleDetailsComponent,
    ArticlesListComponent,
    ThemesListComponent,
    CreateArticleComponent
  ],
  imports: [
    CommonModule,
    PrivateRoutingModule
  ]
})
export class PrivateModule { }
