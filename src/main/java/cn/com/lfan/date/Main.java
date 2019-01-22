package cn.com.lfan.date;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * @author: lfan
 * @date: 2019/1/21 13:46
 */
public class Main {
    /**
     * LocatDate 的对象是一个不可变对象，只提供了简单的日期，并不含当前的时间信息。
     * 可以通过静态工厂方法of创建一个LocatDate实例，提供了一些获取年月日周的方法。
     * 如:
     *
     * @see LocalDate#getYear()
     * @see LocalDate#getMonth()
     * @see LocalDate#getDayOfYear()
     * @see LocalDate#getDayOfWeek()
     * 也可以通过传入一个 {@link java.time.temporal.TemporalField} 参数给get方法拿到同样的信息。
     * @see java.time.temporal.TemporalField 是一个接口，定义了如何访问temporal对象摸个字段的值。
     * @see java.time.temporal.ChronoField 枚举实现了这一接口。
     */
    @Test
    public void localDate() {
        /*
         * 使用LocalDate的of方法创建对象
         */
        LocalDate localDate = LocalDate.of(2019, 1, 21);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int day = localDate.getDayOfYear();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        boolean leap = localDate.isLeapYear();
        //该月的有几天
        int len = localDate.lengthOfMonth();

        /*
         * 使用TemporalField 读取LocatDate的值
         */
        LocalDate today = LocalDate.now();
        int year1 = today.get(ChronoField.YEAR);
    }

    /**
     * 一天中的时间，比如13:45:20，可以使用{@link LocalTime}类表示。
     * 可以使用of重载的工厂方法创建LocalTime的实例。第一个重载函数接收小时和分钟，第二个重载函数同时还接收秒。
     * 同{@link LocalDate} 一样，LocatTime也提供了一些getter方法访问这些变量的值。
     * 如：
     *
     * @see LocalTime#getHour()
     * @see LocalTime#getSecond()
     * @see LocalTime#getMinute()
     */
    @Test
    public void localTime() {
        LocalTime localTime = LocalTime.of(13, 45, 20);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
    }

    /**
     * {@link LocalDate} 和{@link LocalTime} 可以通过解析代表它们的字符串创建。
     * 使用静态方法parse。
     * 可以向{@link LocalDate#parse(CharSequence)}方法传递一个{@link java.time.format.DateTimeFormatter}.
     * 该类的实例定义了如何格式化一个日期或者时间对象。
     */
    @Test
    public void parseStr() {
        LocalDate localDate = LocalDate.parse("2019-01-21");
        LocalTime localTime = LocalTime.parse("13:45:30");
    }

    /**
     * 合并日期和时间
     * 将{@link LocalDate} 和 {@link LocalTime} 合并。
     *
     * @see LocalDateTime 同时表示日期和时间。可以直接创建，也可以通过合并日期和时间对象构造。
     */
    @Test
    public void localDateTime() {
        LocalDate localDate = LocalDate.parse("2019-01-21");
        LocalTime localTime = LocalTime.parse("13:45:30");
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.MARCH, 18, 13, 45, 30);
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);
    }

    /**
     * 机器的日期和时间格式
     * 我们习惯于以星期几，几号，几点，几分这样的方式理解日期和时间，但是这种方式对于计算机而言并不容易立即。
     * 从计算机的角度来看，建模时间最自然的格式是表示一个持续时间段上某个点的单一大整型数。
     *
     * @see Instant 对时间建模的方式，以Unix元年时间开始所经历的秒数进行计算，（UTC时区1970年1月1日午夜时分）
     * 开始所经历的秒数进行计算。
     * <p>
     * 创建实例方法：
     * @see Instant#ofEpochMilli(long) 传递一个代表秒数的值创建一个该类的实例。
     * @see Instant#ofEpochSecond(long, long) 秒数，第二个参数：以纳秒为单位的参数值（0-999 999 999之间）。
     */
    @Test
    public void instant() {
        Instant instant = Instant.ofEpochMilli(3);
        Instant instant1 = Instant.ofEpochSecond(3, 111_000_000);
    }

    /**
     * @see Duration 用于计算两个时间间隔的类。
     * 目前为止，上述所有的类都实现了{@link java.time.temporal.Temporal}接口，该接口定义了如何读取和操纵为时间建模的对象的值。
     * 我们需要创建两个Temporal对象之间的duration。{@link Duration#between(Temporal, Temporal)} 方法。
     * 可以创建两个LocalTime对象和两个LocalDateTime对象，或两个Instant对象之间的duration。
     * {@link LocalDateTime} 和 {@link Instant} 是为了不同的目的而设计的。一个是为了便于人们阅读使用，另一个是为了便于机器处理。
     * 如果试图在这两个类之间创建duration，会触发一个DateTimeException异常。
     * 由于Duration类主要用于以秒和纳秒衡量时间的长短，不能仅向{@link Duration#between(Temporal, Temporal)}方法传递一个LocalDate对象做参数。
     */
    @Test
    public void durationAndPeriod() {
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.of(13, 45, 30);
        Duration duration = Duration.between(time1, time2);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now();
        Duration duration1 = Duration.between(localDateTime, localDateTime1);

        //不能
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.now();
        Duration duration2 = Duration.between(localDate, localDate1);

        // 抛出 DateTimeException 异常
        Instant instant = Instant.ofEpochSecond(3);
        Duration duration3 = Duration.between(localDateTime, instant);
    }

    /**
     * 操作、解析和格式化日期
     * <p>
     * 如果已经有一个{@link LocalDate}对象，想要创建它的一个修改版，最直接也最简单的方法时用
     * withAttribute方法。withAttribute方法会创建对象的一个副本，并按照需要修改它的属性。
     * 下面代码中所有方法都返回一个修改了属性的对象。它们都不会修改原来的对象。
     */
    @Test
    public void operationDate() {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.withYear(2018);
        LocalDate localDate2 = localDate1.withDayOfMonth(25);
        LocalDate localDate3 = localDate2.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println(localDate3.getYear());
    }

    /**
     * 截止目前，所有日期操作都是相对比较直接的。有时，需要进行复杂的操作。
     * 比如，将日期调整到下个周日、下个工作日、或者本月的最后一天。
     *
     * @see java.time.temporal.TemporalAdjuster
     */
    @Test
    public void temporalAdjuster() {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate localDate2 = localDate1.with(lastDayOfMonth());
    }
}
