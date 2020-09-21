package com.example.startech.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements RecyclerInterface {

    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    ArrayList<Contact> contact_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.id_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contact_list=new ArrayList<>();
        contact_list.add(new Contact("Reza","01772711529",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Akul","01521423567",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Shakil","01799890244",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Sagor","01517942050",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Jamil","01731785215",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Nur","01760523833",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Arman","01760523833",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Tanvir","01760523833",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Opu","01765671813",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Shakib","01746544548",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Yousuf","01734260555",R.drawable.ic_launcher_background));
        contact_list.add(new Contact("Pranto","01743176468",R.drawable.ic_launcher_background));


        contactAdapter=new ContactAdapter(this,contact_list,this);
        recyclerView.setAdapter(contactAdapter);

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            Collections.swap(contact_list,viewHolder.getAdapterPosition(),target.getAdapterPosition());
            recyclerView.getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());






            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };



    @Override
    public void onItemClick(int position) {
        //contact_list.remove(position);
        //contactAdapter.notifyItemRemoved(position);
        Toast.makeText(this,"clicked position:"+position ,Toast.LENGTH_SHORT).show();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.item_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.id_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.getFilter().filter(newText);

                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
}