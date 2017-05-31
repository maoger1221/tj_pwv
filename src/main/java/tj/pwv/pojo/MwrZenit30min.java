package tj.pwv.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MwrZenit30min {
    private Long id;

    private Date date;

    private Integer rainflag;

    private BigDecimal iwv;

    private BigDecimal elev;

    private BigDecimal azi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRainflag() {
        return rainflag;
    }

    public void setRainflag(Integer rainflag) {
        this.rainflag = rainflag;
    }

    public BigDecimal getIwv() {
        return iwv;
    }

    public void setIwv(BigDecimal iwv) {
        this.iwv = iwv;
    }

    public BigDecimal getElev() {
        return elev;
    }

    public void setElev(BigDecimal elev) {
        this.elev = elev;
    }

    public BigDecimal getAzi() {
        return azi;
    }

    public void setAzi(BigDecimal azi) {
        this.azi = azi;
    }
}