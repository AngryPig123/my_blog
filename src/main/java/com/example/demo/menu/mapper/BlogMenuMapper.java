package com.example.demo.menu.mapper;

import com.example.demo.menu.dto.req.BaseMenuReq;
import com.example.demo.menu.dto.res.BaseMenuRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMenuMapper {

    int blogMenuInsert(BaseMenuReq baseMenuReq);

    List<BaseMenuRes> blogMenuListAllSelect();

    boolean doesMenuExists(@Param("menuDepsList") List<String> menuDepsList);

}
