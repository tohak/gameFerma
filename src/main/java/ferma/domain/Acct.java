package ferma.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "acct_tbl")
public class Acct extends BaseEntety {
    public static final boolean BUN_NULL = false;
    public static final int LENGTH = 20;

    @Column(name = "acc_name", nullable = BUN_NULL, length = LENGTH, unique = true)
    private String name;

    @Column(name = "pass", nullable = BUN_NULL, length = LENGTH)
    private String pass;

    @Column(name = "money")
    private Long money;

    @Column(name = "up_acct")
    private Boolean checkup;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "acct_id", nullable = false)
    private Set<Garden> gardens;

    public Acct() {
    }

    public Acct(String name, String pass, Long money, Boolean checkup, Set<Garden> gardens) {
        this.name = name;
        this.pass = pass;
        this.money = money;
        this.checkup = checkup;
        this.gardens = gardens;
    }

    public Acct(String name, String pass, Long money, Boolean checkup) {
        this.name = name;
        this.pass = pass;
        this.money = money;
        this.checkup = checkup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<Garden> getGardens() {
        return gardens;
    }

    public void setGardens(Set<Garden> gardens) {
        this.gardens = gardens;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Boolean getCheckup() {
        return checkup;
    }

    public void setCheckup(Boolean checkup) {
        this.checkup = checkup;
    }

    @Override
    public String toString() {
        return "Acct{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", money=" + money +
                ", checkup=" + checkup +
                ", gardens=" + gardens +
                '}';
    }
}
