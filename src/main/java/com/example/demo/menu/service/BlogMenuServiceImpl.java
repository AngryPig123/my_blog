package com.example.demo.menu.service;

import com.example.demo.menu.dto.req.BaseMenuReq;
import com.example.demo.menu.dto.res.BaseMenuRes;
import com.example.demo.menu.mapper.BlogMenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogMenuServiceImpl implements BlogMenuService {

    private final BlogMenuMapper blogMenuMapper;

    @Override
    public boolean menuInsert(BaseMenuReq baseMenuReq) {
        int i = blogMenuMapper.blogMenuInsert(baseMenuReq);
        log.info("i = {}", i);
        log.info("baseMenuReq = {}", baseMenuReq);
        return i == 1;
    }

    @Override
    public List<BaseMenuRes> getAllMenuList() {
        List<BaseMenuRes> baseMenuRes = blogMenuMapper.blogMenuListAllSelect();
        log.info("baseMenuRes = {}", baseMenuRes);

        return baseMenuRes;
    }

    @Override
    public List<BaseMenuRes.OneDeps> getOneDepsMenuList(List<BaseMenuRes> baseMenuResList) {
        List<BaseMenuRes.OneDeps> oneDepsList = baseMenuResList.stream().map(BaseMenuRes::toOneDeps).distinct().collect(Collectors.toList());
        log.info("oneDepsList = {}", oneDepsList);
        if (oneDepsList.isEmpty()) {
            log.error("oneDepsList = {}", oneDepsList);
        }
        return oneDepsList;
    }

    @Override
    public List<BaseMenuRes.TwoDeps> getTwoDepsMenuList(List<BaseMenuRes> baseMenuResList) {
        List<BaseMenuRes.TwoDeps> twoDepsList = baseMenuResList.stream().map(BaseMenuRes::toTwoDeps).distinct().collect(Collectors.toList());
        log.info("twoDepsList = {}", twoDepsList);
        if (twoDepsList.isEmpty()) {
            log.error("twoDepsList = {}", twoDepsList);
        }
        return twoDepsList;
    }

    @Override
    public List<BaseMenuRes.ThreeDeps> getThreeDepsMenuList(List<BaseMenuRes> baseMenuResList) {
        List<BaseMenuRes.ThreeDeps> threeDepsList = baseMenuResList.stream().map(BaseMenuRes::toThreeDeps).distinct().collect(Collectors.toList());
        log.info("threeDepsList = {}", threeDepsList);
        if (threeDepsList.isEmpty()) {
            log.error("threeDepsList = {}", threeDepsList);
        }
        return threeDepsList;
    }

    @Override
    public void setMenuForModel(Model model) {
        Result result = getResult();
        model.addAttribute("oneDepsMenuList", result.oneDepsMenuList);
        model.addAttribute("twoDepsMenuList", result.twoDepsMenuList);
        model.addAttribute("threeDepsMenuList", result.threeDepsMenuList);
    }

    @Override
    public void setMenuForModel(ModelAndView modelAndView) {
        Result result = getResult();
        modelAndView.addObject("oneDepsMenuList", result.oneDepsMenuList);
        modelAndView.addObject("twoDepsMenuList", result.twoDepsMenuList);
        modelAndView.addObject("threeDepsMenuList", result.threeDepsMenuList);
    }

    @NotNull
    private Result getResult() {
        List<BaseMenuRes> allMenuList = getAllMenuList();
        List<BaseMenuRes.OneDeps> oneDepsMenuList = this.getOneDepsMenuList(allMenuList);
        List<BaseMenuRes.TwoDeps> twoDepsMenuList = this.getTwoDepsMenuList(allMenuList);
        List<BaseMenuRes.ThreeDeps> threeDepsMenuList = this.getThreeDepsMenuList(allMenuList);
        return new Result(oneDepsMenuList, twoDepsMenuList, threeDepsMenuList);
    }

    private static class Result {
        public final List<BaseMenuRes.OneDeps> oneDepsMenuList;
        public final List<BaseMenuRes.TwoDeps> twoDepsMenuList;
        public final List<BaseMenuRes.ThreeDeps> threeDepsMenuList;

        public Result(List<BaseMenuRes.OneDeps> oneDepsMenuList, List<BaseMenuRes.TwoDeps> twoDepsMenuList, List<BaseMenuRes.ThreeDeps> threeDepsMenuList) {
            this.oneDepsMenuList = oneDepsMenuList;
            this.twoDepsMenuList = twoDepsMenuList;
            this.threeDepsMenuList = threeDepsMenuList;
        }
    }

}
