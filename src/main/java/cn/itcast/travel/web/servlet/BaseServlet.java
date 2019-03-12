package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/2/12 15:14
 * @Description: TODO
 **/
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        //  /user/add
        String uri = req.getRequestURI();
        //获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        //获取方法对象Method
        //谁调用我，this就代表谁
        try {
            Method method = this.getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void writeValue(HttpServletResponse response, Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json; charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    public String writeValueAsString(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(obj);
    }
}
