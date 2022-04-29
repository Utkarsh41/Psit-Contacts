package com.psit.psitcontacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class personAdapter extends FirebaseRecyclerAdapter<person,personAdapter.personsViewholder> {

    public personAdapter(@NonNull FirebaseRecyclerOptions<person> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull person model) {
        holder.fullname.setText(model.getFullname());
        holder.branch.setText(model.getBranch());
        holder.id.setText(model.getId());
        holder.extension.setText(model.getExtension());
        holder.cabin.setText(model.getCabin());
        holder.contact.setText(model.getContact());
        holder.designation.setText(model.getDesignation());
        holder.email.setText(model.getFemail());
    }

    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person,parent,false);
        return new personAdapter.personsViewholder(view);

    }

    class personsViewholder extends RecyclerView.ViewHolder {
        TextView fullname, branch, id,contact,designation,cabin,extension,email;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);
            email=itemView.findViewById(R.id.femail);
            contact=itemView.findViewById(R.id.Contact);
            designation=itemView.findViewById(R.id.Designation);
            cabin=itemView.findViewById(R.id.cabin);
            extension=itemView.findViewById(R.id.Extension);
            fullname= itemView.findViewById(R.id.fullname);
            branch = itemView.findViewById(R.id.branch);
            id = itemView.findViewById(R.id.id);
        }
    }
}






