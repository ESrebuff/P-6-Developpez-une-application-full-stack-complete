import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable, Subscription, of, take } from 'rxjs';
import { Subject } from 'src/app/core/interfaces/subject.interface';
import { ArticleService } from 'src/app/core/services/article.service';
import { SubjectService } from 'src/app/core/services/subject.service';

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {
  private subs: Subscription[] = [];
  subjets$: Observable<Subject[]> | undefined;
  title: string = '';
  content: string = '';
  selectedSubjectId: number | undefined;

  constructor(
    private subjectService: SubjectService,
    private articleService: ArticleService,
  ) { }

  ngOnInit(): void {
    this.subjectService.getAllSubjects()
      .pipe(take(1))
      .subscribe(
        (subjects) => {
          this.subjets$ = of(subjects.subjects);
        },
        (error) => {
          console.error('Erreur lors de la récupération des themes', error);
        }
      );
  }

  onSubmit(articleForm: NgForm): void {
    if (this.selectedSubjectId && this.content) {
      const newArticle = {
        title: this.title,
        content: this.content,
        subjectId: this.selectedSubjectId,
      };
      this.articleService.createArticle(newArticle)
        .pipe(take(1))
        .subscribe(
          (createdArticle) => {
            alert('Article créé avec succès');
            this.title = '';
            this.content = '';
            this.selectedSubjectId = undefined;
            articleForm.resetForm();
          },
          (error) => {
            console.error('Erreur lors de la création de l\'article', error);
          }
        );
    }
  }

}
