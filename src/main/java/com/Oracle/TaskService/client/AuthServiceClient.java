package com.Oracle.TaskService.client;

import com.Oracle.TaskService.infra.config.FeignClientConfig;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "auth-service",
    url = "http://oraclekairo.com/api/auth/",
    configuration = FeignClientConfig.class)
public interface AuthServiceClient {

  @GetMapping("/users/{userId}")
  Map<String, Object> getUserById(@PathVariable("userId") Long userId);

  @GetMapping("/teams/{teamId}")
  Map<String, Object> getTeamById(@PathVariable("teamId") Long teamId);

  @GetMapping("/teams/{teamId}/users")
  List<Map<String, Object>> getUsersByTeamId(@PathVariable("teamId") Long teamId);

  @GetMapping("/userteams/{userId}")
  List<Map<String, Object>> getTeamsByUserId(@PathVariable("userId") Long userId);
}
