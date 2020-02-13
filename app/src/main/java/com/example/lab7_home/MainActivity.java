package com.example.lab7_home;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference firebaseReference;
    private EditText editText;
    private Spinner spinnerGroup;
    private Button btnAdd;
    private ListView listView;
    private List<MyData> users;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseReference = FirebaseDatabase.getInstance()
                .getReference();
        initInstances();
        showData();
    }

    private void showData() {
        Query query = firebaseReference.child("users");
        //Query query = firebaseReference.child("");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                users.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    MyData dao = postSnapshot.getValue(MyData.class);
                    users.add(dao);
                }
                adapter = new MyAdapter(users);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDialog(final MyData dao )
    {
        AlertDialog.Builder dialogBuilder =
                new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        dialogBuilder.setTitle(dao.getName());
        editText.setText(dao.getName());
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void initInstances()
    {
        editText = (EditText) findViewById(R.id.edtName);
        spinnerGroup = (Spinner) findViewById(R.id.spinGroup);
        btnAdd = (Button) findViewById(R.id.btnAddData);
        btnAdd.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemLongClickListener
                (new AdapterView.OnItemLongClickListener()
                {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MyData dao = users.get(i);
                        showDialog(dao);
                        return false;
                    }
                });
        users = new ArrayList<>();
    }
    
    @Override
    public void onClick(View view){
        if(view == btnAdd)  addUserData();
    }

    private void addUserData() {
        String name = editText.getText().toString();
        String group = spinnerGroup.getSelectedItem().toString(); // spin

        //checking if the value is provided
        if(!TextUtils.isEmpty(name))
        {
            String id = firebaseReference.child("user")
                    .push().getKey();
            //String id = firebaseReference.child("").push().getKey();

            MyData user = new MyData();
            user.setId(id);
            user.setName(name);
            user.setGroup(group);

            firebaseReference.child("users").child(id).setValue(user);
            //firebaseReference.child("").child(id).setValue(user);
            Toast.makeText(this, "User data added",Toast.LENGTH_LONG).show();
            editText.setText("");
        }
        else
        {
            Toast.makeText(this, "Please enter a user data", Toast.LENGTH_LONG).show();
        }
    }

    /*
    @Override
    public void onClick(View v) {

    }*/

    /*
    EditText editText_Topic, editText_Msg;
    Button button_Mybutton;
    RecyclerView myRecyclerView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseRecyclerOptions<MsgClass2> options;
    FirebaseRecyclerAdapter<MsgClass2, RecyclerViewHolderTest> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_Msg = (EditText) findViewById(R.id.editTextMsg);
        editText_Topic = (EditText) findViewById(R.id.editTextTopic);
        button_Mybutton = (Button) findViewById(R.id.idButton);
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        button_Mybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                addMessage();
            }
        });
        displayComment();

    }

    private void displayComment() {
        options = new FirebaseRecyclerOptions.Builder<MsgClass2>()
                .setQuery(databaseReference, MsgClass2.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<MsgClass2, RecyclerViewHolderTest>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerViewHolderTest holder, int position,
                                            @NonNull MsgClass2 model) {
                holder.textViewTitle.setText(model.getTitle());
                holder.textViewComment.setText(model.getMyMessage());

            }

            @NonNull
            @Override
            public RecyclerViewHolderTest onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
            {
                View itemView = LayoutInflater.from(getBaseContext())
                        .inflate(R.layout.item_msg,viewGroup,false);
                return new RecyclerViewHolderTest(itemView);
            }
        };
        adapter.startListening();
        myRecyclerView.setAdapter(adapter);
    }

    private void addMessage() {
        String title = editText_Topic.getText().toString();
        String message = editText_Msg.getText().toString();

        MsgClass2 msgObj = new MsgClass2(title, message);
        databaseReference.push().setValue(msgObj);
        adapter.notifyDataSetChanged();
    }
    */

}
