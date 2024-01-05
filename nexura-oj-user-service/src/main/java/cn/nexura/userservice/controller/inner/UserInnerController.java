package cn.nexura.userservice.controller.inner;

import cn.nexura.model.entity.User;
import cn.nexura.model.vo.UserVO;
import cn.nexura.serviceclient.service.UserFeignClient;
import cn.nexura.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 内部调用
 * @author PeiYP
 * @since 2024年01月05日 10:12
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {


    @Resource
    private UserService userService;

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/get/vo")
    @Override
    public UserVO getUserVO(@RequestBody User user) {
        return userService.getUserVO(user);
    }


    /**
     * 根据id获取用户
     * @since 2024/1/5 9:13
     * @param userId
     * @return
     */
    @GetMapping("/get/id")
    @Override
    public User getUserById(@RequestParam("userId") Long userId) {
        return userService.getById(userId);
    }


    /**
     * 获取用户列表
     * @since 2024/1/5 9:14
     * @param idList
     * @return
     */
    @GetMapping("/get/ids")
    @Override
    public List<User> listByUserIds(@RequestParam("idList") Collection<Long> idList) {
        return userService.listByIds(idList);
    }

}
