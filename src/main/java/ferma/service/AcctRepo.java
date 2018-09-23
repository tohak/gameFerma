package ferma.service;

import ferma.domain.Acct;

import java.util.List;

public interface AcctRepo {

    public List<Acct> findAll();
    public void save(Acct user);
}
