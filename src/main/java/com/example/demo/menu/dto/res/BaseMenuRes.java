package com.example.demo.menu.dto.res;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseMenuRes {

    private Integer oneDepsId;
    private String oneDepsName;
    private LocalDateTime oneDepsCreatedDate;
    private LocalDateTime oneDepsUpdatedDate;
    private String oneDepsCreatedBy;
    private String oneDepsUpdatedBy;
    private String oneDepsMenuGrade;
    private String oneDepsUsedAt;

    private Integer twoDepsId;
    private Integer twoDepsParentMenuId;
    private String twoDepsName;
    private LocalDateTime twoDepsCreatedDate;
    private LocalDateTime twoDepsUpdatedDate;
    private String twoDepsCreatedBy;
    private String twoDepsUpdatedBy;
    private String twoDepsMenuGrade;
    private String twoDepsUsedAt;

    private Integer threeDepsId;
    private Integer threeDepsParentMenuId;
    private String threeDepsName;
    private LocalDateTime threeDepsCreatedDate;
    private LocalDateTime threeDepsUpdatedDate;
    private String threeDepsCreatedBy;
    private String threeDepsUpdatedBy;
    private String threeDepsMenuGrade;
    private String threeDepsUsedAt;

    public OneDeps toOneDeps() {
        return OneDeps.builder()
                .oneDepsId(oneDepsId)
                .oneDepsName(oneDepsName)
                .oneDepsCreatedDate(oneDepsCreatedDate)
                .oneDepsUpdatedDate(oneDepsUpdatedDate)
                .oneDepsCreatedBy(oneDepsCreatedBy)
                .oneDepsUpdatedBy(oneDepsUpdatedBy)
                .oneDepsMenuGrade(oneDepsMenuGrade)
                .oneDepsUsedAt(oneDepsUsedAt)
                .build();
    }

    public TwoDeps toTwoDeps() {
        return TwoDeps.builder()
                .twoDepsId(twoDepsId)
                .twoDepsParentMenuId(twoDepsParentMenuId)
                .twoDepsName(twoDepsName)
                .twoDepsCreatedDate(twoDepsCreatedDate)
                .twoDepsUpdatedDate(twoDepsUpdatedDate)
                .twoDepsCreatedBy(twoDepsCreatedBy)
                .twoDepsUpdatedBy(twoDepsUpdatedBy)
                .twoDepsMenuGrade(twoDepsMenuGrade)
                .twoDepsUsedAt(twoDepsUsedAt)
                .build();
    }

    public ThreeDeps toThreeDeps() {
        return ThreeDeps.builder()
                .threeDepsId(threeDepsId)
                .threeDepsParentMenuId(threeDepsParentMenuId)
                .threeDepsName(threeDepsName)
                .threeDepsCreatedDate(threeDepsCreatedDate)
                .threeDepsUpdatedDate(threeDepsUpdatedDate)
                .threeDepsCreatedBy(threeDepsCreatedBy)
                .threeDepsUpdatedBy(threeDepsUpdatedBy)
                .threeDepsMenuGrade(threeDepsMenuGrade)
                .threeDepsUsedAt(threeDepsUsedAt)
                .build();
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OneDeps {
        private Integer oneDepsId;
        private String oneDepsName;
        private LocalDateTime oneDepsCreatedDate;
        private LocalDateTime oneDepsUpdatedDate;
        private String oneDepsCreatedBy;
        private String oneDepsUpdatedBy;
        private String oneDepsMenuGrade;
        private String oneDepsUsedAt;

        public OneDepsList toOneDepsList() {
            return OneDepsList.builder()
                    .oneDepsId(oneDepsId)
                    .oneDepsName(oneDepsName)
                    .oneDepsCreatedBy(oneDepsCreatedBy)
                    .oneDepsUpdatedBy(oneDepsUpdatedBy)
                    .oneDepsMenuGrade(oneDepsMenuGrade)
                    .oneDepsUsedAt(oneDepsUsedAt)
                    .build();
        }

        @Getter
        @Setter
        @Builder
        @ToString
        @EqualsAndHashCode
        @NoArgsConstructor
        @AllArgsConstructor
        public static class OneDepsList {
            private Integer oneDepsId;
            private String oneDepsName;
            private String oneDepsCreatedBy;
            private String oneDepsUpdatedBy;
            private String oneDepsMenuGrade;
            private String oneDepsUsedAt;
        }

    }

    @Getter
    @Setter
    @Builder
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TwoDeps {
        private Integer twoDepsId;
        private Integer twoDepsParentMenuId;
        private String twoDepsName;
        private LocalDateTime twoDepsCreatedDate;
        private LocalDateTime twoDepsUpdatedDate;
        private String twoDepsCreatedBy;
        private String twoDepsUpdatedBy;
        private String twoDepsMenuGrade;
        private String twoDepsUsedAt;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ThreeDeps {
        private Integer threeDepsId;
        private Integer threeDepsParentMenuId;
        private String threeDepsName;
        private LocalDateTime threeDepsCreatedDate;
        private LocalDateTime threeDepsUpdatedDate;
        private String threeDepsCreatedBy;
        private String threeDepsUpdatedBy;
        private String threeDepsMenuGrade;
        private String threeDepsUsedAt;
    }

}
