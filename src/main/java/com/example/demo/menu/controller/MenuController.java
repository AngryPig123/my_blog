package com.example.demo.menu.controller;

import com.example.demo.menu.dto.req.BaseMenuReq;
import com.example.demo.menu.dto.res.BaseMenuRes;
import com.example.demo.menu.service.BlogMenuService;
import com.example.demo.requestholder.RequestHolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/menu")
public class MenuController {

    private final BlogMenuService menuService;
    private final RequestHolderService requestHolderService;

    @PostMapping(path = "/list")
    public String getMenuListModifyForm(Model model) {

        //  insert form
        BaseMenuReq baseMenuReq = new BaseMenuReq();
        model.addAttribute("baseMenuReq", baseMenuReq);

        //  list
        List<BaseMenuRes> allMenuList = menuService.getAllMenuList();
        List<BaseMenuRes.OneDeps> oneDepsMenuList = menuService.getOneDepsMenuList(allMenuList);
        List<BaseMenuRes.OneDeps.OneDepsList> oneDepsMenuResList = oneDepsMenuList.stream().map(BaseMenuRes.OneDeps::toOneDepsList).collect(Collectors.toList());
        model.addAttribute("oneDepsMenuResList", oneDepsMenuResList);

        return "/menu/list";

    }

}
