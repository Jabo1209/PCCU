package com.example.pccu;

import java.util.Date;

public class FirebaseBean {

    String Title,Room,Parkspace,Pet,Money,Address,WaterFee,ElectricityFee,Internet,Remark,ImageLink;
    Date Date;

    public FirebaseBean(){

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getParkspace() {
        return Parkspace;
    }

    public void setParkspace(String parkspace) {
        Parkspace = parkspace;
    }

    public String getPet() {
        return Pet;
    }

    public void setPet(String pet) {
        Pet = pet;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getWaterFee() {
        return WaterFee;
    }

    public void setWaterFee(String waterFee) {
        WaterFee = waterFee;
    }

    public String getElectricityFee() {
        return ElectricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        ElectricityFee = electricityFee;
    }

    public String getInternet() {
        return Internet;
    }

    public void setInternet(String internet) {
        Internet = internet;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Date getDate(){
        return  Date;
    }

    public void setDate(Date date) {
        Date = date;
    }
}
