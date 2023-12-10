import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Article } from 'src/app/core/interfaces/article.interface';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.scss']
})
export class ArticleDetailsComponent implements OnInit {
  commentText: string = '';
  @Output() commentSubmitted = new EventEmitter<string>();
  article$: Observable<Article> | undefined;

  constructor(private articleService: ArticleService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const articleId = this.route.snapshot.paramMap.get('id');
    
    if (articleId) {
      this.article$ = this.articleService.getArticleById(parseInt(articleId, 10));
    }
  }

  onSubmit(): void {
    if (this.commentText.trim() !== '') {
      this.commentSubmitted.emit(this.commentText);
      this.commentText = '';
    }
  }
}
