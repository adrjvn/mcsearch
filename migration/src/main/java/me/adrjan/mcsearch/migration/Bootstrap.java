package me.adrjan.mcsearch.migration;

import me.adrjan.mcsearch.api.Module;
import me.adrjan.mcsearch.migration.module.ModuleFactory;

public class Bootstrap {

    public static void main(String[] args) {
        Module module = new ModuleFactory().get(Constans.MODULE_MODE);
        module.start();
        System.exit(0);
    }
}