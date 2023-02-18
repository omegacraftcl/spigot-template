package com.claudiobo.template.enums;

import lombok.Getter;

public enum Test {
    ONE(0), TWO(1);

    @Getter
    private int value;

    private Test(int value) {
        this.value = value;
    }

    public static Test valueOf(int value) {
        switch (value) {
            case 0:
                return Test.ONE;
            case 1:
                return Test.TWO;
        }
        return null;
    }
}
