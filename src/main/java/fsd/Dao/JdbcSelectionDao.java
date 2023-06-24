package fsd.Dao;

import fsd.Exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcSelectionDao implements SelectionDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcSelectionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void linkRunSelection(int runId, String selectionName, int selectionCount) throws DaoException {
        int rowsAffected = 0;
        String sql = "INSERT INTO run_selection (run_id, selection_name, selection_count)\n" +
                "VALUES (?, ?, ?);";
        try {
            rowsAffected = jdbcTemplate.update(sql, runId, selectionName, selectionCount);
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
