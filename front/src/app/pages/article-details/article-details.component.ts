import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, map, take } from 'rxjs';
import { Article } from 'src/app/core/interfaces/article.interface';
import { ArticleService } from 'src/app/core/services/article.service';
import { CommentService } from '../../core/services/comment.service';
import { NgForm } from '@angular/forms';
import { Comment } from 'src/app/core/interfaces/comment.interface';

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.scss']
})
export class ArticleDetailsComponent implements OnInit {
  articleId?: string | null
  content?: string;
  article$: Observable<Article> | undefined;
  comments: Comment[] = [];

  constructor(private articleService: ArticleService, private commentService: CommentService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.articleId = this.route.snapshot.paramMap.get('id');

    if (this.articleId) {
      this.article$ = this.articleService.getArticleById(parseInt(this.articleId, 10)).pipe(
        take(1),
        map(article => {
          this.comments = article.comments;
          return article;
        })
      );
    }
  }

  onSubmit(commentForm: NgForm): void {
    if (this.articleId && this.content) {
      const newComment = {
        content: this.content,
        articleId: parseInt(this.articleId),
      };

      this.commentService.createComment(newComment)
      .pipe(take(1))
      .subscribe(
        (createdComment) => {
          this.comments.push(createdComment);
          this.content = '';
          commentForm.resetForm();
        }
      )

    }
  }
}
