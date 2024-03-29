package com.school.server.model;

import com.school.server.Config.AesEncryption;

public abstract class Person {
    String id;
    String Address;
    String Phone;
    String Sex;
    String National_Id;
    String Name;
    String Password;
    public abstract String getId();

    public abstract void setId(String id);

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getNational_Id() {
        return National_Id;
    }

    public void setNational_Id(String national_Id) {
        National_Id = national_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "\"" + this.id + "\"," +
                "\"" + this.Address + "\"," +
                "\"" + this.Phone + "\"," +
                "\"" + this.Name + "\"," +
                "\"" + this.National_Id + "\"," +
                "\"" + this.Sex + "\"," +
                "\"" + this.Password + "\"";
    }
    public abstract String ToStringSpecific();
    public  String generatePassword(){
        AesEncryption aes = new AesEncryption();
        String encriptValue = aes.encrypt("@"+this.National_Id);
        return encriptValue;
    }

}
