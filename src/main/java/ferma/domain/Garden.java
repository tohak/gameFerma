package ferma.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "garden_tbl")
public class Garden extends BaseEntety {

    public static final boolean BUN_NULL = false;
    public static final int LENGTH = 10;

    @Column(name = "pole", nullable = BUN_NULL, length = LENGTH)
    private Integer pole;

    @Column(name = "sbor")
    private LocalDateTime sbor;

    @Column(name = "check_pole")
    private Boolean checkarea;

    @Column(name = "price_shell")
    private Long priceShell;


    public Garden() {
    }

    public Garden(Integer pole, Boolean checkarea) {
        this.pole = pole;
        this.checkarea = checkarea;
    }

    public Long getPriceShell() {
        return priceShell;
    }

    public void setPriceShell(Long priceShell) {
        this.priceShell = priceShell;
    }

    public Integer getPole() {
        return pole;
    }

    public void setPole(Integer pole) {
        this.pole = pole;
    }

    public LocalDateTime getSbor() {
        return sbor;
    }

    public void setSbor(LocalDateTime sbor) {
        this.sbor = sbor;
    }

    public Boolean getCheckarea() {
        return checkarea;
    }

    public void setCheckarea(Boolean checkarea) {
        this.checkarea = checkarea;
    }

    @Override
    public String toString() {
        return "Garden{" +
                "pole=" + pole +
                ", sbor=" + sbor +
                ", checkarea=" + checkarea +
                '}';
    }
}
