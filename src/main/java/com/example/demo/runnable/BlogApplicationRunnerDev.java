package com.example.demo.runnable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("develop")
@RequiredArgsConstructor
public class BlogApplicationRunnerDev implements ApplicationRunner {

    private final CommonRunnableService commonRunnableService;

    @Override
    public void run(ApplicationArguments args) {
        commonRunnableService.initServerLink();
    }

}
