package core;

import javafx.scene.image.Image;

import java.net.URL;

public class Book {
    private String _title, _author, _ISBN;
    private URL _path;
    //private Image _cover;
    private int _rating;

    public URL _path(){
        return _path;
    }

    public void set_path(URL _path){
        this._path = _path;
    }

    public String get_title(){
        return _title;
    }

    public void set_title(String _title){
        this._title = _title;
    }

    public String get_author(){
        return _author;
    }

    public void set_author(String _author){
        this._author = _author;
    }

    public String get_ISBN(){
        return _ISBN;
    }

    public void set_ISBN(String _ISBN){
        this._ISBN = _ISBN;
    }

    public int get_rating(){
        return _rating;
    }

    public void set_rating(int _rating){
        this._rating = _rating;
    }
    public Book(){
        super();
    }

    public Book(URL _path, String _title, String _author, String _ISBN, int _rating){
        super();
        set_path(_path);
        set_title(_title);
        set_author(_author);
        set_ISBN(_ISBN);
        set_rating(_rating);
    }

    /*public Book bookFromDatabase() {
        return new Book();
    }*/
}
