package cn.nexura.serviceclient.service;

import cn.nexura.common.ErrorCode;
import cn.nexura.constant.UserConstant;
import cn.nexura.exception.BusinessException;
import cn.nexura.model.entity.User;
import cn.nexura.model.enums.UserRoleEnum;
import cn.nexura.model.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 用户服务
 *
 * @author peiYP
 */
@FeignClient(name = "nexura-oj-user-service", path = "/api/user/inner")
public interface UserFeignClient {


    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    default User getLoginUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    default boolean isAdmin(User user) {
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/get/vo")
    UserVO getUserVO(@RequestBody User user);


    /**
     * 根据id获取用户
     * @since 2024/1/5 9:13
     * @param userId
     * @return
     */
    @GetMapping("/get/id")
    User getUserById(@RequestParam("userId") Long userId);


    /**
     * 获取用户列表
     * @since 2024/1/5 9:14
     * @param idList
     * @return
     */
    @GetMapping("/get/ids")
    List<User> listByUserIds(@RequestParam("idList") Collection<Long> idList);

}
