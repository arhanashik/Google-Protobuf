package com.blackspider.googleprotobuf.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.blackspider.googleprotobuf.AddressBookProtos;

/*
 *  ****************************************************************************
 *  * Created by : Md. Hasnain on 4/16/2018 at 11:59 AM.
 *  * Email : ashik.pstu.cse@gmail.com
 *  *
 *  * Last edited by : Md. Hasnain on 4/16/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

public class Person implements Parcelable{
     private int id;
     private String name;
     private String email;
     private String phoneHome;
     private String phoneWork;

     private AddressBookProtos.AddressBook addressBook;
     private AddressBookProtos.Person person;

     public Person(int id, String name, String email, String phoneHome, String phoneWork) {
          this.id = id;
          this.name = name;
          this.email = email;
          this.phoneHome = phoneHome;
          this.phoneWork = phoneWork;


     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPhoneHome() {
          return phoneHome;
     }

     public void setPhoneHome(String phoneHome) {
          this.phoneHome = phoneHome;
     }

     public String getPhoneWork() {
          return phoneWork;
     }

     public void setPhoneWork(String phoneWork) {
          this.phoneWork = phoneWork;
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel parcel, int i) {
         parcel.writeInt(id);
         parcel.writeString(name);
         parcel.writeString(phoneHome);
         parcel.writeString(email);
         parcel.writeString(phoneWork);
     }

     public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator(){
         public Person createFromParcel(Parcel percel){
             return new Person(percel);
        }

        public Person[] newArray(int size){
             return new Person[size];
        }
    };

     public Person(Parcel parcel){
         id = parcel.readInt();
         name = parcel.readString();
         email = parcel.readString();
         phoneHome = parcel.readString();
         phoneWork = parcel.readString();
     }

}
