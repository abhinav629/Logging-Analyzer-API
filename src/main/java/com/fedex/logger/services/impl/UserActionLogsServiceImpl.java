package com.fedex.logger.services.impl;

import com.fedex.logger.dao.FpUserActionLogsDao;
import com.fedex.logger.dto.FlightSearchDTO;
import com.fedex.logger.models.FpUserActionLogs;
import com.fedex.logger.services.UserActionLogsService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class UserActionLogsServiceImpl implements UserActionLogsService {
    private final FpUserActionLogsDao fpUserActionLogs_dao;

    public UserActionLogsServiceImpl(FpUserActionLogsDao fpUserActionLogs_dao) {
        this.fpUserActionLogs_dao = fpUserActionLogs_dao;
    }

    @Override
    public List<FpUserActionLogs> getAllLogs(){
        return fpUserActionLogs_dao.getAllLogs();
    }

    @Override
    public FpUserActionLogs getLog(Integer logID){
        return fpUserActionLogs_dao.getLog(logID);
    }

    @Override
    public Map<Timestamp, List<FpUserActionLogs>> getAllSessionWiseLogsForAUser(String userID){
        return fpUserActionLogs_dao.getAllSessionWiseLogsForAUser(userID);
    }

    @Override
    public List<FpUserActionLogs> getAllLogsForAFlight(Integer flightNumber, Integer legNumber, Date flightDate){
        return fpUserActionLogs_dao.getAllLogsForAFlight(flightNumber, legNumber, flightDate);
    }

    @Override
    public FpUserActionLogs addLog(FpUserActionLogs fp_user_action_log){
        return fpUserActionLogs_dao.addLog(fp_user_action_log);
    }

    @Override
    public void deleteLog(Integer logId) {
        fpUserActionLogs_dao.deleteLog(logId);
    }
}
