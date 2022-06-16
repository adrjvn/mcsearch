package me.adrjan.mcsearch.bots.data.account;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum Privilege {
    DETAILS,
    PASSWORDS,
    INTERNATIONAL,
    PRIVATE_DATA,
    API,
    GUILD_SEARCH;

    public static final Set<Privilege> VALUES = Collections.unmodifiableSet(EnumSet.allOf(Privilege.class));
}