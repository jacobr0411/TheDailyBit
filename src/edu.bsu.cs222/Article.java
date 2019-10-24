package edu.bsu.cs222;

public class Article {
    private String author, title, description, url, urlToImage, publishedDate;

    Article(String author, String title, String description,
            String url, String urlToImage, String publishedDate){
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedDate = publishedDate;
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

    String getUrlToImage() {
        return urlToImage;
    }

    String getPublishedDate() {
        return publishedDate;
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
                '}';
    }
}
