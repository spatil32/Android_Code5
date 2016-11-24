package com.spatil32.a20367073_bookreviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //UI elements and adapters
    ListView listView;
    ListAdapter listAdapter;
    Spinner spinner;
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayList<String> allBookNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.bookinfo);
        spinner = (Spinner)findViewById(R.id.detailSpinner);
        Button detailsButton = (Button)findViewById(R.id.detailsButton);
        final Button spinnerButton = (Button)findViewById(R.id.spinner);

        SqlHelper db = new SqlHelper(this);
        /**CRUD Operations**/
        //add books
        db.addBook(new Book("To Kill A MockingBird","Harper Lee", 3.5f));
        db.addBook(new Book("The Great Gatsby","Scott Fitzgerald", 2.0f));
        db.addBook(new Book("The Catcher in Rye","J.D.Salinger", 4.5f));
        db.addBook(new Book("The Scarlet Letter","Nathaniel Hawthrone", 3.0f));
        db.addBook(new Book("Fahrenheit 451","Ray Bradburry", 3.5f));
        db.addBook(new Book("In Cold Blood","Truman Capote", 4.0f));
        db.addBook(new Book("East of Eden","John Steinbeck", 2.0f));
        db.addBook(new Book("Gone With The Wind","M. Mitchell", 3.2f));
        db.addBook(new Book("The Color Purple","Alice Walker", 2.5f));
        db.addBook(new Book("The Stand","Stephen King", 4.5f));

        //get all books
        List<Book> allBooks = db.getAllBooks();

        //Update a book
        int j = db.updateBook(allBooks.get(0));

        //Delete a book
        db.deleteBook(allBooks.get(0));

        //getAllBooks
        allBooks = db.getAllBooks();
        Log.d("Books Sizes is : ", String.valueOf(allBooks.size()));
        final String[] authors = new String[allBooks.size()];
        final String[] books = new String[allBooks.size()];
        final Float[] ratings = new Float[allBooks.size()];

        for(int i=0; i<allBooks.size(); i++)
        {
            allBookNames.add(i, allBooks.get(i).getTitle());
            authors[i] = allBooks.get(i).getAuthor();
            books[i] = allBooks.get(i).getTitle();
            ratings[i] = allBooks.get(i).getRating();
        }

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setAdapter(null);
                listAdapter = new CustomListAdapter(MainActivity.this, books, authors, ratings);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), books[position], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        spinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(null);
                spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_info, R.id.book_name, allBookNames);
                spinner.setAdapter(spinnerArrayAdapter);

                //onItemSelected event for spinner contents
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), "Name = " + books[position] + " Author = " + authors[position] + ", Rating = " + ratings[position], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(getApplicationContext(), "Select Book Item", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}