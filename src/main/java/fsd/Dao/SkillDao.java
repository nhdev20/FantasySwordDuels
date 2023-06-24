package fsd.Dao;

import fsd.Exception.DaoException;

public interface SkillDao {
    void linkRunSkill(int runId, String skillName, int skillValue) throws DaoException;
}
