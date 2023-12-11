import { Component, OnInit } from '@angular/core';
import { Observable, map, of, take } from 'rxjs';
import { Article } from 'src/app/core/interfaces/article.interface';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-articles-list',
  templateUrl: './articles-list.component.html',
  styleUrls: ['./articles-list.component.scss']
})
export class ArticlesListComponent implements OnInit {
  articles$: Observable<Article[]> | undefined;

  constructor(private articleService: ArticleService) { }

  ngOnInit(): void {
    this.articleService.getAllArticles()
      .pipe(take(1))
      .subscribe(
        (articles) => {
          this.articles$ = of(articles);
        },
        (error) => {
          console.error('Erreur lors de la récupération des articles', error);
        }
      );
  }

  sortArticlesByChronological() {
    if (this.articles$) {
      this.articles$ = this.articles$.pipe(
        map(articles => [...articles].sort((a, b) => new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()))
      );
    }
  }

  sortArticlesBydeChronological() {
    if (this.articles$) {
      this.articles$ = this.articles$.pipe(
        map(articles => [...articles].sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()))
      );
    }
  }

  sortArticlesByDefault() {
    this.articleService.getAllArticles()
      .pipe(take(1))
      .subscribe(
        (articles) => {
          this.articles$ = of(articles);
        },
        (error) => {
          console.error('Erreur lors de la récupération des articles', error);
        }
      );
  }
}
