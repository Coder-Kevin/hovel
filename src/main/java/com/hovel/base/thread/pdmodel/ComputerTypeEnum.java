package com.hovel.base.thread.pdmodel;

import lombok.Getter;

@Getter
public enum ComputerTypeEnum {

    NOTE_BOOK("0", "笔记本"),
    DESKTOP_COMPUTER("1", "台式机");
    private String typeCode;

    private String typeName;

    ComputerTypeEnum(String typeCode, String typeName) {
        this.typeCode = typeCode;

        this.typeName = typeName;
    }
}
