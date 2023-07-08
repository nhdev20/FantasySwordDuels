package fsd.dao;

import fsd.exception.DaoException;

public interface SelectionDao {
    void linkRunSelection (int runId, String selectionName, int selectionCount) throws DaoException;
}
