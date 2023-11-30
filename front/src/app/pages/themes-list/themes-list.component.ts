import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/core/interfaces/subject.interface';
import { SubjectService } from '../../core/services/subject.service';
import { Observable, map } from 'rxjs';

@Component({
  selector: 'app-themes-list',
  templateUrl: './themes-list.component.html',
  styleUrls: ['./themes-list.component.scss']
})
export class ThemesListComponent implements OnInit {
  subjects$: Observable<Subject[]> | undefined;

  themes = [
    { title: 'Theme 1', author: 'author'  },
    { title: 'Theme 2', author: 'author'  },
    { title: 'Theme 3', author: 'author'  },
    { title: 'Theme 4', author: 'author'  },
    { title: 'Theme 5', author: 'author'  },
    { title: 'Theme 6', author: 'author'  },
    { title: 'Theme 7', author: 'author'  },
    { title: 'Theme 8', author: 'author'  },
    { title: 'Theme 9', author: 'author'  }
  ];


  constructor(private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.subjects$ = this.subjectService.getAllSubjects().pipe(map(response => response.subjects));
    this.subjects$.subscribe(data => console.log(data));
  }

}
