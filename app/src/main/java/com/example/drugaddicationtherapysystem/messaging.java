package com.example.drugaddicationtherapysystem;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class messaging extends AppCompatActivity {
    Context thisContext;
    RecyclerView recyclerView_suggestion = null;
    EditText suggestion_message;
    ImageView Upload_suggestion_Button;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    DatabaseReference databaseReference9;
    FirebaseUser user;
    String sendername;
    String senderImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create__room_);
        thisContext = getApplicationContext();
        //View rootview = inflater.inflate(R.layout.fragment_create__room_, container, false);
        recyclerView_suggestion = findViewById(R.id.recyclerview_suggestions);
        Upload_suggestion_Button = findViewById(R.id.btn_upload_suggestion_id);
        suggestion_message = findViewById(R.id.suggestion_message_id);
        FirebaseRecyclerOptions<Suggestions> options;
        FirebaseRecyclerAdapter<Suggestions, SuggestionsViewHolder> adapter;
        recyclerView_suggestion.setLayoutManager(new LinearLayoutManager(thisContext));
        recyclerView_suggestion.setHasFixedSize(true);
        databaseReference = FirebaseDatabase.getInstance()
                .getReference().child("DRUGADDCTIONS").child("MESSAGES");
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<Suggestions>()
                .setQuery(databaseReference,Suggestions.class).build();
        adapter = new FirebaseRecyclerAdapter<Suggestions, SuggestionsViewHolder>(options) {
            @Override
            protected void onBindViewHolder
                    (@NonNull final SuggestionsViewHolder holder, int position, @NonNull Suggestions model)
            {
                holder.sender_name.setText(model.getSender());

                holder.suggestion_message_d.setText(model.getSuggestion());
                holder.suggestion_message_d.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        toasMessageLong("delete me bro");
                        return false;
                    }
                });
                if (model.getSenderImage()==null){
                    //Picasso.get().load(R.drawable.profile_icon).into(holder.sender_profile_image);

                }else {
                    //Picasso.get().load(model.getSenderImage()).into(holder.sender_profile_image);
                }












            }

            @NonNull
            @Override
            public SuggestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.suggestions_view,parent,false);
                return new SuggestionsViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView_suggestion.setAdapter(adapter);

        databaseReference1 = FirebaseDatabase.getInstance()
                .getReference().child("DRUGADDCTIONS").child("career");

        databaseReference1.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    databaseReference1.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            sendername = new String(snapshot.child("Email").getValue().toString());
                            //senderImageUrl = snapshot.child("mImageUrl").getValue().toString();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    sendername = user.getEmail();
                    // senderImageUrl = null;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toasMessageShort(error.getMessage());

            }
        });








        Upload_suggestion_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(thisContext,sendername+senderImageUrl,Toast.LENGTH_LONG).show();
                suggestion_message.setText("");
                addSuggestion(sendername,suggestion_message.getText().toString(),"senderImageUrl");
            }
        });








    }
    public void addSuggestion(String sender, String suggestion,String senderImage){
        Suggestions message = new Suggestions(sender, suggestion,senderImage);
        databaseReference.child(databaseReference.push().getKey()).setValue(message)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                           add("https://firebasestorage.googleapis.com/v0/b/drugaddictiontherapysystem.appspot.com/o/Cooking.pdf?alt=media&token=d9f879f1-047c-430d-b1ee-7f1da7c35bca");

                        //databaseReference9 = FirebaseDatabase.getInstance()
                               // .getReference().child("DRUGADDCTIONS").child("Skills pdfs");
                      //  databaseReference9.child("url").setValue("");

                    }
                });
    }

    public void add(String x){
        addpdf adi = new addpdf(x);
        databaseReference9 = FirebaseDatabase.getInstance()
        .getReference().child("DRUGADDCTIONS").child("Skills pdfs");
        databaseReference9.setValue(adi);
    }
    public void toasMessageShort(String toastText){
        Toast.makeText(thisContext,toastText,
                Toast.LENGTH_SHORT).show();
    }

    public void toasMessageLong(String toastText){
        Toast.makeText(thisContext,toastText,
                Toast.LENGTH_LONG).show();
    }


}