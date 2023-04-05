package com.jjpeng.tdd.args;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author JJPeng
 * @date 2023/4/5 15:59
 */
public class ArgsTest {

    @Test
    public void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    @Test
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "3", "-5");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new int[]{1, 2, 3, -5}, options.decimals());
    }

    static record Options(@Option("l")boolean logging, @Option("p")int port, @Option("d")String directory) {
    }

    static record ListOptions(@Option("g")String[] group, @Option("d")int[] decimals) {
    }
}
