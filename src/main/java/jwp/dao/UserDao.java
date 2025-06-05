package jwp.dao;

import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Users VALUES(?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
        };


        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Users WHERE userId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> preparedStatement.setString(1, userId);
        RowMapper<User> rowMapper = resultSet -> new User(
            resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("email")
        );

        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "UPDATE Users SET password = ?, name = ?, email = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
        };

        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Users";
        RowMapper<User> rowMapper = resultSet -> new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("email"));

        return jdbcTemplate.query(sql, rowMapper);
    }
}