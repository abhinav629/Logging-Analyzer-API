package com.fedex.logger.controller;

import com.fedex.logger.models.FpUserActionLogs;
import com.fedex.logger.services.UserActionLogsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class UserActionLogsController {
    private final UserActionLogsService userActionLogsService;

    public UserActionLogsController(UserActionLogsService userActionLogsService) {
        this.userActionLogsService = userActionLogsService;
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<FpUserActionLogs>> getAllLogs() {
        return new ResponseEntity(userActionLogsService.getAllLogs(), HttpStatus.OK);
    }

    @GetMapping({"/{logID}"})
    public ResponseEntity<FpUserActionLogs> getLog(@PathVariable(required = true, name = "logID") Integer logID) {
        return new ResponseEntity(userActionLogsService.getLog(logID), HttpStatus.OK);
    }

    @GetMapping({"/user/{userID}"})
    public ResponseEntity<Map<Timestamp, List<FpUserActionLogs>>> getAllSessionWiseLogsForAUser(@PathVariable(required = true, name = "userID") String userID) {
        return new ResponseEntity(userActionLogsService.getAllSessionWiseLogsForAUser(userID), HttpStatus.OK);
    }

    @GetMapping({"/flight"})
    public ResponseEntity<List<FpUserActionLogs>> getAllLogsForAFlight(@RequestParam(name="flightNumber") Integer flightNumber,
                                                                       @RequestParam(name="legNumber") Integer legNumber,
                                                                       @RequestParam(name="flightDate") Date flightDate) {
        return new ResponseEntity(userActionLogsService.getAllLogsForAFlight(flightNumber, legNumber, flightDate), HttpStatus.OK);
    }

    @PostMapping({"/add"})
    public ResponseEntity<FpUserActionLogs> addLog(@Valid @RequestBody FpUserActionLogs fp_user_action_log) {
        return new ResponseEntity(userActionLogsService.addLog(fp_user_action_log), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{logId}"})
    public ResponseEntity<String> deleteLog(@PathVariable(required = true, name = "logId") Integer logId) {
        userActionLogsService.deleteLog(logId);
        return new ResponseEntity("LOG DELETED SUCCESSFULLY", HttpStatus.CREATED);
    }
}
