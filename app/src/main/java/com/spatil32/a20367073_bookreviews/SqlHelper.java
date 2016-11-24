package com.spatil32.a20367073_bookreviews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Shreyas on 11/5/2016.
 */
public class SqlHelper extends SQLiteOpenHelper
{
    //Database version
    public static final int DATABASE_VERSION = 4;
    //Database Name
    public static final String DATABASE_NAME = "BookDB";
    //Books Table Name
    public static final String TABLE_BOOKS = "BOOKS";

    //Books Table Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_RATING = "rating";

    public SqlHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE BOOKS ( "+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, author TEXT, rating REAL)";

        //Create Table BOOK
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older BOOKS table if exists
        db.execSQL("DROP TABLE IF EXISTS BOOKS");
        //Create fresh books table
        this.onCreate(db);
    }

    /* CRUD Opearations (Create "Add", READ "GET", UPDATE, DELETE) */
    public void addBook(Book newBook)
    {
        Log.d("Add Book", newBook.toString());
        //1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, newBook.getTitle());
        values.put(KEY_AUTHOR, newBook.getAuthor());
        values.put(KEY_RATING, newBook.getRating());
        //3. INSERT data
        db.insert(TABLE_BOOKS, //table
                null, //nullColumnHack
                values); //key/value = column names/values

        //4. Close Database
        db.close();
    }

    //Get All Books
    public List<Book> getAllBooks()
    {
        List<Book> books = new LinkedList<Book>();
        //1. Build the query
        String query = "SELECT * FROM " + TABLE_BOOKS;

        //2. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //3. Go over each row, build book and add it to the list
        Book book = null;
        if(cursor.moveToFirst())
        {
            do{
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setRating(cursor.getFloat(3));
                // Add book to books list
                books.add(book);
            }while (cursor.moveToNext());
        }
        Log.d("getAllBooks()", books.toString());
        return books; //return books list
    }

    //Updating Single Book
    public int updateBook(Book book)
    {
        //1. Get reference to writable book
        SQLiteDatabase db = this.getWritableDatabase();
        //2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle()); //getTitle
        values.put("author", book.getAuthor()); //getAuthor
        values.put("rating", book.getRating()); //getRating
        //3. Updating row
        int i = db.update(TABLE_BOOKS, //table
                values, //column/value
                KEY_ID+" = ?", //selections
                new String[] {String.valueOf(book.getId())}); //selection args

        //4. Close databse
        db.close();
        Log.d("UpdateBook", book.toString());
        return i;
    }

    //Delete single book
    public void deleteBook(Book deleteBook) {
    //1. Get reference to writable DB
    SQLiteDatabase db = this.getWritableDatabase();
    //2. Delete
    db.delete(TABLE_BOOKS, KEY_ID + " =?", new String[]{String.valueOf(deleteBook.getId())});

    //3. Close
    db.close();
    Log.d("deleteBook", deleteBook.toString());
    }
}
