package com.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, new UserRowMapper());
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    public void createUser(User user) {
        jdbcTemplate.update("INSERT INTO users (login) VALUES (?)", user.getLogin());
    }

    public void updateUserLogin(int id, String login) {
        jdbcTemplate.update("UPDATE users SET login = ? WHERE id = ?", login, id);
    }

    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    public void changeLogin(int id, String login) {
        jdbcTemplate.update("UPDATE users SET login = ? WHERE id = ?", login, id);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId((int) rs.getLong("id"));
            user.setLogin(rs.getString("login"));
            return user;
        }
    }
}
