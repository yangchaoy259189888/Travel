package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.IUserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/2/12 15:11
 * @Description: TODO
 **/
@WebServlet(name = "UserServlet", urlPatterns = "/user/*")
public class UserServlet extends BaseServlet {
    public void registUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //马上清除session，保证验证码只能用一次
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            // 验证码错误
            // 注册失败
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！请刷新重试······");

            //将info对象序列化为json
            String json = writeValueAsString(info);
            //将json数据写回客户端
            //设置content-type
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            return;
        }

        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service完成注册
        IUserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if (flag) {
            // 注册成功
            info.setFlag(true);
        } else {
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！！！");
        }

        //将info对象序列化为json
        String json = writeValueAsString(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //马上清除session，保证验证码只能用一次
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            // 验证码错误
            // 注册失败
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！请刷新重试······");

            //将info对象序列化为json
            String json = writeValueAsString(info);
            //将json数据写回客户端
            //设置content-type
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        // 封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 调用service查询
        IUserService service = new UserServiceImpl();
        User u = service.login(user);

        ResultInfo info = new ResultInfo();
        //判断查询出来的用户对象是否是null
        if (u == null) {
            //用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        // 判断是否激活
        if (u != null && !u.getStatus().equals("Y")) {
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活!");
        }
        // 判断登录成功
        if (u != null && u.getStatus().equals("Y")) {
            //设置session，将来要显示在页面头部的欢迎信息上
            request.getSession().setAttribute("user", u);
            //登录成功
            info.setFlag(true);
        }

        //响应数据
        writeValue(response, info);
    }

    public void findUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");

        //响应数据
        writeValue(response, user);
    }

    public void exitServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //2.跳转登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    public void activeUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String code = request.getParameter("code");

        if (code != null) {
            //2.调用service完成激活
            IUserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            //3.判断标记
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
