package com.example.talkyourself.MedicalRecord;

public class PatientModel {
    String patientName;
    String patientEmail;
    String patientAge;
    String patientBloodGroup;
    String patientAddress;
    String patientPhone;
    String patientPrevApp;
    String patientNextApp;

    public PatientModel() {
    }

    public PatientModel(String patientName, String patientEmail, String patientAge, String patientBloodGroup, String patientAddress, String patientPhone,
                        String patientPrevApp, String patientNextApp) {
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.patientAge = patientAge;
        this.patientBloodGroup = patientBloodGroup;
        this.patientAddress = patientAddress;
        this.patientPhone = patientPhone;
        this.patientPrevApp = patientPrevApp;
        this.patientNextApp = patientNextApp;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientBloodGroup() {
        return patientBloodGroup;
    }

    public void setPatientBloodGroup(String patientBloodGroup) {
        this.patientBloodGroup = patientBloodGroup;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }


    public String getPatientPrevApp() {
        return patientPrevApp;
    }

    public void setPatientPrevApp(String patientPrevApp) {
        this.patientPrevApp = patientPrevApp;
    }

    public String getPatientNextApp() {
        return patientNextApp;
    }

    public void setPatientNextApp(String patientNextApp) {
        this.patientNextApp = patientNextApp;
    }
}

