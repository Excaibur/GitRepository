package com.eshop.servlet;

import com.eshop.dao.ManageDao;
import com.eshop.model.ManageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ManageServlet")
public class ManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp=request.getParameter("action");
        int status=5;
        try {
            status= (int) request.getSession().getAttribute("status");
        }catch (NullPointerException e){
            response.sendRedirect("/Admin/sorry.jsp");
        }
        HttpSession session=request.getSession();
        PrintWriter pw=response.getWriter();
        if (status==0||status==4) {
            if (temp.equals("list")) {
                String curpage = request.getParameter("curpage");
                int page = 1;
                if (curpage != null) {
                    page = Integer.parseInt(curpage);
                    if (page <= 0) {
                        page = 1;
                    } else if (page >= new ManageDao().pageNum()) {
                        page = new ManageDao().pageNum();
                    }
                }
                List<ManageBean> list = new ManageDao().queryAll(page);
                request.setAttribute("list", list);
                request.setAttribute("page",page);
                request.setAttribute("endpage",new ManageDao().pageNum());
                request.getRequestDispatcher("/Admin/adminUser.jsp").forward(request, response);
            }else if (temp.equals("selId")){
                int id=Integer.parseInt(request.getParameter("id"));
                ManageBean mBean=new ManageDao().queryById(id);
                request.setAttribute("manage",mBean);
                request.getRequestDispatcher("/Admin/adminModiUser.jsp").forward(request,response);
            }else if (temp.equals("del")){
                int id=Integer.parseInt(request.getParameter("id"));
                int i=new ManageDao().delById(id);
                if (i>0){
                    pw.println("<script>alert('删除成功！');location.href='/manage?action=list';</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('删除失败！');history.go(-1);</script>");
                    pw.flush();
                    pw.close();
                }
            }else if (temp.equals("update")){
                int id=Integer.parseInt(request.getParameter("id"));
                String name=request.getParameter("adminName");
                String user=request.getParameter("loginName");
                String pwd=request.getParameter("loginPwd");
                int type=Integer.parseInt(request.getParameter("adminType"));
                ManageBean mBean=new ManageBean();
                mBean.setMid(id);
                mBean.setMuser(user);
                mBean.setMname(name);
                mBean.setMpassword(pwd);
                mBean.setMstatus(type);
                int i=new ManageDao().update(mBean);
                if (i>0){
                    pw.println("<script>alert('修改成功！');location.href='/manage?action=list';</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('修改失败！');location.href='/manage?action=list';</script>");
                    pw.flush();
                    pw.close();
                }
            }
        }else {
            response.sendRedirect("/Admin/sorry.jsp");
        }
        if (status==0){
            if (temp.equals("add")){
                String name=request.getParameter("adminName");
                String user=request.getParameter("loginName");
                String pwd=request.getParameter("loginPwd");
                int type=Integer.parseInt(request.getParameter("adminType"));
                ManageBean mBean=new ManageBean();
                mBean.setMuser(user);
                mBean.setMname(name);
                mBean.setMpassword(pwd);
                mBean.setMstatus(type);
                int i=new ManageDao().addUser(mBean);
                if (i>0){
                    pw.println("<script>alert('添加成功！');location.href='/manage?action=list';</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('添加失败！');location.href='/Admin/adminAddUser.jsp';</script>");
                    pw.flush();
                    pw.close();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
