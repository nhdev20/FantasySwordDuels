package fsd.Dao;

import fsd.Exception.DaoException;

public interface SelectionDao {
    void linkRunSelection (int runId, String selectionName, int selectionCount) throws DaoException;
}
