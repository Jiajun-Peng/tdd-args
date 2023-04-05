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
    public void should() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        options.logging();
        options.port();
        options.directory();
    }

    static record Options(@Option("l")boolean logging, @Option("p")int port, @Option("d")String directory) {
    }
}
