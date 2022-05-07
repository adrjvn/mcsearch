package me.adrjan.mcsearch.migration.module;

import me.adrjan.mcsearch.api.Factory;
import me.adrjan.mcsearch.api.Module;
import me.adrjan.mcsearch.migration.injection.InjectorModule;
import me.adrjan.mcsearch.migration.migrate.MigrationModule;

public class ModuleFactory implements Factory<Module> {

    public Module get(ModuleMode mode) {
        Module module;
        switch (mode) {
            case INJECTION -> module = new InjectorModule();
            case MIGRATION -> module = new MigrationModule();
            default -> module = null;
        }
        return module;
    }
}