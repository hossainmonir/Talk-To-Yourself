package com.example.talkyourself.MedicinLocator;

public class MedicinModel {
    String Name;
    String Company;
    String Weight;
    String Expiredate;
    String StoreName;

    public MedicinModel() {
    }

    public MedicinModel(String name, String company, String weight, String expiredate, String storeName) {
        Name = name;
        Company = company;
        Weight = weight;
        Expiredate = expiredate;
        StoreName = storeName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getExpiredate() {
        return Expiredate;
    }

    public void setExpiredate(String expiredate) {
        Expiredate = expiredate;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }
}
