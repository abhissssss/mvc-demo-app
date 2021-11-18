package com.avisheksingh.salary.model;

import java.util.Objects;

public class EmployeeEntity {
    private final Long id;
    private String name;
    private Integer salary;
    private Integer age;

    public EmployeeEntity(Long id, String name, Integer salary, Integer age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public static EmployeeEntity.Builder builderFactory() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private Integer salary;
        private Integer age;
        
        private Builder() {}
        
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSalary(Integer salary) {
            this.salary = salary;
            return this;
        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public EmployeeEntity build() {
            return new EmployeeEntity(id, name, salary, age);
        }
    }

    public EmployeeEntity(Long id, String name, Integer salary) {
        this(id, name, salary, -1);
    }

    public EmployeeEntity(Long id) {
        this(id, "", -1);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.join(" ", String.valueOf(id), name, String.valueOf(salary));
    }

    // https://openjdk.java.net/jeps/394
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof EmployeeEntity employee) {
            return employee.getId().equals(this.getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
