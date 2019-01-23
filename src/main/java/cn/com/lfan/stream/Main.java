package cn.com.lfan.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 8中的集合支持一个新的stream方法，它会返回一个流 {@link java.util.stream.Stream}
 * 流的定义：从支持数据处理操作的源生成的元素序列
 * <p>
 * 元素序列：可以访问特定元素类型的一组有序值。集合是数据结构，主要目的是以特定的时间/空间复杂度存储和访问元素。
 * 如 ArrayList与LinkedList。流的目的在于表达计算，如filter、sorted 和 map。
 * <p>
 * 源：流会使用一个提供数据的源。如集合、数组或输入/输出资源。从有序集合生成流时会保留原有的顺序。有列表生成的流，其元素顺序与列表一致。
 * <p>
 * 数据处理操作：常用操作用：filter、map、reduce、find、match、sort等。流操作可以顺序执行，也可以并行执行。
 *
 * @author: lfan
 * @date: 2019/1/22 14:59
 */
public class Main {


    private static List<Dish> dishes = Arrays.asList(
            new Dish("pock", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french", true, 500, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.FISH)
    );

    /**
     * 1. 定义一个菜单（dishes）;
     * <p>
     * 2. 对菜单调用 {@link Collection#stream()}方法，得到一个流;
     * <p>
     * 3. 对流应用一系列数据处理操作：filter、map、limit 和 collect;
     * 除了 collect 之外，所有操作都会返回另一个流，这样可以接成一条流水线，于是就可以看作对源的一个查询。
     * 最后 collect 操作开始处理流水线，并返回结果。在调用 collect 之前，没有任何结果产生，
     * 实际上根本就没有从 dishes 中选择数据。
     * <p>
     * 总结：链中的方法调用都在排队等待，直到调用 collect 。
     * <p>
     * filter-接受Lambda，从流中排除某些元素。
     * <p>
     * map-接受Lambda，将元素转换成其他形式或提取信息。本例使用Dish::getName，提取每道菜的菜名。
     * <p>
     * limit-截断流，使其元素不超过给定数量。
     * <p>
     * collect-将流转换为其他形式。
     * <p>
     * 流的使用一般包括三件事:
     * 1. 一个数据源（如集合）来执行一个查询;
     * 2. 一个中间操作链，形成一条流水线;
     * 3. 一个终端操作，执行流水线，并能生成结果。
     */
    @Test
    public void stream() {
        List<String> threeHighCaloricDishNames =
                //从dishes中获取流
                dishes.stream()
                        /*
                         * 建立操作流水线
                         */
                        //选出高热量的菜肴
                        .filter(dish -> dish.getCalories() > 300)
                        //获取菜名
                        .map(Dish::getName)
                        //只选择前3个
                        .limit(3)
                        //将结果保存在另一个List中
                        .collect(Collectors.toList());
        //结果：[pock, beef, chicken]
        System.out.println(threeHighCaloricDishNames);
    }


    /**
     * 集合和流
     * Java现有的集合和新的流都提供了接口，来配合代表元素型有序值的数据接口。所谓有序，就是按顺序取值。
     * 比如：
     * 存在DVD中的电影，就是一个集合（字节或者帧，无所谓），因为它包含了整个数据结构。
     * 在线观看电影就是一个流，字节流或者帧流。
     * <p>
     * 集中式内存中的数据结构，包含了数据结构中所有的值。
     * 流就像是一个延迟创建的集合：只有在消费者要求的时候才计算值。
     * <p>
     * 例子:
     * 以质数为例，要创建一个包含所有质数的集合，那程序就没完没了了，因为总有新的质数需要计算，然后加到集合中。
     * <p>
     * 和迭代器类似，流只能遍历一次。
     */
    @Test
    public void testStream() {
        List<String> list = Arrays.asList("java8", "java7", "java6");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
        //抛出异常 java.lang.IllegalStateException: stream has already been operated upon or closed
        stream.forEach(System.out::println);
    }

    /**
     * 筛选和切片
     *
     * @see java.util.stream.Streams 接口支持 filter 方法。该操作会接受一个谓词（一个返回boolean的函数）作为参数，
     * 并返回一个包含所有符合谓词的元素的流。
     * <p>
     * 筛选各异的元素
     * @see Stream#distinct() 返回一个元素各异的流。
     * <p>
     * 截断流
     * @see Stream#limit(long) 返回一个不超过给定长度的流。
     * <p>
     * 跳过元素
     * @see Stream#skip(long) 返回一个丢掉前long个元素的流。
     */
    @Test
    public void useStream() {
        // 所有符合素食者的菜肴
        List<Dish> vegetarianMenu = dishes.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        // 筛选出列表中所有的偶数，并确保没有重复。
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 5, 6, 7, 7, 8);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }
}
