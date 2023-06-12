package com.lmq.servlet;

import com.lmq.pojo.User;
import com.lmq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet",urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if(action.equals("login")){
            login(request,response);
        }else if(action.equals("register")){
            register(request,response);
        }
    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //1.获得表单信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.验证表单信息的合法性
        if(username.equals("")||password.equals("")){
            request.getRequestDispatcher("admin/login.jsp").forward(request,response);
            return;
        }
        //将数据封装到user对象中
        User user = new User();
        user.setStudentname(username);
        user.setPwd(password);
        //创建UserService对象
        UserService userService = new UserService();
        boolean flag = userService.validate(user);
        //获取session中的验证码
        String token =(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = request.getParameter("code");
        if(token!=null&&token.equalsIgnoreCase(code)){
            if(flag){
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                response.sendRedirect("admin/index.html");
            }else {
                request.setAttribute("msg","用户名或密码错误！");
                request.getRequestDispatcher("error.jsp").forward(request,response);

            }
        }else {
            request.setAttribute("msg","验证码错误,请刷新页面重新登录！");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }

    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                    /*
        1.获取表单信息
        2.验证表单信息的合法性
        3.封装表单数据到User中
        4.调用UserService里面的service方法验证，返回true或false
        5.ture---》注册成功--跳转到login.jsp
        6.false--->注册失败，请求转发到error.jsp显示错误信息
         */
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //1.获取表单信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String date = request.getParameter("birthday");
        String email = request.getParameter("email");
////        2.验证表单信息的合法性
        if(username.equals("")||password==password2&&date.equals("")||date.equals("")||email.equals("")){
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }
//        3.封装表单数据到User中
        User user = new User();
        user.setStudentname(username);
        user.setPwd(password);

        // 4.调用UserService里面的service方法验证，返回true或false
        UserService userService = new UserService();
        boolean regist = userService.registerfind(user);
        if(regist){
            //成功，---》login.jsp
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("login.jsp");
        }else{
            request.setAttribute("msg1","用户名和密码已存在！");
            request.getRequestDispatcher("error2.jsp").forward(request,response);
        }
    }

}


