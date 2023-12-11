import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/core/interfaces/subject.interface';
import { SubjectService } from '../../core/services/subject.service';
import { Observable, take } from 'rxjs';

@Component({
  selector: 'app-themes-list',
  templateUrl: './themes-list.component.html',
  styleUrls: ['./themes-list.component.scss']
})
export class ThemesListComponent implements OnInit {
  subjects$: Observable<Subject[]> | undefined;

  constructor(private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.subjects$ = this.subjectService.getAllSubjectsWithSubscriptionStatus().pipe(take(1));
  }

}
