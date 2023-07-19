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
    @JsonValue
    private final String englishName;

    CategoryEnum(String category) {
        this.englishName = category;
    }

    public static class Category {
        public static final String KAYO = "KAYO";
        public static final String POP = "POP";
        public static final String ROCK = "ROCK";
        public static final String EDM = "EDM";
        public static final String JAZZCLASSIC = "JAZZ-CLASSIC";
        public static final String JPOP = "J-POP";
    }

    public static CategoryEnum nameOf(String englishName) {
        for(CategoryEnum e: CategoryEnum.values()) {
            System.out.println(e.toString());
            if(e.getEnglishName().equals(englishName))
                return e;
        }
        return null;
    }

}
