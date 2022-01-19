package plus.yuhaozhang.service.edu.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.yuhaozhang.commonUtils.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuh Z
 * @date 1/18/22
 */
@Api(value = "和登录相关的接口")
@RestController
@RequestMapping("user")
public class LoginController {
    @PostMapping("/login")
    public Result login() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","result");
        return Result.success(map);
    }
    @GetMapping("info")
    public Result getInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","zzz");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success(map);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.success();
    }

}
