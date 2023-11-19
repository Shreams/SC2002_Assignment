package org.example.extractexcel;

import org.example.users.enums.UserType;

public interface IExtractExcel {


    String getName();
    String getEmail();
    String getFacultyName();

    UserType getUserType();

    String getUserID();
}

