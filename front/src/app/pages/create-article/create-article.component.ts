import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {

  themes = [
    { name: '1 test', id: 1 }, 
    { name: '2 test', id: 2 }, 
    { name: '3 test', id: 3 }, 
    { name: '4 test', id: 4 }, 
    { name: '5 test', id: 5 }, 
    { name: '6 test', id: 6 }
  ]
  commentText: string = '';
  @Output() commentSubmitted = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
    const test = this.themes.unshift({name: 'Selectionner un thème', id: 0})
  }

  onSubmit(): void {

  }

}
