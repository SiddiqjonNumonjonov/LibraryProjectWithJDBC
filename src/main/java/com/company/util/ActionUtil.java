package com.company.util;

import com.company.container.ComponentContainer;

public class ActionUtil {
    public static Integer getAction() {
        return ComponentContainer.scannerForDigit.nextInt();
    }
}
