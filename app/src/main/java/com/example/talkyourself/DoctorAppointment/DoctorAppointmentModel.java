package com.example.talkyourself.DoctorAppointment;

public class DoctorAppointmentModel {

    String doctorName;
    String doctorQuali;
    String doctorConselling;


    public DoctorAppointmentModel() {
    }

    public DoctorAppointmentModel(String doctorName, String doctorQuali, String doctorConselling) {
        this.doctorName = doctorName;
        this.doctorQuali = doctorQuali;
        this.doctorConselling = doctorConselling;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorQuali() {
        return doctorQuali;
    }

    public void setDoctorQuali(String doctorQuali) {
        this.doctorQuali = doctorQuali;
    }

    public String getDoctorConselling() {
        return doctorConselling;
    }

    public void setDoctorConselling(String doctorConselling) {
        this.doctorConselling = doctorConselling;
    }
}
