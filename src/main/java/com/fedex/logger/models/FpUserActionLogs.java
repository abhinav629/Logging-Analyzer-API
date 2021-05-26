package com.fedex.logger.models;

import com.fedex.logger.enums.ACTION_TYPES;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "FP_USER_ACTION_LOGS")
public class FpUserActionLogs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FP_USER_ACTION_LOGID", nullable = false, columnDefinition = "int default 1")
    private int fpUserActionLogId;

    @Column(name = "FLT_NBR")
    private Integer flightNumber; // Flight Number

    @Column(name = "FLT_DT")
    private Date flightDate; // Flight Date

    @Column(name = "LEG_NBR")
    private Integer legNumber; // Leg Number

    @Column(name = "ITERATION_NBR")
    private Integer iterationNumber; // Iteration Number

    @Column(name = "UID")
    @NotNull(message = "User ID Can't Be Null")
    private String userId; // User ID

    @Column(name = "ACTN_TYPE")
    @NotNull(message = "Action Type Can't Be Null")
    private ACTION_TYPES actionType; // Action Type

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ACTN_DESC")
    private String actionDescription; // Action Description

    @Column(name = "PREV_VALUE")
    private String previousValue; // Previous Value

    @Column(name = "CUR_VALUE")
    private String currentValue; // Current Value

    @Column(name = "VALUE_TYPE")
    private String valueType; // Int, String etc.

    @Column(name = "FLD_REF")
    private String fieldReference; // Current Value's Field Reference

    @Column(name = "SESS_TMSTP")
    @NotNull(message = "Session Timestamp Can't Be Null")
    private Timestamp sessionTimestamp; //  Session Login Timestamp

    @Column(name = "ACTN_TMSTP")
    @CreationTimestamp
    private Timestamp actionTimestamp; // Action Timestamp

    @Column(name = "USR_BRW_IF")
    private String userBrowserInformation; // User Browser Information ( User agent )

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFpUserActionLogId() {
        return fpUserActionLogId;
    }

    public void setFpUserActionLogId(int fpUserActionLogId) {
        this.fpUserActionLogId = fpUserActionLogId;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Integer getLegNumber() {
        return legNumber;
    }

    public void setLegNumber(Integer legNumber) {
        this.legNumber = legNumber;
    }

    public Integer getIterationNumber() {
        return iterationNumber;
    }

    public void setIterationNumber(Integer iterationNumber) {
        this.iterationNumber = iterationNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ACTION_TYPES getActionType() {
        return actionType;
    }

    public void setActionType(ACTION_TYPES actionType) {
        this.actionType = actionType;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getFieldReference() {
        return fieldReference;
    }

    public void setFieldReference(String fieldReference) {
        this.fieldReference = fieldReference;
    }

    public Timestamp getSessionTimestamp() {
        return sessionTimestamp;
    }

    public void setSessionTimestamp(Timestamp sessionTimestamp) {
        this.sessionTimestamp = sessionTimestamp;
    }

    public Timestamp getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(Timestamp actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }

    public String getUserBrowserInformation() {
        return userBrowserInformation;
    }

    public void setUserBrowserInformation(String userBrowserInformation) {
        this.userBrowserInformation = userBrowserInformation;
    }
}
