package fsd.dao;

import fsd.exception.DaoException;
import fsd.model.Run;

public interface RunDao {
    Run addRun(Run run) throws DaoException;

    Run getRunById(int id) throws DaoException;
}
