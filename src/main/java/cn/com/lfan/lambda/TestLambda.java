package cn.com.lfan.lambda;

import cn.com.lfan.jo.Student;
import cn.com.lfan.jo.Wealthy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @anthor: lfan
 * @date: 2018/9/30 15:36
 */
public class TestLambda {

    @Test
    public void test() {
        //JDK1.8前
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("小张", 18, "男", 10.0, 1.0));
        studentList.add(new Student("小芳", 18, "女", 1000.0, 1.1));
        studentList.add(new Student("小红", 19, "女", 550.0, 1.3));

//        Map<String, Student> studentMap = new HashMap<>();
//        studentMap.put("001", new Student("小张", 18, "男", 10.0, 1.0));
//        studentMap.put("002", new Student("小芳", 18, "女", 1000.0, 1.1));

        //将studentList中的所有name放入list中。
        List<String> names = studentList.stream().map(v -> v.getName()).collect(Collectors.toList());
        //将studentList中的所有年龄放入set中
        Set<Integer> ages = studentList.stream().map(v -> v.getAge()).collect(Collectors.toSet());

        //将所有 女 同学的姓名放入list中。
        List<String> girlnames = studentList.stream().filter(f -> "女".equals(f.getSex()))
                .map(v -> v.getName()).collect(Collectors.toList());

        List<Wealthy> wealthys = studentList.stream().filter(f -> f.getMoney() > 1000)
                .map(v -> {
                    Wealthy wealthy = new Wealthy();
                    wealthy.setAge(v.getAge());
                    wealthy.setName(v.getName());
                    wealthy.setPinMoney(v.getMoney());
                    return wealthy;
                }).collect(Collectors.toList());
    }
}
