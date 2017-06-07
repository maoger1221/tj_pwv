package tj.pwv.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Swv {
    private Long id;

    private Date date;

    private BigDecimal swv;

    private BigDecimal elev;

    private BigDecimal azi;

    private Integer prn;

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

    public BigDecimal getSwv() {
        return swv;
    }

    public void setSwv(BigDecimal swv) {
        this.swv = swv;
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

    public Integer getPrn() {
        return prn;
    }

    public void setPrn(Integer prn) {
        this.prn = prn;
    }
}