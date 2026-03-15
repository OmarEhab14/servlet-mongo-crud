package com.example.servletmongocrud.infrastructure.daos;

import com.example.servletmongocrud.domain.daos.UserDAO;
import com.example.servletmongocrud.domain.entities.User;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMongoDAO implements UserDAO {
    private final MongoCollection<Document> userCollection;

    public UserMongoDAO(MongoClient client) {
        this.userCollection = client
                .getDatabase("Users")
                .getCollection("Users");
    }

    @Override
    public Optional<User> findById(String id) {
            ObjectId objectId;

            try {
                objectId = new ObjectId(id);
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }

            Document document = userCollection.find(Filters.eq("_id", objectId))
                                        .first();
            if (document == null) {
                return Optional.empty();
            }

            User user = User.builder()
                    .id(document.getObjectId("_id").toHexString())
                    .firstName(document.getString("first_name"))
                    .lastName(document.getString("last_name"))
                    .email(document.getString("email"))
                    .age(document.getInteger("age"))
                    .build();

            return Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        FindIterable<Document> document = userCollection.find();
        List<User> users = new ArrayList<>();

        try (MongoCursor<Document> cursor = document.iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                User user = User.builder()
                        .id(doc.getObjectId("_id").toHexString())
                        .firstName(doc.getString("first_name"))
                        .lastName(doc.getString("last_name"))
                        .email(doc.getString("email"))
                        .age(doc.getInteger("age"))
                        .build();

                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User save(User user) {
        Document userDocument = new Document()
                .append("first_name", user.getFirstName())
                .append("last_name", user.getLastName())
                .append("email", user.getEmail())
                .append("age", user.getAge());

        var result = userCollection.insertOne(userDocument);

        if (result.getInsertedId() != null) {
            user.setId(result.getInsertedId()
                    .asObjectId()
                    .getValue()
                    .toString());
        }

        return user;
    }
}
