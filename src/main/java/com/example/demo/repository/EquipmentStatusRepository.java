package com.example.demo.repository;

import com.example.demo.model.EquipmentStatus;
import com.example.demo.model.EquipmentStatusReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentStatusRepository extends JpaRepository<EquipmentStatus, Long> {

    @Query("SELECT e.serial_number AS serialNumber, es.currentStatus AS currentStatus, es.location AS location, es.dateStart AS dateStart, es.dateEnd AS dateEnd " +
            "FROM EquipmentStatus es " +
            "JOIN es.equipment e " +
            "WHERE es.dateStart >= :startDate AND es.dateEnd <= :endDate")
    List<EquipmentStatusReportProjection> findByDateRange(String startDate, String endDate);

    @Query("SELECT e.serial_number AS serialNumber, es.currentStatus AS currentStatus, es.location AS location, es.dateStart AS dateStart, es.dateEnd AS dateEnd " +
            "FROM EquipmentStatus es " +
            "JOIN es.equipment e " +
            "WHERE e.serial_number = :serialNumber")
    List<EquipmentStatusReportProjection> findBySerialNumber(String serialNumber);

    @Query("SELECT e.serial_number AS serialNumber, es.currentStatus AS currentStatus, es.location AS location, es.dateStart AS dateStart, es.dateEnd AS dateEnd " +
            "FROM EquipmentStatus es " +
            "JOIN es.equipment e " +
            "WHERE es.dateStart >= :startDate AND es.dateEnd <= :endDate AND e.serial_number = :serialNumber")
    List<EquipmentStatusReportProjection> findByDateRangeAndSerialNumber(String startDate, String endDate, String serialNumber);

    @Query("SELECT e.serial_number AS serialNumber, es.currentStatus AS currentStatus, es.location AS location, es.dateStart AS dateStart, es.dateEnd AS dateEnd " +
            "FROM EquipmentStatus es " +
            "JOIN es.equipment e")
    List<EquipmentStatusReportProjection> findAllReports();
}
