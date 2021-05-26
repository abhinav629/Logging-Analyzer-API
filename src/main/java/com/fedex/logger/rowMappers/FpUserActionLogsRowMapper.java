package com.fedex.logger.rowMappers;

import com.fedex.logger.enums.ACTION_TYPES;
import com.fedex.logger.models.FpUserActionLogs;
import org.springframework.jdbc.core.RowMapper;

public final class FpUserActionLogsRowMapper {
    public static final RowMapper<FpUserActionLogs> fpUserActionLogsRowMapper = (resultSet, i) -> {
        FpUserActionLogs fpUserActionLog = new FpUserActionLogs();
        fpUserActionLog.setFpUserActionLogId(resultSet.getInt("FP_USER_ACTION_LOGID"));
        fpUserActionLog.setFlightNumber(resultSet.getInt("FLT_NBR"));
        fpUserActionLog.setFlightDate(resultSet.getDate("FLT_DT"));
        fpUserActionLog.setLegNumber(resultSet.getInt("LEG_NBR"));
        fpUserActionLog.setIterationNumber(resultSet.getInt("ITERATION_NBR"));
        fpUserActionLog.setUserId(resultSet.getString("UID"));
        fpUserActionLog.setActionType(ACTION_TYPES.valueOf(resultSet.getString("ACTN_TYPE")));
        fpUserActionLog.setActionDescription(resultSet.getString("ACTN_DESC"));
        fpUserActionLog.setPreviousValue(resultSet.getString("PREV_VALUE"));
        fpUserActionLog.setCurrentValue(resultSet.getString("CUR_VALUE"));
        fpUserActionLog.setValueType(resultSet.getString("VALUE_TYPE"));
        fpUserActionLog.setFieldReference(resultSet.getString("FLD_REF"));
        fpUserActionLog.setStatus(resultSet.getString("STATUS"));
        fpUserActionLog.setSessionTimestamp(resultSet.getTimestamp("SESS_TMSTP"));
        fpUserActionLog.setActionTimestamp(resultSet.getTimestamp("ACTN_TMSTP"));
        fpUserActionLog.setUserBrowserInformation(resultSet.getString("USR_BRW_IF"));
        return fpUserActionLog;
    };
}
