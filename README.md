# News
This is simple RESTful Api for news articles.

# Entity
The application works with entity called Article
Articles have the following attributes
* id - automatically generated by the system.
* title - String, Required,minimum 10 and maximum 255 charachters
* shortDescription - String, maximum 512 characters
* text - String, Required, minimum 128 characters
* date - Date on which the article was created. Automatically generated by the system.

# Instructions
Currently the application is configured to run at localhost with port 8080.

## GET
` ~/api/articles ` returns a page of ten articles.
The articles can be sorted and filtered by title and date using a query string.
`~/api/articles?title=foo&date=2010/10/10` Returns first ten  articles from the specified date, containing part of the specified title string.
`~/api/articles?page=2&sort=title` Returns the second page of ten articles ordered by title ascending.
`~/api/articles/id` Returns a single article record, where `id` should match the id of existing article.

The date argument in the query string should be in the following format `YYYY/MM/DD` !

## POST
`~/api/articles` creates a new article. The body of the request should have the following structure :
`
  {   
    "title" : "foo",  
    "shortDescription": "bar",  
    "text": "xyz"  
  }
`

## PUT
`~/api/articles/id` Where `id` should match the id of existing article. Edits an existing article. The body of the request should have the following structure :
`
  {
    "title" : "foo",
    "shortDescription": "bar",
    "text": "xyz"
  }
`
## DELETE 

`~/api/articles/id`Where `id` should match the id of existing article. Deletes an existing article.


# Used tools
* Java 13
* Spring boot
* PostgreSQL
