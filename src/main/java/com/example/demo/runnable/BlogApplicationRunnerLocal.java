package com.example.demo.runnable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.lang.Thread.sleep;

@Slf4j
@Component
@Profile("local")
@RequiredArgsConstructor
public class BlogApplicationRunnerLocal implements ApplicationRunner {

    @Value("${cloud.localstack.start}")
    private String LOCAL_STACK_START;

    @Value("${cloud.localstack.stop}")
    private String LOCAL_STACK_STOP;

    private static final String COMMAND_START = "cmd /c ";
    private final CommonRunnableService commonRunnableService;

    @Override
    public void run(ApplicationArguments args) {
        initLocalStack();
        commonRunnableService.createLocalStackBucket();
        commonRunnableService.initBucketImageFolder();
        commonRunnableService.initBucketDefaultImageFolder();
        commonRunnableService.initServerLink();
    }

    private void initLocalStack() {
        commandStart(LOCAL_STACK_STOP);
        commandStart(LOCAL_STACK_START);
    }

    private void commandStart(String command) {
        StringBuilder stringBuilder = new StringBuilder(COMMAND_START);
        stringBuilder.append(command);
        try {
            Process exec = Runtime.getRuntime().exec(stringBuilder.toString());
            exec.waitFor();
            sleep(2000);
        } catch (IOException ioException) {
            log.error("shell script error = ", ioException);
        } catch (InterruptedException interruptedException) {
            log.error("shell script interrupted error = ", interruptedException);
        }
    }

}
