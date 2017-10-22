package com.eshop.servlet;

import com.eshop.dao.CheckDao;
import com.eshop.model.MemberBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChkServlet")
public class ChkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String temp = request.getParameter("action");
        String name = request.getParameter("loginName");
        HttpSession session=request.getSession();
        boolean flag = false;
        if (temp.equals("chkMemName")) {
            flag = new CheckDao().chkRegName(name);
            if (flag) {
                pw.println("<font color='green'>该用户名可以注册</font>");
                pw.flush();
                pw.close();
            } else {
                pw.println("<font color='red'>该用户名已注册</font>");
                pw.flush();
                pw.close();
            }
        } else if (temp.equals("chkUserName")) {
            flag = new CheckDao().chkUserName(name);
            if (flag) {
                pw.println("<font color='green'>该用户名可以注册</font>");
                pw.flush();
                pw.close();
            } else {
                pw.println("<font color='red'>该用户名已注册</font>");
                pw.flush();
                pw.close();
            }
        } else if (temp.equals("chkProType")) {
            String cate = request.getParameter("cateName");
            flag = new CheckDao().chkCateName(cate);
            if (flag) {
                pw.println("<font color='green'>OK</font>");
                pw.flush();
                pw.close();
            } else {
                pw.println("<font color='red'>该图书类型已存在</font>");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("chk")){
            String url = request.getParameter("name");
            MemberBean mem= (MemberBean) session.getAttribute("mBean");
            if (mem!=null){
                if (url.equals("cart")){
                    request.getRequestDispatcher("/cart?action=list&id="+mem.getMemid()).forward(request,response);
                }else if (url.equals("order")){
                    request.getRequestDispatcher("/myorder?action=list&id="+mem.getMemid()).forward(request,response);
                }else if (url.equals("modi")){
                    response.sendRedirect("modiReg.jsp");
                }
            }else {
                response.sendRedirect("sorry.jsp");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
