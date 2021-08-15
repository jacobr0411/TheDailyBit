package edu.bsu.cs222;

public class Article {
    private String author, title, description, url, urlToImage, publishedDate, content;

    Article(String author, String title, String description,
            String url, String urlToImage, String publishedDate, String content){
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedDate = publishedDate;
        this.content = content;
    }

    String getAuthor() {
        return author;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
