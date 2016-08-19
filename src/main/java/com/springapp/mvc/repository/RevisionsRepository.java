package com.springapp.mvc.repository;

import com.springapp.mvc.domain.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 09.01.2016.
 */
@Repository
@Transactional
public class RevisionsRepository {
    @Autowired
    SessionFactory sessionFactory;

    private final String GETALL = "SELECT revisions.id, revisions.date, revisions.description, revisiontype.abr,\n" +
            "medicinecard.pib, disinfectant_solution.name, medicinecard.adress, medicinecard.burnDate, medicinecard.identifyCode," +
            "refferalDictionary.name, revisions.firstPatalogy\n" +
            "FROM Revisions revisions, Revisiontype revisiontype, Medicinecard medicinecard, DisinfectantSolution disinfectant_solution," +
            " RefferalDictionary refferalDictionary\n" +
            "where revisions.revisiontypeId = revisiontype.id and revisions.medicinecardId = medicinecard.id\n" +
            "and revisions.disinfectantSolutionId = disinfectant_solution.id and revisions.refferalDictionaryId = refferalDictionary.id " +
            "and revisions.doctorId = :doctorId ";

    private final String GETALL1 = "SELECT revisions.id, revisions.date, revisions.description, revisiontype.abr,\n" +
            "medicinecard.pib, disinfectant_solution.name, medicinecard.adress, medicinecard.burnDate, medicinecard.identifyCode," +
            "refferalDictionary.name, revisions.firstPatalogy\n" +
            "FROM Revisions revisions, Revisiontype revisiontype, Medicinecard medicinecard, DisinfectantSolution disinfectant_solution," +
            " RefferalDictionary refferalDictionary\n" +
            "where revisions.revisiontypeId = revisiontype.id and revisions.medicinecardId = medicinecard.id\n" +
            "and revisions.disinfectantSolutionId = disinfectant_solution.id and revisions.refferalDictionaryId = refferalDictionary.id " +
            "and revisions.doctorId = :doctorId " +
            "ORDER BY revisions.date desc ";

    private final String GET_BY_ID = "SELECT revisions.id, " +
            "DATE(revisions.date), " +
            "revisions.description," +
            " revisiontype.revisionName," +
            " medicinecard.pib," +
            " disinfectant_solution.name," +
            " medicinecard.adress," +
            " medicinecard.burnDate," +
            " medicinecard.identifyCode," +
            " refferalDictionary.name, " +
            " apparatus.name," +
            " revisions.firstPatalogy\n " +
            "FROM Revisions revisions, Revisiontype revisiontype, Medicinecard medicinecard," +
            " DisinfectantSolution disinfectant_solution," +
            " RefferalDictionary refferalDictionary," +
            " Apparatus apparatus " +
            "where revisions.revisiontypeId = revisiontype.id " +
            "and revisions.medicinecardId = medicinecard.id\n" +
            "and revisions.disinfectantSolutionId = disinfectant_solution.id " +
            "and revisions.refferalDictionaryId = refferalDictionary.id " +
            "and revisions.apparatusId = apparatus.id " +
            "and revisions.id = :id " +
            "and revisions.doctorId = :doctorId ";


    private final String SELECT_FOR_MANIPULATIONS = "SELECT count(*) FROM Revisions revisions," +
            "Manipulations  manipulations " +
            "WHERE manipulations.revisionsId = revisions.id and YEAR(revisions.date) = " +
            ":year and MONTH(revisions.date) = :month and DAY(revisions.date) = :day" +
            " and revisions.doctorId = :doctorId and revisions.revisiontypeId = :rTypeId";
    private final String SELECT_FOR_OPERATIONS = "SELECT count(*) FROM Revisions revisions," +
            "Operations operations " +
            "WHERE operations.revisionsId = revisions.id and YEAR(revisions.date) = " +
            ":year and MONTH(revisions.date) = :month and DAY(revisions.date) = :day" +
            " and revisions.revisiontypeId = :rTypeId and revisions.doctorId = :doctorId";
    private final String SELECT_FOR_FIRST_PATALOGY = "SELECT count(*) FROM Revisions revisions " +
            "WHERE revisions.firstPatalogy = 1 and YEAR(revisions.date) = " +
            ":year and MONTH(revisions.date) = :month and DAY(revisions.date) = :day" +
            " and revisions.revisiontypeId = :rTypeId and revisions.doctorId = :doctorId";

    public final String SQL0 = "SELECT DAY(revisions.date) FROM Revisions revisions where " +
            "MONTH(revisions.date) = :month and YEAR(revisions.date) = :year and " +
            "revisions.doctorId = :doctorId " +
            "GROUP BY DAY(revisions.date) " +
            "ORDER BY revisions.date";

    public final String SQL1 = "SELECT count(*) FROM Revisions revisions where " +
            "MONTH(revisions.date) = :month and YEAR(revisions.date) = :year and " +
            "DAY(revisions.date) = :day and revisions.doctorId = :doctorId " +
            "and revisions.revisiontypeId = :rTypeId";


    public List getAllByDateAndId(String sql, Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(GETALL + sql);
        query.setParameter("doctorId", doctorId);
        return query.list();
    }

    public List getAll(Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(GETALL1);
        query.setParameter("doctorId", doctorId);
        return query.list();
    }

    public List getRevision(int id, Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(GET_BY_ID);
        query.setParameter("id", id);
        query.setParameter("doctorId", doctorId);
        return query.list();
    }

    public Integer addRevision(Revisions r) {
        this.sessionFactory.getCurrentSession().save(r);
        return r.getId();
    }

    public Integer getCountByDisinfectantSolutionId(Integer id) {
        return this.sessionFactory.getCurrentSession().createSQLQuery("SELECT disinfectant_solution_id from revisions where disinfectant_solution_id=" + id).list().size();
    }

    public Integer getCountByApparatusId(Integer id) {
        return this.sessionFactory.getCurrentSession().createSQLQuery("SELECT apparatus_id from revisions where apparatus_id=" + id).list().size();
    }

    public Integer getCountRefferalId(Integer id) {
        return this.sessionFactory.getCurrentSession().createSQLQuery("SELECT refferal_dictionary_id from revisions where refferal_dictionary_id = " + id).list().size();
    }

    public Integer getCountMedicinecard(Integer id) {
        return this.sessionFactory.getCurrentSession().createSQLQuery("SELECT medicinecard_identifyCode from revisions where medicinecard_identifyCode = " + id).list().size();
    }

    public Long[][] getRevisionsForMonth(Integer month, Integer year, Integer doctorId) {
        List<Revisiontype> rTypes = this.sessionFactory.getCurrentSession().createQuery("from Revisiontype ").list();
        Query query = this.sessionFactory.getCurrentSession().createQuery(SQL0);
        query.setParameter("month", month);
        query.setParameter("year", year);
        query.setParameter("doctorId", doctorId);
        List days = query.list();
        Long[][] m = new Long[days.size()][(rTypes.size() + 1) * 6 + 1];
        for (int i = 0; i < days.size(); i++) {
            int k = 7;
            m[i][0] = (long) (Integer) days.get(i);
            m[i][1] = (long) 0;
            m[i][2] = (long) 0;
            m[i][3] = (long) 0;
            m[i][4] = (long) 0;
            m[i][5] = (long) 0;
            m[i][6] = (long) 0;
            m[i][7] = (long) 0;
            for (int j = 0; j < rTypes.size(); j++) {
                m[i][k + 1] = getRevisionCountForDay((Integer) days.get(i),
                        month, year, rTypes.get(j).getId(), doctorId);
                m[i][k + 2] = getManipulationCount((Integer) days.get(i),
                        month, year, rTypes.get(j).getId(), doctorId);
                m[i][k + 4] = getOperationCount((Integer) days.get(i),
                        month, year, rTypes.get(j).getId(), doctorId);
                m[i][k + 5] = getFirstPatalogyCount((Integer) days.get(i),
                        month, year, rTypes.get(j).getId(), doctorId);
                m[i][k] = m[i][k + 1] + m[i][k + 2] + m[i][k + 4];
                m[i][k + 3] = m[i][k + 2];
                m[i][1] += m[i][k];
                m[i][2] += m[i][k + 1];
                m[i][3] += m[i][k + 2];
                m[i][4] += m[i][k + 3];
                m[i][5] += m[i][k + 4];
                m[i][6] += m[i][k + 5];
                k += 6;
            }
        }
        return m;
    }

//    public Long[][] getRevisionsForYear(Integer year, Integer doctorId) {
//        List<Revisiontype> rTypes = this.sessionFactory.getCurrentSession().createQuery("from Revisiontype ").list();
//        Long[][] data = new Long[12][(rTypes.size() + 1) * 6 + 1];
//        int sum = 0;
//        for (int i = 1; i <= 12; i++) {
//            data[i-1][0] = (long)i;
//            Long[][] mData = getRevisionsForMonth(i,year,doctorId);
//            if(mData.length > 0) {
//                for (int j = 1; j < mData[0].length; j++) {
//                    sum = 0;
//                    for (int k = 0; k < mData.length; k++) {
//                        sum += mData[k][j];
//                    }
//                    data[i - 1][j] = (long) sum;
//                }
//            }else{
//                for(int j = 1; j < data[i-1].length; j++){
//                    data[i - 1][j] = (long)0;
//                }
//            }
//        }
//        return data;
//    }

    public Long[][] getRevisionsForMonths(Integer monthCount, Integer year, Integer doctorId){
        List<Revisiontype> rTypes = this.sessionFactory.getCurrentSession().createQuery("from Revisiontype ").list();
        Long[][] data = new Long[monthCount][(rTypes.size() + 1) * 6 + 1];
        int sum = 0;
        for (int i = 1; i <= monthCount; i++) {
            data[i-1][0] = (long)i;
            Long[][] mData = getRevisionsForMonth(i,year,doctorId);
            if(mData.length > 0) {
                for (int j = 1; j < mData[0].length; j++) {
                    sum = 0;
                    for (int k = 0; k < mData.length; k++) {
                        sum += mData[k][j];
                    }
                    data[i - 1][j] = (long) sum;
                }
            }else{
                for(int j = 1; j < data[i-1].length; j++){
                    data[i - 1][j] = (long)0;
                }
            }
        }
        return data;
    }

    public Long getRevisionCountForDay(Integer day, Integer month, Integer year,
                                       Integer rTypeId, Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(SQL1);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("day", day);
        query.setParameter("doctorId", doctorId);
        query.setParameter("rTypeId", rTypeId);
        System.out.println("day=" + day + " rType=" + rTypeId + " res=" + query.list().get(0));
        return (Long) query.list().get(0);
    }

    public Long getManipulationCount(Integer day, Integer month, Integer year,
                                     Integer rTypeId, Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(SELECT_FOR_MANIPULATIONS);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("day", day);
        query.setParameter("rTypeId", rTypeId);
        query.setParameter("doctorId", doctorId);
        return (Long) query.list().get(0);
    }

    public Long getOperationCount(Integer day, Integer month, Integer year,
                                  Integer rTypeId, Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(SELECT_FOR_OPERATIONS);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("day", day);
        query.setParameter("rTypeId", rTypeId);
        query.setParameter("doctorId", doctorId);
        return (Long) query.list().get(0);
    }

    public Long getFirstPatalogyCount(Integer day, Integer month, Integer year,
                                      Integer rTypeId, Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(SELECT_FOR_FIRST_PATALOGY);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("day", day);
        query.setParameter("rTypeId", rTypeId);
        query.setParameter("doctorId", doctorId);
        return (Long) query.list().get(0);
    }

    public void update(Integer id, String description) {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("update revisions " +
                "set description = :description where id = :id");
        query.setParameter("description", description);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
