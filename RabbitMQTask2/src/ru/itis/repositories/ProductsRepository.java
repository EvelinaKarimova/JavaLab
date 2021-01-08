package ru.itis.repositories;

import ru.itis.models.Product;

import java.sql.*;
import java.util.Optional;

public class ProductsRepository {
    private static final String SELECT = "select * from product where name = ?";
    private Connection connection;

    public ProductsRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<Product> find(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            set.next();
            Product product = new Product(set.getString(2), set.getString(3),
                    set.getInt(4));
            statement.close();
            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
