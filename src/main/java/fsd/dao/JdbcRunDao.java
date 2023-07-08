package fsd.dao;

import fsd.exception.DaoException;
import fsd.model.Run;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcRunDao implements RunDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcRunDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Run addRun (Run run) throws DaoException{
        Run newRun = null;

        String sql = "INSERT INTO run (date_time, highest_level_completed)\n" +
                "VALUES (?, ?) RETURNING run_id";

        try {
            Integer newRunId = jdbcTemplate.queryForObject(sql, Integer.class, run.getDateTime(), run.getHighestLevelComplete());

            newRun = getRunById(newRunId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection issue with server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return newRun;
    }

    @Override
    public Run getRunById(int runId) throws DaoException{

        Run run = new Run();
        String sql = "SELECT * FROM run WHERE run_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, runId);
            if (results.next()) {
                run.setRunId(results.getInt("run_id"));
                if (results.getTimestamp("date_time") != null) {
                    run.setDateTime(results.getTimestamp("date_time").toLocalDateTime());
                }
                run.setHighestLevelComplete(results.getInt("highest_level_completed"));
                return run;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection issue with server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return null;
    }
}
