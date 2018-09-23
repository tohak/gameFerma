package ferma.service;

import ferma.domain.Acct;
import ferma.domain.Garden;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UtilService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
    private EntityManager em = emf.createEntityManager();
    private AcctService service = new AcctService(em);

    public UtilService() {
        List<Acct> acctList = service.findAll();
        Menu game = new Menu();
        game.startMenu(acctList);
        Acct acct = game.getAcct();
        service.save(acct);
    }
    public void printConsol(List<Acct> acctList) {
        for (Acct a : acctList) {
            System.out.println(a);
        }
    }
    public void close() {
        emf.close();
    }

}
