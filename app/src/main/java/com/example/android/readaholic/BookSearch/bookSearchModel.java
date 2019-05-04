package com.example.android.readaholic.BookSearch;

public class bookSearchModel {
    private String title;
    private String image ;
    private String author;
    private String publicationDate ;
    private String lastUpdate ;
    private int pagesNum ;
    private int bookId ;
    private int ratingCoung ;
    private double ratingAvg ;

    public bookSearchModel(String title, String image, String author, String publicationDate, String lastUpdate, int pagesNum, int bookId, int ratingCoung, double ratingAvg) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.publicationDate = publicationDate;
        this.lastUpdate = lastUpdate;
        this.pagesNum = pagesNum;
        this.bookId = bookId;
        this.ratingCoung = ratingCoung;
        this.ratingAvg = ratingAvg;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public int getPagesNum() {
        return pagesNum;
    }

    public int getBookId() {
        return bookId;
    }

    public int getRatingCoung() {
        return ratingCoung;
    }

    public double getRatingAvg() {
        return ratingAvg;
    }
}
