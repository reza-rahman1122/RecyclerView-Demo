package com.example.startech.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    Context context;
    List<Contact> contactList;
    RecyclerInterface recyclerInterface;

    public ContactAdapter(Context context, List<Contact> contactList,RecyclerInterface recyclerInterface) {
        this.context = context;
        this.contactList = contactList;
        this.recyclerInterface=recyclerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact=contactList.get(position);
        holder.contact_name.setText(contact.getName());
        holder.contact_num.setText(contact.getNumber());
        holder.contact_image.setImageResource(contact.getImage());


    }

    @Override
    public int getItemCount() {

        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name,contact_num;
        ImageView contact_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_name=itemView.findViewById(R.id.id_contact_name);
            contact_num=itemView.findViewById(R.id.id_contact_no);
            contact_image=itemView.findViewById(R.id.id_contact_img);

itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        recyclerInterface.onItemClick(getAdapterPosition());
    }
});
        }
    }
}
