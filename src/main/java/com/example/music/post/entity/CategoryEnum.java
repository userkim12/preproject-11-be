package com.example.music.post.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CategoryEnum {
    KAYO(Category.KAYO),
    POP(Category.POP),
    ROCK(Category.ROCK),
    EDM(Category.EDM),
    JAZZCLASSIC(Category.JAZZCLASSIC),
    JPOP(Category.JPOP);


    @Getter
    private final String korean;

    CategoryEnum(String category) {
        this.korean = category;
    }

    public static class Category {
        public static final String KAYO = "가요";
        public static final String POP = "팝";
        public static final String ROCK = "락";
        public static final String EDM = "EDM";
        public static final String JAZZCLASSIC = "재즈/클래식";
        public static final String JPOP = "J-POP";
    }

    public static CategoryEnum nameOf(String korean) {
        for(CategoryEnum e: CategoryEnum.values()) {
            if(e.getKorean().equals(korean))
                return e;
        }
        return null;
    }

}
