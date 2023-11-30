import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.scss']
})
export class ArticleDetailsComponent implements OnInit {

  article = {
    id: 1,
    title: 'title test',
    content: 'content test',
    author: {
      id: 1,
      username: 'test222@mail.com',
      name: 'Estebanresr',
      created_at: '2023-11-21T23:52:20.488364',
      updated_at: '2023-11-24T00:00:40.942172'
    },
    subject: {
      id: 1,
      name: 'Science',
      created_at: '2023-11-22T00:52:01',
      updated_at: '2023-11-22T00:52:01'
    },
    createdAt: '2023-11-21T23:54:34.921577',
    updatedAt: null,
    comments: [
      {
        id: 1,
        content: 'content test',
        author: {
          id: 1,
          username: 'test222@mail.com',
          name: 'Estebanresr',
          created_at: '2023-11-21T23:52:20.488364',
          updated_at: '2023-11-24T00:00:40.942172'
        },
        createdAt: '2023-11-23T00:43:45.894644',
        updatedAt: null
      },
      {
        id: 2,
        content: 'test 2 test',
        author: {
          id: 1,
          username: 'test222@mail.com',
          name: 'Estebanresr',
          created_at: '2023-11-21T23:52:20.488364',
          updated_at: '2023-11-24T00:00:40.942172'
        },
        createdAt: '2023-11-23T00:44:21.017977',
        updatedAt: null
      }
    ]
  }

  commentText: string = '';
  @Output() commentSubmitted = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }


  onSubmit(): void {
    if (this.commentText.trim() !== '') {
      this.commentSubmitted.emit(this.commentText);
      this.commentText = '';
    }
  }
}