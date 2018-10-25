package cn.com.lfan.jo;

import java.io.Serializable;

/**
 * @anthor: lfan
 * @date: 2018/9/30 15:55
 */
public class Wealthy implements Serializable {

    private String name;
    private Integer age;
    private Double pinMoney;

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

    public Double getPinMoney() {
        return pinMoney;
    }

    public void setPinMoney(Double pinMoney) {
        this.pinMoney = pinMoney;
    }
}
