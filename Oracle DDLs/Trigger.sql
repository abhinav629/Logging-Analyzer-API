-- Trigger DLL
CREATE OR REPLACE TRIGGER log_trig
    BEFORE INSERT ON FP_USER_ACTION_LOGS
    FOR EACH ROW

BEGIN
    SELECT log_seq.NEXTVAL
    INTO   :new.FP_USER_ACTION_LOGID
    FROM   dual;
END;