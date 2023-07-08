package fsd.dao;

import fsd.exception.DaoException;

public interface SkillDao {
    void linkRunSkill(int runId, String skillName, int skillValue) throws DaoException;
}
