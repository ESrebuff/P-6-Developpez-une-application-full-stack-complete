import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-articles-list',
  templateUrl: './articles-list.component.html',
  styleUrls: ['./articles-list.component.scss']
})
export class ArticlesListComponent implements OnInit {
  articles = [
    {
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
    },
    {
      id: 2,
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
      createdAt: '2023-11-21T23:56:59.65056',
      updatedAt: null,
      comments: []
    },
    {
      id: 3,
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
        id: 5,
        name: 'Forme Physique',
        created_at: '2023-11-22T00:52:01',
        updated_at: '2023-11-22T00:52:01'
      },
      createdAt: '2023-11-21T23:57:45.463026',
      updatedAt: null,
      comments: []
    },
    {
      id: 4,
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
        id: 5,
        name: 'Forme Physique',
        created_at: '2023-11-22T00:52:01',
        updated_at: '2023-11-22T00:52:01'
      },
      createdAt: '2023-11-23T00:41:55.31243',
      updatedAt: null,
      comments: []
    },
    {
      id: 5,
      title: 'title test',
      content: 'content test',
      author: {
        id: 4,
        username: 'tesefst1@mail.com',
        name: 'Estebanresr',
        created_at: '2023-11-29T21:21:13.547405',
        updated_at: null
      },
      subject: {
        id: 5,
        name: 'Forme Physique',
        created_at: '2023-11-22T00:52:01',
        updated_at: '2023-11-22T00:52:01'
      },
      createdAt: '2023-11-29T21:24:56.78679',
      updatedAt: null,
      comments: []
    }
  ];
  sortedArticles: any = [];

  constructor() { }

  ngOnInit(): void {
    this.sortedArticles = [...this.articles];
  }

  sortArticlesByChronology() {
    this.sortedArticles = [...this.articles].sort((a, b) => {
      return new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime();
    });
  }


}
