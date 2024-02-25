package com.moikiitos.model;

import java.util.ArrayList;
import java.util.List;

public class RelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RelationExample() {
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

        public Criteria andFolloweridIsNull() {
            addCriterion("followerId is null");
            return (Criteria) this;
        }

        public Criteria andFolloweridIsNotNull() {
            addCriterion("followerId is not null");
            return (Criteria) this;
        }

        public Criteria andFolloweridEqualTo(Long value) {
            addCriterion("followerId =", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridNotEqualTo(Long value) {
            addCriterion("followerId <>", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridGreaterThan(Long value) {
            addCriterion("followerId >", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridGreaterThanOrEqualTo(Long value) {
            addCriterion("followerId >=", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridLessThan(Long value) {
            addCriterion("followerId <", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridLessThanOrEqualTo(Long value) {
            addCriterion("followerId <=", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridIn(List<Long> values) {
            addCriterion("followerId in", values, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridNotIn(List<Long> values) {
            addCriterion("followerId not in", values, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridBetween(Long value1, Long value2) {
            addCriterion("followerId between", value1, value2, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridNotBetween(Long value1, Long value2) {
            addCriterion("followerId not between", value1, value2, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidIsNull() {
            addCriterion("followeeId is null");
            return (Criteria) this;
        }

        public Criteria andFolloweeidIsNotNull() {
            addCriterion("followeeId is not null");
            return (Criteria) this;
        }

        public Criteria andFolloweeidEqualTo(Long value) {
            addCriterion("followeeId =", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidNotEqualTo(Long value) {
            addCriterion("followeeId <>", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidGreaterThan(Long value) {
            addCriterion("followeeId >", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidGreaterThanOrEqualTo(Long value) {
            addCriterion("followeeId >=", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidLessThan(Long value) {
            addCriterion("followeeId <", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidLessThanOrEqualTo(Long value) {
            addCriterion("followeeId <=", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidIn(List<Long> values) {
            addCriterion("followeeId in", values, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidNotIn(List<Long> values) {
            addCriterion("followeeId not in", values, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidBetween(Long value1, Long value2) {
            addCriterion("followeeId between", value1, value2, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidNotBetween(Long value1, Long value2) {
            addCriterion("followeeId not between", value1, value2, "followeeid");
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