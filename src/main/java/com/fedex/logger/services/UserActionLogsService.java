package com.fedex.logger.services;

import com.fedex.logger.models.FpUserActionLogs;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface UserActionLogsService {
    List<FpUserActionLogs> getAllLogs();

    FpUserActionLogs getLog(Integer logID);

    Map<Timestamp, List<FpUserActionLogs>> getAllSessionWiseLogsForAUser(String userID);

    List<FpUserActionLogs> getAllLogsForAFlight(Integer flightNumber, Integer legNumber, Date flightDate);

    FpUserActionLogs addLog(FpUserActionLogs fp_user_action_log);

    void deleteLog(Integer flightId);
}
