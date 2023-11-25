package com.example.demo.menu.service;

import com.example.demo.menu.dto.req.BaseMenuReq;
import com.example.demo.menu.dto.res.BaseMenuRes;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BlogMenuService {
    boolean menuInsert(BaseMenuReq baseMenuReq);

    List<BaseMenuRes> getAllMenuList();

    List<BaseMenuRes.OneDeps> getOneDepsMenuList(List<BaseMenuRes> baseMenuResList);

    List<BaseMenuRes.TwoDeps> getTwoDepsMenuList(List<BaseMenuRes> baseMenuResList);

    List<BaseMenuRes.ThreeDeps> getThreeDepsMenuList(List<BaseMenuRes> baseMenuResList);

    void setMenuForModel(Model model);
    void setMenuForModel(ModelAndView modelAndView);

}
