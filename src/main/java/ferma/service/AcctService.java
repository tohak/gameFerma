package ferma.service;

import ferma.domain.Acct;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AcctService implements AcctRepo {
    private static EntityManager menager;


    public AcctService(EntityManager em) {
        menager = em;
    }

    @Override
    public List<Acct> findAll() {
        List<Acct> acctList = new ArrayList<>();
        try {
            Query query = menager.createQuery("SELECT  c FROM  Acct c", Acct.class);
            acctList = (List<Acct>) query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Ошибка. Не удалось вытянуть аккаунты с базы или база пуста");
        }
        return acctList;
    }

    @Override
    public void save(Acct user) {

        menager.getTransaction().begin();
        try{
            user.setCheckup(true);
            menager.merge(user);
            menager.getTransaction().commit();
        }catch (Exception e){
            menager.getTransaction().rollback();
        }
    }
    public void refreshBD(List<Acct> accts){
        for (Acct user:accts){
            if (!user.getCheckup()){
                save(user);
            }
        }
    }


}
