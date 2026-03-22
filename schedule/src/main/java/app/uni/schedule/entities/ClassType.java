package app.uni.schedule.entities;

import lombok.Getter;

public enum ClassType {
    LECTURE("лек."),
    LABORATORY_LESSON("лаб. з."),
    PRACTICAL_LESSON("пр. з."),
    PRACTICE("практ."),
    GRADED_CREDIT("дифф. зач."),
    CREDIT("зач."),
    EXAM("экз."),
    CREDIT_TRANSFER("пер."),
    COURSE_WORK("курс. р."),
    COMMISSION("ком.");

    @Getter
    private final String title;

    ClassType(String title) { this.title = title; }

}
