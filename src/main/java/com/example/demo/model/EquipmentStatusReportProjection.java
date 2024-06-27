package com.example.demo.model;

import java.util.Date;

public interface EquipmentStatusReportProjection {

    String getSerialNumber();
    String getCurrentStatus();
    String getLocation();
    Date getDateStart();
    Date getDateEnd();
}
