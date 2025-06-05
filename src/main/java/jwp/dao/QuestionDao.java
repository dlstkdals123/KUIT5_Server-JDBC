package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.KeyHolder;
import jwp.model.Question;

import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Questions";
        RowMapper<Question> rowMapper = resultSet -> new Question(
                resultSet.getLong("questionId"),
                resultSet.getString("writer"),
                resultSet.getString("title"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate"),
                resultSet.getInt("countOfAnswer")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question insert(String writer, String title, String contents) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES(?,?,?,?,?)";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, writer);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, contents);
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(5, 0);
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);

        return findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> preparedStatement.setLong(1, id);

        RowMapper<Question> rowMapper = resultSet -> new Question(
                resultSet.getLong("questionId"),
                resultSet.getString("writer"),
                resultSet.getString("title"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate"),
                resultSet.getInt("countOfAnswer")
        );

        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public void updateCountOfAnswer(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE Questions SET countOfAnswer = ? WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setLong(1, question.getCountOfAnswer());
            preparedStatement.setLong(2, question.getQuestionId());
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }
}
