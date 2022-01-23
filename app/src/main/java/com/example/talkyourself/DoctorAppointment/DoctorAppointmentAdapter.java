package com.example.talkyourself.DoctorAppointment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.talkyourself.R;
import java.util.ArrayList;



public class DoctorAppointmentAdapter extends RecyclerView.Adapter<DoctorAppointmentAdapter.MyViewHolder> {

    Context context;
    ArrayList <DoctorAppointmentModel> model;


    public DoctorAppointmentAdapter( Context c, ArrayList<DoctorAppointmentModel> m) {
        context = c;
        model = m;
    }

    public DoctorAppointmentAdapter(ArrayList<DoctorAppointmentModel> mylist) {

    }


    @NonNull
    @Override
    public DoctorAppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_appointment_list, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final DoctorAppointmentAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int pos) {

        holder.name.setText("☉ "+ model.get(pos).getDoctorName());
        holder.conselling.setText(model.get(pos).getDoctorQuali());
        holder.qualify.setText(model.get(pos).getDoctorConselling());


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DoctorDetails.class);
                i.putExtra("name",  model.get(pos).getDoctorName());
                view.getContext().startActivity(i);
                i.putExtra("qualify", model.get(pos).getDoctorQuali());
                view.getContext().startActivity(i);
                i.putExtra("conselling", model.get(pos).getDoctorConselling());
                view.getContext().startActivity(i);

            }
        });

    }


    @Override
    public int getItemCount() {
        return model == null ? 0 : model.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, conselling, qualify;
        public MyViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.name);
            conselling = itemView.findViewById(R.id.conselling);
            qualify = itemView.findViewById(R.id.qualifi);

        }

    }
}