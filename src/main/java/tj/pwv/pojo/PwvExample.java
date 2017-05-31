package tj.pwv.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PwvExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PwvExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andTotalzenIsNull() {
            addCriterion("totalzen is null");
            return (Criteria) this;
        }

        public Criteria andTotalzenIsNotNull() {
            addCriterion("totalzen is not null");
            return (Criteria) this;
        }

        public Criteria andTotalzenEqualTo(BigDecimal value) {
            addCriterion("totalzen =", value, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenNotEqualTo(BigDecimal value) {
            addCriterion("totalzen <>", value, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenGreaterThan(BigDecimal value) {
            addCriterion("totalzen >", value, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalzen >=", value, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenLessThan(BigDecimal value) {
            addCriterion("totalzen <", value, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalzen <=", value, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenIn(List<BigDecimal> values) {
            addCriterion("totalzen in", values, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenNotIn(List<BigDecimal> values) {
            addCriterion("totalzen not in", values, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalzen between", value1, value2, "totalzen");
            return (Criteria) this;
        }

        public Criteria andTotalzenNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalzen not between", value1, value2, "totalzen");
            return (Criteria) this;
        }

        public Criteria andWetzenIsNull() {
            addCriterion("wetzen is null");
            return (Criteria) this;
        }

        public Criteria andWetzenIsNotNull() {
            addCriterion("wetzen is not null");
            return (Criteria) this;
        }

        public Criteria andWetzenEqualTo(BigDecimal value) {
            addCriterion("wetzen =", value, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenNotEqualTo(BigDecimal value) {
            addCriterion("wetzen <>", value, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenGreaterThan(BigDecimal value) {
            addCriterion("wetzen >", value, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wetzen >=", value, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenLessThan(BigDecimal value) {
            addCriterion("wetzen <", value, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wetzen <=", value, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenIn(List<BigDecimal> values) {
            addCriterion("wetzen in", values, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenNotIn(List<BigDecimal> values) {
            addCriterion("wetzen not in", values, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wetzen between", value1, value2, "wetzen");
            return (Criteria) this;
        }

        public Criteria andWetzenNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wetzen not between", value1, value2, "wetzen");
            return (Criteria) this;
        }

        public Criteria andSigzenIsNull() {
            addCriterion("sigzen is null");
            return (Criteria) this;
        }

        public Criteria andSigzenIsNotNull() {
            addCriterion("sigzen is not null");
            return (Criteria) this;
        }

        public Criteria andSigzenEqualTo(BigDecimal value) {
            addCriterion("sigzen =", value, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenNotEqualTo(BigDecimal value) {
            addCriterion("sigzen <>", value, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenGreaterThan(BigDecimal value) {
            addCriterion("sigzen >", value, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sigzen >=", value, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenLessThan(BigDecimal value) {
            addCriterion("sigzen <", value, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sigzen <=", value, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenIn(List<BigDecimal> values) {
            addCriterion("sigzen in", values, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenNotIn(List<BigDecimal> values) {
            addCriterion("sigzen not in", values, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigzen between", value1, value2, "sigzen");
            return (Criteria) this;
        }

        public Criteria andSigzenNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigzen not between", value1, value2, "sigzen");
            return (Criteria) this;
        }

        public Criteria andPwIsNull() {
            addCriterion("PW is null");
            return (Criteria) this;
        }

        public Criteria andPwIsNotNull() {
            addCriterion("PW is not null");
            return (Criteria) this;
        }

        public Criteria andPwEqualTo(BigDecimal value) {
            addCriterion("PW =", value, "pw");
            return (Criteria) this;
        }

        public Criteria andPwNotEqualTo(BigDecimal value) {
            addCriterion("PW <>", value, "pw");
            return (Criteria) this;
        }

        public Criteria andPwGreaterThan(BigDecimal value) {
            addCriterion("PW >", value, "pw");
            return (Criteria) this;
        }

        public Criteria andPwGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PW >=", value, "pw");
            return (Criteria) this;
        }

        public Criteria andPwLessThan(BigDecimal value) {
            addCriterion("PW <", value, "pw");
            return (Criteria) this;
        }

        public Criteria andPwLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PW <=", value, "pw");
            return (Criteria) this;
        }

        public Criteria andPwIn(List<BigDecimal> values) {
            addCriterion("PW in", values, "pw");
            return (Criteria) this;
        }

        public Criteria andPwNotIn(List<BigDecimal> values) {
            addCriterion("PW not in", values, "pw");
            return (Criteria) this;
        }

        public Criteria andPwBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PW between", value1, value2, "pw");
            return (Criteria) this;
        }

        public Criteria andPwNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PW not between", value1, value2, "pw");
            return (Criteria) this;
        }

        public Criteria andSigpwIsNull() {
            addCriterion("sigpw is null");
            return (Criteria) this;
        }

        public Criteria andSigpwIsNotNull() {
            addCriterion("sigpw is not null");
            return (Criteria) this;
        }

        public Criteria andSigpwEqualTo(BigDecimal value) {
            addCriterion("sigpw =", value, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwNotEqualTo(BigDecimal value) {
            addCriterion("sigpw <>", value, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwGreaterThan(BigDecimal value) {
            addCriterion("sigpw >", value, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sigpw >=", value, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwLessThan(BigDecimal value) {
            addCriterion("sigpw <", value, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sigpw <=", value, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwIn(List<BigDecimal> values) {
            addCriterion("sigpw in", values, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwNotIn(List<BigDecimal> values) {
            addCriterion("sigpw not in", values, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigpw between", value1, value2, "sigpw");
            return (Criteria) this;
        }

        public Criteria andSigpwNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigpw not between", value1, value2, "sigpw");
            return (Criteria) this;
        }

        public Criteria andPressIsNull() {
            addCriterion("press is null");
            return (Criteria) this;
        }

        public Criteria andPressIsNotNull() {
            addCriterion("press is not null");
            return (Criteria) this;
        }

        public Criteria andPressEqualTo(BigDecimal value) {
            addCriterion("press =", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotEqualTo(BigDecimal value) {
            addCriterion("press <>", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressGreaterThan(BigDecimal value) {
            addCriterion("press >", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("press >=", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLessThan(BigDecimal value) {
            addCriterion("press <", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLessThanOrEqualTo(BigDecimal value) {
            addCriterion("press <=", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressIn(List<BigDecimal> values) {
            addCriterion("press in", values, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotIn(List<BigDecimal> values) {
            addCriterion("press not in", values, "press");
            return (Criteria) this;
        }

        public Criteria andPressBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("press between", value1, value2, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("press not between", value1, value2, "press");
            return (Criteria) this;
        }

        public Criteria andTempIsNull() {
            addCriterion("temp is null");
            return (Criteria) this;
        }

        public Criteria andTempIsNotNull() {
            addCriterion("temp is not null");
            return (Criteria) this;
        }

        public Criteria andTempEqualTo(BigDecimal value) {
            addCriterion("temp =", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotEqualTo(BigDecimal value) {
            addCriterion("temp <>", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThan(BigDecimal value) {
            addCriterion("temp >", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("temp >=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThan(BigDecimal value) {
            addCriterion("temp <", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThanOrEqualTo(BigDecimal value) {
            addCriterion("temp <=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempIn(List<BigDecimal> values) {
            addCriterion("temp in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotIn(List<BigDecimal> values) {
            addCriterion("temp not in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("temp between", value1, value2, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("temp not between", value1, value2, "temp");
            return (Criteria) this;
        }

        public Criteria andZhdIsNull() {
            addCriterion("ZHD is null");
            return (Criteria) this;
        }

        public Criteria andZhdIsNotNull() {
            addCriterion("ZHD is not null");
            return (Criteria) this;
        }

        public Criteria andZhdEqualTo(BigDecimal value) {
            addCriterion("ZHD =", value, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdNotEqualTo(BigDecimal value) {
            addCriterion("ZHD <>", value, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdGreaterThan(BigDecimal value) {
            addCriterion("ZHD >", value, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ZHD >=", value, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdLessThan(BigDecimal value) {
            addCriterion("ZHD <", value, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ZHD <=", value, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdIn(List<BigDecimal> values) {
            addCriterion("ZHD in", values, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdNotIn(List<BigDecimal> values) {
            addCriterion("ZHD not in", values, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZHD between", value1, value2, "zhd");
            return (Criteria) this;
        }

        public Criteria andZhdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZHD not between", value1, value2, "zhd");
            return (Criteria) this;
        }

        public Criteria andGradnsIsNull() {
            addCriterion("gradNS is null");
            return (Criteria) this;
        }

        public Criteria andGradnsIsNotNull() {
            addCriterion("gradNS is not null");
            return (Criteria) this;
        }

        public Criteria andGradnsEqualTo(BigDecimal value) {
            addCriterion("gradNS =", value, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsNotEqualTo(BigDecimal value) {
            addCriterion("gradNS <>", value, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsGreaterThan(BigDecimal value) {
            addCriterion("gradNS >", value, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("gradNS >=", value, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsLessThan(BigDecimal value) {
            addCriterion("gradNS <", value, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("gradNS <=", value, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsIn(List<BigDecimal> values) {
            addCriterion("gradNS in", values, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsNotIn(List<BigDecimal> values) {
            addCriterion("gradNS not in", values, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gradNS between", value1, value2, "gradns");
            return (Criteria) this;
        }

        public Criteria andGradnsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gradNS not between", value1, value2, "gradns");
            return (Criteria) this;
        }

        public Criteria andSignsIsNull() {
            addCriterion("sigNS is null");
            return (Criteria) this;
        }

        public Criteria andSignsIsNotNull() {
            addCriterion("sigNS is not null");
            return (Criteria) this;
        }

        public Criteria andSignsEqualTo(BigDecimal value) {
            addCriterion("sigNS =", value, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsNotEqualTo(BigDecimal value) {
            addCriterion("sigNS <>", value, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsGreaterThan(BigDecimal value) {
            addCriterion("sigNS >", value, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sigNS >=", value, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsLessThan(BigDecimal value) {
            addCriterion("sigNS <", value, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sigNS <=", value, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsIn(List<BigDecimal> values) {
            addCriterion("sigNS in", values, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsNotIn(List<BigDecimal> values) {
            addCriterion("sigNS not in", values, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigNS between", value1, value2, "signs");
            return (Criteria) this;
        }

        public Criteria andSignsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigNS not between", value1, value2, "signs");
            return (Criteria) this;
        }

        public Criteria andGradewIsNull() {
            addCriterion("gradEW is null");
            return (Criteria) this;
        }

        public Criteria andGradewIsNotNull() {
            addCriterion("gradEW is not null");
            return (Criteria) this;
        }

        public Criteria andGradewEqualTo(BigDecimal value) {
            addCriterion("gradEW =", value, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewNotEqualTo(BigDecimal value) {
            addCriterion("gradEW <>", value, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewGreaterThan(BigDecimal value) {
            addCriterion("gradEW >", value, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("gradEW >=", value, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewLessThan(BigDecimal value) {
            addCriterion("gradEW <", value, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewLessThanOrEqualTo(BigDecimal value) {
            addCriterion("gradEW <=", value, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewIn(List<BigDecimal> values) {
            addCriterion("gradEW in", values, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewNotIn(List<BigDecimal> values) {
            addCriterion("gradEW not in", values, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gradEW between", value1, value2, "gradew");
            return (Criteria) this;
        }

        public Criteria andGradewNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gradEW not between", value1, value2, "gradew");
            return (Criteria) this;
        }

        public Criteria andSigewIsNull() {
            addCriterion("sigEW is null");
            return (Criteria) this;
        }

        public Criteria andSigewIsNotNull() {
            addCriterion("sigEW is not null");
            return (Criteria) this;
        }

        public Criteria andSigewEqualTo(BigDecimal value) {
            addCriterion("sigEW =", value, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewNotEqualTo(BigDecimal value) {
            addCriterion("sigEW <>", value, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewGreaterThan(BigDecimal value) {
            addCriterion("sigEW >", value, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sigEW >=", value, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewLessThan(BigDecimal value) {
            addCriterion("sigEW <", value, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sigEW <=", value, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewIn(List<BigDecimal> values) {
            addCriterion("sigEW in", values, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewNotIn(List<BigDecimal> values) {
            addCriterion("sigEW not in", values, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigEW between", value1, value2, "sigew");
            return (Criteria) this;
        }

        public Criteria andSigewNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sigEW not between", value1, value2, "sigew");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}