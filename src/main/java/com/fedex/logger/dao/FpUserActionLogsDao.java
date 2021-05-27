package com.fedex.logger.dao;

import com.fedex.logger.models.FpUserActionLogs;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import static com.fedex.logger.rowMappers.FpUserActionLogsRowMapper.fpUserActionLogsRowMapper;

@Repository
public class FpUserActionLogsDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String FETCH_LOG = "SELECT * from FP_USER_ACTION_LOGS where FP_USER_ACTION_LOGID = :FP_USER_ACTION_LOGID";

    private final String DELETE_LOG = "DELETE from FP_USER_ACTION_LOGS where FP_USER_ACTION_LOGID = :FP_USER_ACTION_LOGID";

    private final String FETCH_ALL_LOGS = "SELECT * from FP_USER_ACTION_LOGS";

    private final String FETCH_ALL_LOGS_FOR_USER = "SELECT * from FP_USER_ACTION_LOGS where UID = :UID order by ACTN_TMSTP";

    private final String FETCH_ALL_LOGS_PER_FLIGHT = "SELECT * from FP_USER_ACTION_LOGS where " +
            "FLT_NBR = :FLT_NBR and " +
            "LEG_NBR = :LEG_NBR and " +
            "FLT_DT = :FLT_DT";

    private final String INSERT_SQL = "INSERT INTO FP_USER_ACTION_LOGS(" +
            "FLT_NBR, FLT_DT, LEG_NBR, ITERATION_NBR, UID, ACTN_TYPE," +
            "ACTN_DESC, PREV_VALUE, CUR_VALUE, VALUE_TYPE, FLD_REF, SESS_TMSTP, USR_BRW_IF, STATUS) " +
            "values(:FLT_NBR, :FLT_DT, :LEG_NBR, :ITERATION_NBR, :UID, :ACTN_TYPE, " +
            ":ACTN_DESC, :PREV_VALUE, :CUR_VALUE, :VALUE_TYPE, :FLD_REF, :SESS_TMSTP, :USR_BRW_IF, :STATUS)";


    public FpUserActionLogsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<FpUserActionLogs> getAllLogs(){
        return namedParameterJdbcTemplate.query(FETCH_ALL_LOGS, fpUserActionLogsRowMapper);
    }

    public FpUserActionLogs getLog(int FP_USER_ACTION_LOGID){
        Map parameters = new HashMap<> ();
        parameters.put("FP_USER_ACTION_LOGID", FP_USER_ACTION_LOGID);
        try {
            return namedParameterJdbcTemplate.queryForObject(FETCH_LOG, parameters, fpUserActionLogsRowMapper);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<FpUserActionLogs> getAllLogsForAUser(String uid){
        Map parameters = new HashMap();
        parameters.put("UID", uid);
        return namedParameterJdbcTemplate.query(FETCH_ALL_LOGS_FOR_USER, parameters, fpUserActionLogsRowMapper);
    }

    public Map<Timestamp, List<FpUserActionLogs>> getAllSessionWiseLogsForAUser(String uid){
        List<FpUserActionLogs> logsForUser = this.getAllLogsForAUser(uid);

        // Segregating Logs According To Sessions They Belong To
        Map<Timestamp, List<FpUserActionLogs>> sessionsMap = new TreeMap<>();
        for(FpUserActionLogs fp_user_action_logsMapper : logsForUser){
            if(!sessionsMap.containsKey(fp_user_action_logsMapper.getSessionTimestamp())){
                sessionsMap.put(fp_user_action_logsMapper.getSessionTimestamp(), new ArrayList<> ());
            }
            sessionsMap.get(fp_user_action_logsMapper.getSessionTimestamp()).add(fp_user_action_logsMapper);
        }
        return sessionsMap;
    }

    public List<FpUserActionLogs> getAllLogsForAFlight(Integer FLT_NBR, Integer LEG_NBR, Date FLT_DT){
        if(FLT_NBR != null && LEG_NBR != null && FLT_DT != null) {
            Map parameters = new HashMap();
            parameters.put("FLT_NBR", FLT_NBR);
            parameters.put("LEG_NBR", LEG_NBR);
            parameters.put("FLT_DT", FLT_DT);
            return namedParameterJdbcTemplate.query(FETCH_ALL_LOGS_PER_FLIGHT, parameters, fpUserActionLogsRowMapper);
        }
        return new ArrayList<> ();
    }

    private SqlParameterSource sourceMap(FpUserActionLogs fp_user_action_log){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("FLT_NBR", fp_user_action_log.getFlightNumber())
                .addValue("FLT_DT", fp_user_action_log.getFlightDate())
                .addValue("LEG_NBR", fp_user_action_log.getLegNumber())
                .addValue("ITERATION_NBR", fp_user_action_log.getIterationNumber())
                .addValue("UID", fp_user_action_log.getUserId())
                .addValue("ACTN_TYPE", fp_user_action_log.getActionType().toString())
                .addValue("ACTN_DESC", fp_user_action_log.getActionDescription())
                .addValue("PREV_VALUE", fp_user_action_log.getPreviousValue())
                .addValue("CUR_VALUE", fp_user_action_log.getCurrentValue())
                .addValue("VALUE_TYPE", fp_user_action_log.getValueType())
                .addValue("FLD_REF", fp_user_action_log.getFieldReference())
                .addValue("SESS_TMSTP", fp_user_action_log.getSessionTimestamp())
                .addValue("USR_BRW_IF", fp_user_action_log.getUserBrowserInformation())
                .addValue("STATUS", fp_user_action_log.getStatus());
        return parameters;
    }

    public FpUserActionLogs addLog(FpUserActionLogs fp_user_action_log) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = this.sourceMap(fp_user_action_log);
        namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
        fp_user_action_log.setFpUserActionLogId(holder.getKey().intValue());
        return fp_user_action_log;
    }

    public List<FpUserActionLogs> bulkAddLog(List<FpUserActionLogs> fp_user_action_logs) {
        SqlParameterSource[] bulkParameters = new SqlParameterSource[fp_user_action_logs.size()];
        final int[] counter = {0};
        fp_user_action_logs.forEach(fpUserActionLogs -> {
            bulkParameters[counter[0]++] = this.sourceMap(fpUserActionLogs);
        });
        namedParameterJdbcTemplate.batchUpdate(INSERT_SQL, bulkParameters);
        return fp_user_action_logs;
    }

    public void deleteLog(Integer FP_USER_ACTION_LOGID){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("FP_USER_ACTION_LOGID", FP_USER_ACTION_LOGID);
        namedParameterJdbcTemplate.update(DELETE_LOG, parameters);
    }

}
