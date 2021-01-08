package ru.itis;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class Main{
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("task1");
        MongoCollection<Document> users = database.getCollection("users");

        users.find().forEach(document -> System.out.println(document.getString("last_name")));
    }
}