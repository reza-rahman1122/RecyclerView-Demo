package com.example.startech.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements Filterable {

    Context context;
    List<Contact> contactList;
    List<Contact> contactList_all;
    RecyclerInterface recyclerInterface;

    public ContactAdapter(Context context, List<Contact> contactList,RecyclerInterface recyclerInterface) {
        this.context = context;
        this.contactList = contactList;
        this.contactList_all=new ArrayList<>(contactList);
        this.recyclerInterface=recyclerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Contact contact=contactList.get(position);
        holder.contact_name.setText(contact.getName());
        holder.contact_num.setText(contact.getNumber());
        holder.contact_image.setImageResource(contact.getImage());
        holder.dial_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"click btn "+position,Toast.LENGTH_SHORT).show();


                String value="tel:"+contactList.get(position).getNumber();
               context.startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse(value)));


            }

        });


    }



    @Override
    public int getItemCount() {

        return contactList.size();
    }

    @Override
    public Filter getFilter() {
        return search_filter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name,contact_num;
        ImageView contact_image;
        ImageButton dial_button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_name=itemView.findViewById(R.id.id_contact_name);
            contact_num=itemView.findViewById(R.id.id_contact_no);
            contact_image=itemView.findViewById(R.id.id_contact_img);
            dial_button=itemView.findViewById(R.id.id_btn_dial);

           itemView.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {

            recyclerInterface.onItemClick(getAdapterPosition());
    }
});
        }
    }


    Filter search_filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Contact> search_list=new ArrayList<>();
            if (charSequence.toString().isEmpty())

            {
                search_list.addAll(contactList_all);

            }

            else {
                for (Contact item:contactList_all)
                {
                    if (item.getName().toLowerCase().contains(charSequence.toString().toLowerCase().trim()))
                    {
                        search_list.add(item);

                    }
                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=search_list;
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            contactList.clear();
            contactList.addAll((Collection<? extends Contact>) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
