package com.example.topcelebritieslistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myDataBase;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataBase = new DatabaseHelper(this);
        myDataBase.open();

        listView = (ListView) findViewById(R.id.myListView);
        listView.setEmptyView(findViewById(R.id.tvEmpty));

        ArrayList<Celebrity> arrayOfCelebrity = new ArrayList<Celebrity>();
        /*
        arrayOfCelebrity.add(new Celebrity ("Leonardo", "DiCaprio", R.drawable.leonardo_di_caprio));
        arrayOfCelebrity.add(new Celebrity ("Leonardo 1", "DiCaprio", R.drawable.leonardo_di_caprio));
        arrayOfCelebrity.add(new Celebrity ("Leonardo 2", "DiCaprio", R.drawable.leonardo_di_caprio));
        arrayOfCelebrity.add(new Celebrity ("Leonardo 3", "DiCaprio", R.drawable.leonardo_di_caprio));
*/
        CelebrityAdapter adapter = new CelebrityAdapter(this, R.layout.custom_list_celebrities,arrayOfCelebrity);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Item selected :"+position,Toast.LENGTH_SHORT).show();

            }
        });

        chargeData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.celebrities_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_celebrity: {
                Intent intent = new Intent(this, CelebrityDetailsActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.search :{
                Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void chargeData(){
        final String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.FIRST_NAME,DatabaseHelper.LAST_NAME, DatabaseHelper.DATE};
        final int [] to = new int [] {R.id.etfname, R.id.etlname, R.id.etDate};
        Cursor c = myDataBase.getAllCelebrities();

        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,R.layout.custom_list_celebrities,c,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }
}