package tj.pwv.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MwrZenit30minExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MwrZenit30minExample() {
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

        public Criteria andRainflagIsNull() {
            addCriterion("rainflag is null");
            return (Criteria) this;
        }

        public Criteria andRainflagIsNotNull() {
            addCriterion("rainflag is not null");
            return (Criteria) this;
        }

        public Criteria andRainflagEqualTo(Integer value) {
            addCriterion("rainflag =", value, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagNotEqualTo(Integer value) {
            addCriterion("rainflag <>", value, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagGreaterThan(Integer value) {
            addCriterion("rainflag >", value, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("rainflag >=", value, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagLessThan(Integer value) {
            addCriterion("rainflag <", value, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagLessThanOrEqualTo(Integer value) {
            addCriterion("rainflag <=", value, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagIn(List<Integer> values) {
            addCriterion("rainflag in", values, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagNotIn(List<Integer> values) {
            addCriterion("rainflag not in", values, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagBetween(Integer value1, Integer value2) {
            addCriterion("rainflag between", value1, value2, "rainflag");
            return (Criteria) this;
        }

        public Criteria andRainflagNotBetween(Integer value1, Integer value2) {
            addCriterion("rainflag not between", value1, value2, "rainflag");
            return (Criteria) this;
        }

        public Criteria andIwvIsNull() {
            addCriterion("iwv is null");
            return (Criteria) this;
        }

        public Criteria andIwvIsNotNull() {
            addCriterion("iwv is not null");
            return (Criteria) this;
        }

        public Criteria andIwvEqualTo(BigDecimal value) {
            addCriterion("iwv =", value, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvNotEqualTo(BigDecimal value) {
            addCriterion("iwv <>", value, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvGreaterThan(BigDecimal value) {
            addCriterion("iwv >", value, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("iwv >=", value, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvLessThan(BigDecimal value) {
            addCriterion("iwv <", value, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvLessThanOrEqualTo(BigDecimal value) {
            addCriterion("iwv <=", value, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvIn(List<BigDecimal> values) {
            addCriterion("iwv in", values, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvNotIn(List<BigDecimal> values) {
            addCriterion("iwv not in", values, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("iwv between", value1, value2, "iwv");
            return (Criteria) this;
        }

        public Criteria andIwvNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("iwv not between", value1, value2, "iwv");
            return (Criteria) this;
        }

        public Criteria andElevIsNull() {
            addCriterion("elev is null");
            return (Criteria) this;
        }

        public Criteria andElevIsNotNull() {
            addCriterion("elev is not null");
            return (Criteria) this;
        }

        public Criteria andElevEqualTo(BigDecimal value) {
            addCriterion("elev =", value, "elev");
            return (Criteria) this;
        }

        public Criteria andElevNotEqualTo(BigDecimal value) {
            addCriterion("elev <>", value, "elev");
            return (Criteria) this;
        }

        public Criteria andElevGreaterThan(BigDecimal value) {
            addCriterion("elev >", value, "elev");
            return (Criteria) this;
        }

        public Criteria andElevGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("elev >=", value, "elev");
            return (Criteria) this;
        }

        public Criteria andElevLessThan(BigDecimal value) {
            addCriterion("elev <", value, "elev");
            return (Criteria) this;
        }

        public Criteria andElevLessThanOrEqualTo(BigDecimal value) {
            addCriterion("elev <=", value, "elev");
            return (Criteria) this;
        }

        public Criteria andElevIn(List<BigDecimal> values) {
            addCriterion("elev in", values, "elev");
            return (Criteria) this;
        }

        public Criteria andElevNotIn(List<BigDecimal> values) {
            addCriterion("elev not in", values, "elev");
            return (Criteria) this;
        }

        public Criteria andElevBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("elev between", value1, value2, "elev");
            return (Criteria) this;
        }

        public Criteria andElevNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("elev not between", value1, value2, "elev");
            return (Criteria) this;
        }

        public Criteria andAziIsNull() {
            addCriterion("azi is null");
            return (Criteria) this;
        }

        public Criteria andAziIsNotNull() {
            addCriterion("azi is not null");
            return (Criteria) this;
        }

        public Criteria andAziEqualTo(BigDecimal value) {
            addCriterion("azi =", value, "azi");
            return (Criteria) this;
        }

        public Criteria andAziNotEqualTo(BigDecimal value) {
            addCriterion("azi <>", value, "azi");
            return (Criteria) this;
        }

        public Criteria andAziGreaterThan(BigDecimal value) {
            addCriterion("azi >", value, "azi");
            return (Criteria) this;
        }

        public Criteria andAziGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("azi >=", value, "azi");
            return (Criteria) this;
        }

        public Criteria andAziLessThan(BigDecimal value) {
            addCriterion("azi <", value, "azi");
            return (Criteria) this;
        }

        public Criteria andAziLessThanOrEqualTo(BigDecimal value) {
            addCriterion("azi <=", value, "azi");
            return (Criteria) this;
        }

        public Criteria andAziIn(List<BigDecimal> values) {
            addCriterion("azi in", values, "azi");
            return (Criteria) this;
        }

        public Criteria andAziNotIn(List<BigDecimal> values) {
            addCriterion("azi not in", values, "azi");
            return (Criteria) this;
        }

        public Criteria andAziBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("azi between", value1, value2, "azi");
            return (Criteria) this;
        }

        public Criteria andAziNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("azi not between", value1, value2, "azi");
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