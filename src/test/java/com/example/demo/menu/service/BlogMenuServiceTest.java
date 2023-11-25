package com.example.demo.menu.service;

import com.example.demo.auth.MenuGrade;
import com.example.demo.menu.dto.req.BaseMenuReq;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BlogMenuServiceTest {

    @Autowired
    BlogMenuService blogMenuService;


    @Order(1)
    @DisplayName("one_deps_test_menu_insert")
    @ParameterizedTest(
            name =
                    "name={0}," +
                            " updatedBy={1}," +
                            " menuGrade={2}," +
                            " usedAt={3}," +
                            " depsBranch={4}"
    )
    @CsvFileSource(resources = "/data/one_deps_menu.csv")
    void insert_one_deps_menu_for_csv_source(
            String name,
            String updatedBy,
            String menuGrade,
            String usedAt,
            String depsBranch
    ) {
        MenuGrade menuGradeEnum = MenuGrade.valueOf(menuGrade);
        BaseMenuReq oneDeps = BaseMenuReq.builder()
                .name(name)
                .updatedBy(updatedBy)
                .menuGrade(menuGradeEnum)
                .usedAt(usedAt)
                .parentMenuId(null)
                .depsBranch(depsBranch)
                .build();
        boolean b = blogMenuService.menuInsert(oneDeps);
        Assertions.assertTrue(b);
    }

    @Order(2)
    @DisplayName("two_deps_test_menu_insert")
    @ParameterizedTest(
            name =
                    "name={0}," +
                            " updatedBy={1}," +
                            " menuGrade={2}," +
                            " usedAt={3}," +
                            " parentMenuId={4}," +
                            " depsBranch={5}"
    )
    @CsvFileSource(resources = "/data/two_deps_menu.csv")
    void insert_two_deps_menu_for_csv_source(
            String name,
            String updatedBy,
            String menuGrade,
            String usedAt,
            Integer parentMenuId,
            String depsBranch
    ) {
        MenuGrade menuGradeEnum = MenuGrade.valueOf(menuGrade);
        BaseMenuReq twoDeps = BaseMenuReq.builder()
                .name(name)
                .updatedBy(updatedBy)
                .menuGrade(menuGradeEnum)
                .usedAt(usedAt)
                .parentMenuId(parentMenuId)
                .depsBranch(depsBranch)
                .build();
        boolean b = blogMenuService.menuInsert(twoDeps);
        Assertions.assertTrue(b);
    }

    @Order(3)
    @DisplayName("three_deps_test_menu_insert")
    @ParameterizedTest(
            name =
                    "name={0}," +
                            " updatedBy={1}," +
                            " menuGrade={2}," +
                            " usedAt={3}," +
                            " parentMenuId={4}," +
                            " depsBranch={5}"
    )
    @CsvFileSource(resources = "/data/three_deps_menu.csv")
    void insert_three_deps_menu_for_csv_source(
            String name,
            String updatedBy,
            String menuGrade,
            String usedAt,
            Integer parentMenuId,
            String depsBranch
    ) {
        MenuGrade menuGradeEnum = MenuGrade.valueOf(menuGrade);
        BaseMenuReq threeDeps = BaseMenuReq.builder()
                .name(name)
                .updatedBy(updatedBy)
                .menuGrade(menuGradeEnum)
                .usedAt(usedAt)
                .parentMenuId(parentMenuId)
                .depsBranch(depsBranch)
                .build();
        boolean b = blogMenuService.menuInsert(threeDeps);
        Assertions.assertTrue(b);
    }


}