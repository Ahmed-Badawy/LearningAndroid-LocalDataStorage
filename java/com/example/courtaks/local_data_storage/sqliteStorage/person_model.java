package com.example.courtaks.local_data_storage.sqliteStorage;

/**
 * Created by courtaks on 2/2/2016.
 */
public class person_model {

    public long Id;
    public String Name;
    public int Age;
    public int Photo;
    public String person_info;

    public person_model(){

    }

    public person_model(int id,String name,int age , int photo , String person_info){
        this.Id = id;
        this.Name = name;
        this.Age = age;
        this.Photo = photo;
        this.person_info = person_info;
    }

    public person_model(String name,int age , int photo , String person_info){
        this.Name = name;
        this.Age = age;
        this.Photo = photo;
        this.person_info = person_info;
    }

    public String toString(){
        return this.Id+" - "+this.Name;
    }

}
