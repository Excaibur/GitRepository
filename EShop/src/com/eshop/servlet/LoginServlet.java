package com.eshop.servlet;

import com.eshop.dao.LoginDao;
import com.eshop.model.MemberBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp=request.getParameter("action");
        HttpSession session=request.getSession();
        PrintWriter pw=response.getWriter();
        if (temp.equals("def")){
            String name=request.getParameter("loginName");
            String pwd=request.getParameter("loginPwd");
            MemberBean mBean=new LoginDao().defLogin(name,pwd);
            if (mBean!=null){
                session.setAttribute("mBean",mBean);
                response.sendRedirect("default.jsp");
            }else {
                pw.println("<script>alert('用户名或密码错误');location.href='default.jsp';</script>");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("admin")){
            String name=request.getParameter("loginName");
            String pwd=request.getParameter("loginPwd");
            int status=new LoginDao().adminLogin(name,pwd);
            if (status!=5){
                session.setAttribute("name",name);
                session.setAttribute("status",status);
                response.sendRedirect("Admin/adminIndex.jsp");
            }else {
                pw.println("<script>alert('用户名或密码错误');location.href='Admin/adminLogin.jsp';</script>");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("exit")){
            session.removeAttribute("name");
            session.removeAttribute("status");
            pw.println("<script>window.top.location.href='Admin/adminLogin.jsp';</script>");
            pw.flush();
            pw.close();
        }else if (temp.equals("out")){
            session.removeAttribute("mBean");
            response.sendRedirect("index.htm");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
