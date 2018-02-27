package com.commerce.log;

import com.commerce.annotations.LoggingMode;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@LoggingMode(loggingState = false)
public class EventLogException extends Exception {
    public static final int ADD_FLOWER = 0;
    public static final int SET_PRICE = 1;
    public static final int SUPPLY_FLOWER = 2;
    public static final int CREATE_BOUQUET = 3;
    public static final int SALE_BOUQUET = 4;
    public static final int ADD_NE_FLOWER = 5;
    public static final int SET_PRICE_TO_NEF = 6;
    public static final int SUPPLY_NE_FLOWER = 7;

    private final int EVENT_CODE;
    private final boolean isException;
    private final static String[] EVENTS = {"Adding a flower to the bouquet",
                                            "Setting a price for flowers",
                                            "Delivery of flowers",
                                            "Creating a bouquet",
                                            "Bouquets sale",
                                            "Adding a non-existent flower to the bouquet",
                                            "Setting a price for a non-existent flower",
                                            "Delivery of a non-existent flower"};
    private StringBuilder sb;

    public EventLogException(int eventCode) {
        EVENT_CODE = eventCode;
        isException = false;
    }
    public EventLogException(int eventCode, boolean isException) {
        this.isException = isException;
        EVENT_CODE = eventCode;
    }
    public EventLogException(Throwable t, int eventCode, boolean isException) {
        initCause(t);
        this.isException = isException;
        EVENT_CODE = eventCode;
    }

    public void printLog() {
        LoggingMode logMode = this.getClass().getDeclaredAnnotation(LoggingMode.class);
        if (!logMode.loggingState()) return;
        sb = new StringBuilder();
        if (isException) { sb.append("\tException"); }
        else { sb.append("\tEvent"); }
        switch (EVENT_CODE) {
            case ADD_FLOWER:
            case SET_PRICE:
            case SUPPLY_FLOWER:
            case CREATE_BOUQUET:
            case SALE_BOUQUET:
            case ADD_NE_FLOWER:
            case SET_PRICE_TO_NEF:
            case SUPPLY_NE_FLOWER:
                sb.append(" " + EVENT_CODE + ": " + EVENTS[EVENT_CODE]);
                if (isException) {
                    sb.append("\n\tMethod before exception: " + getStackTrace()[0]);
                    sb.append("\n\tCause: " + getCause());
                }
                System.out.println(sb);
                break;
            default:
                System.out.println("\tNot correct event code");
        }
    }

    public void reflectClassInfo() {   //reflection
        sb = new StringBuilder();
        Class c = this.getClass();
        sb.append("Class: " + c.getName());
        sb.append("\nModifiers:");
        int mods = c.getModifiers();
        if (Modifier.isPublic(mods)) {
            sb.append(" public");
        }
        if (Modifier.isPrivate(mods)) {
            sb.append(" private");
        }
        if (Modifier.isProtected(mods)) {
            sb.append(" protected");
        }
        if (Modifier.isAbstract(mods)) {
            sb.append(" abstract");
        }
        if (Modifier.isFinal(mods)) {
            sb.append(" final");
        }
        sb.append("\nConstructors:");
        Constructor constructors[] = c.getConstructors();
        for(Constructor constr : constructors) {
            sb.append("\n\t" + constr);
        }
        sb.append("\nFields:");
        Field fields[] = c.getFields();
        for(Field field : fields) {
            sb.append("\n\t" + field);
        }
        sb.append("\nMethods:");
        Method methods[] = c.getMethods();
        for(Method method : methods) {
            sb.append("\n\t" + method);
        }
        sb.append("\nAnnotations:");
        Annotation annotations[] = c.getAnnotations();
        for(Annotation ann : annotations) {
            sb.append(" " + ann);
        }
        System.out.println(sb);
    }
}
