package com.zakcorp.design_patterns.creational.abstract_factory_pattern.factory;

public class GUIAbstractFactory {
    public static GUIFactory createFactory( String osType ) {
        if (osType.equals("windows")) {
            return new WindowsFactory();
        } else if (osType.equals("mac")) {
            return new MacOSFactory();
        }
        return new MacOSFactory();
    }
}
