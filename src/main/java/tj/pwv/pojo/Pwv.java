package tj.pwv.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Pwv {
    private Long id;

    private Date date;

    private BigDecimal totalzen;

    private BigDecimal wetzen;

    private BigDecimal sigzen;

    private BigDecimal pw;

    private BigDecimal sigpw;

    private BigDecimal press;

    private BigDecimal temp;

    private BigDecimal zhd;

    private BigDecimal gradns;

    private BigDecimal signs;

    private BigDecimal gradew;

    private BigDecimal sigew;

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

    public BigDecimal getTotalzen() {
        return totalzen;
    }

    public void setTotalzen(BigDecimal totalzen) {
        this.totalzen = totalzen;
    }

    public BigDecimal getWetzen() {
        return wetzen;
    }

    public void setWetzen(BigDecimal wetzen) {
        this.wetzen = wetzen;
    }

    public BigDecimal getSigzen() {
        return sigzen;
    }

    public void setSigzen(BigDecimal sigzen) {
        this.sigzen = sigzen;
    }

    public BigDecimal getPw() {
        return pw;
    }

    public void setPw(BigDecimal pw) {
        this.pw = pw;
    }

    public BigDecimal getSigpw() {
        return sigpw;
    }

    public void setSigpw(BigDecimal sigpw) {
        this.sigpw = sigpw;
    }

    public BigDecimal getPress() {
        return press;
    }

    public void setPress(BigDecimal press) {
        this.press = press;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public BigDecimal getZhd() {
        return zhd;
    }

    public void setZhd(BigDecimal zhd) {
        this.zhd = zhd;
    }

    public BigDecimal getGradns() {
        return gradns;
    }

    public void setGradns(BigDecimal gradns) {
        this.gradns = gradns;
    }

    public BigDecimal getSigns() {
        return signs;
    }

    public void setSigns(BigDecimal signs) {
        this.signs = signs;
    }

    public BigDecimal getGradew() {
        return gradew;
    }

    public void setGradew(BigDecimal gradew) {
        this.gradew = gradew;
    }

    public BigDecimal getSigew() {
        return sigew;
    }

    public void setSigew(BigDecimal sigew) {
        this.sigew = sigew;
    }
}