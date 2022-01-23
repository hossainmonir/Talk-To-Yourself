package com.example.talkyourself.MedicalRecord;

public class PrescriptionModel {
    String doctorName;
    String File;
    String datePres;

    public PrescriptionModel() {
    }

    public PrescriptionModel(String doctorName, String file, String datePres) {
        this.doctorName = doctorName;
        File = file;
        this.datePres = datePres;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    public String getDatePres() {
        return datePres;
    }

    public void setDatePres(String datePres) {
        this.datePres = datePres;
    }
}
