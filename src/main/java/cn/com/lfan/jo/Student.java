package cn.com.lfan.jo;

import java.io.Serializable;

/**
 * @anthor: lfan
 * @date: 2018/9/30 15:37
 */
public class Student implements Serializable {

    private String name;
    private Integer age;
    private String sex;
    private Double money;
    private Double legLength;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getLegLength() {
        return legLength;
    }

    public void setLegLength(Double legLength) {
        this.legLength = legLength;
    }

    public Student(String name, Integer age, String sex, Double money, Double legLength) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.money = money;
        this.legLength = legLength;
    }
}
