package com.training.mappers;

import com.training.entities.HistoryEntity;
import com.training.models.History;

/**
 * Utility class that provides static methods for mapping HistoryEntity objects to History objects
 * and History objects to HistoryEntity objects.
 *
 * @see HistoryEntity
 * @see History
 */
public final class HistoryMapper {

    private HistoryMapper() {
    }

    /**
     * Maps History object to HistoryEntity object.
     *
     * @param history History object to be mapped
     * @return HistoryEntity object - result of mapping
     */
    public static HistoryEntity mapModelToEntity(History history) {
        if (history == null)
            return null;
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setPrimaryDriver(DriverMapper.mapModelToEntity(history.getPrimaryDriver()));
        historyEntity.setCoDriver(DriverMapper.mapModelToEntity(history.getCoDriver()));
        historyEntity.setVehicle(VehicleMapper.mapModelToEntity(history.getVehicle()));
        return historyEntity;
    }

    /**
     * Maps HistoryEntity object to History object.
     *
     * @param historyEntity HistoryEntity object to be mapped
     * @return History object - result of mapping
     */
    public static History mapEntityToModel(HistoryEntity historyEntity) {
        if (historyEntity == null)
            return null;
        History history = new History();
        history.setPrimaryDriver(DriverMapper.mapEntityToModel(historyEntity.getPrimaryDriver()));
        history.setCoDriver(DriverMapper.mapEntityToModel(historyEntity.getCoDriver()));
        history.setVehicle(VehicleMapper.mapEntityToModel(historyEntity.getVehicle()));
        return history;
    }
}
