package fsd.dao;

import fsd.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcSkillDao implements SkillDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcSkillDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void linkRunSkill(int runId, String skillName, int skillValue) throws DaoException {
        int rowsAffected = 0;
        String sql = "INSERT INTO run_skill (run_id, skill_name, skill_value)\n" +
                "VALUES (?, ?, ?);";
        try {
            rowsAffected = jdbcTemplate.update(sql, runId, skillName, skillValue);
            if (rowsAffected == 0) {
                throw new DaoException("No rows impacted, expected one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection issue with server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
    }
}
