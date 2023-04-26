package com.jjpeng.tdd.args;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author JJPeng
 * @date 2023/4/5 15:59
 */
public class ArgsTest {

    // 第一个测试用例进行功能拆分后应该需要实现的功能：
    // Single Option:
    //  - Bool -l
    @Test
    public void should_parse_bool_option_to_true_if_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");
        assertTrue(option.logging());
    }

    @Test
    public void should_parse_bool_option_to_false_if_present() {
        BooleanOption option = Args.parse(BooleanOption.class);
        assertFalse(option.logging());
    }

    static record BooleanOption(@Option("l")boolean logging) {
    }

    // - Integer -p 8080
    @Test
    public void should_parse_integer_as_option_value() {
        IntegerOption option = Args.parse(IntegerOption.class, "-p", "8080");
        assertEquals(8080, option.port());
    }

    static record IntegerOption(@Option("p")int port) {
    }

    // - String -d /usr/logs
    @Test
    public void should_parse_string_as_option_value() {
        StringOption parse = Args.parse(StringOption.class, "-d", "/usr/logs");
        assertEquals("/usr/logs", parse.directory());
    }

    static record StringOption(@Option("d")String directory) {
    }

    // Multiple Options:
    // TODO： -l -p 8080 -d /usr/logs
    @Test
    public void should_parse_multi_options() {
        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    static record MultiOptions(@Option("l")boolean logging, @Option("p")int port, @Option("d")String directory) {
    }

    // Sad path:
    // TODO: - Bool -l t / -l t f
    // TODO: - Integer -p 8080 8081
    // TODO: - String -d / -d /usr/logs /usr/vars

    // default value:
    // TODO: - Bool -l:false
    // TODO: - Integer -p:0
    // TODO: - String -d:""


    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "3", "-5");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new int[]{1, 2, 3, -5}, options.decimals());
    }

    static record ListOptions(@Option("g")String[] group, @Option("d")int[] decimals) {
    }
}
