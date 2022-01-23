package com.example.talkyourself.MedicalRecord;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.talkyourself.R;
import java.util.ArrayList;


public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.MyViewHolder> {

    Context context;
    ArrayList<PrescriptionModel> model;

    public PrescriptionAdapter(Context c, ArrayList<PrescriptionModel> m) {
        context = c;
        model = m;
    }

    @NonNull
    @Override
    public PrescriptionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptionAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.prescrption_view, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final PrescriptionAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int pos) {

        holder.name.setText("☉ "+ model.get(pos).getDoctorName());
        holder.date.setText(model.get(pos).getDatePres());



        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PrescriptionView.class);
                i.putExtra("File",  model.get(pos).getFile());
                view.getContext().startActivity(i);

            }
        });


    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView name, date, click;
        public MyViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            click = itemView.findViewById(R.id.click);


        }

    }
}