package com.example.demo.menu.dto.req;

import com.example.demo.auth.MenuGrade;
import com.example.demo.exception.ExtendLogException;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseMenuReq {

    private Integer id;

    private String name;

    private String updatedBy;

    private MenuGrade menuGrade;

    private String usedAt;

    private Integer parentMenuId;

    private String depsBranch;

    @Builder
    public BaseMenuReq(String name, String updatedBy, MenuGrade menuGrade, String usedAt, Integer parentMenuId, String depsBranch) {

        if (depsBranch.equals("two") || depsBranch.equals("three")) {
            //  ToDO RestControllerAdvice 로 에러 response 를 구현 한다.
            if (parentMenuId == null) {
                throw new ExtendLogException(String.format("%s, parent_menu_id = %s ", this.getClass(), this.getParentMenuId()));
            }
        }

        this.name = name;
        this.updatedBy = updatedBy;
        this.menuGrade = menuGrade;
        this.usedAt = usedAt;
        this.parentMenuId = parentMenuId;
        this.depsBranch = depsBranch;

    }

}
