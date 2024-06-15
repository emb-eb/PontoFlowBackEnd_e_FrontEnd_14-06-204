package com.pontoflowatual.pfw.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum UserRoles {

    USUARIO(0),
    ADMINISTRADOR(1),
    GESTOR(2),
    SECRETARIA(3),
    DIRETOR(4);

    private final int roleId;
    private static final Map<Integer, UserRoles> ROLE_MAP = new HashMap<>();

    static {
        for (UserRoles role : UserRoles.values()) {
            ROLE_MAP.put(role.roleId, role);
        }
    }

    UserRoles(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    @JsonCreator
    public static UserRoles fromInt(int value) {
        return ROLE_MAP.get(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
