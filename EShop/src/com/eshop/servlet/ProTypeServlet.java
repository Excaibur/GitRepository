package com.eshop.servlet;

import com.eshop.dao.ProTypeDao;
import com.eshop.model.ProTypeBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProTypeServlet")
public class ProTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp=request.getParameter("action");
        int status= (int) request.getSession().getAttribute("status");
        HttpSession session=request.getSession();
        PrintWriter pw=response.getWriter();
        if (status==0||status==1) {
            if (temp.equals("list")) {
                String curpage = request.getParameter("curpage");
                int page = 1;
                if (curpage != null) {
                    page = Integer.parseInt(curpage);
                    if (page <= 0) {
                        page = 1;
                    } else if (page >= new ProTypeDao().pageNum()) {
                        page = new ProTypeDao().pageNum();
                    }
                }
                List<ProTypeBean> list = new ProTypeDao().queryAll(page);
                request.setAttribute("list", list);
                request.setAttribute("page",page);
                request.setAttribute("endpage",new ProTypeDao().pageNum());
                request.getRequestDispatcher("/Admin/adminCate.jsp").forward(request, response);
            }else if (temp.equals("selId")){
                int id=Integer.parseInt(request.getParameter("id"));
                ProTypeBean typeBean=new ProTypeDao().queryById(id);
                request.setAttribute("ptype",typeBean);
                request.getRequestDispatcher("/Admin/adminModiCate.jsp").forward(request,response);
            }else if (temp.equals("del")){
                int id=Integer.parseInt(request.getParameter("id"));
                int i=new ProTypeDao().delById(id);
                if (i>0){
                    pw.println("<script>alert('删除成功！');location.href='/ptype?action=list';</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('删除失败！');history.go(-1);</script>");
                    pw.flush();
                    pw.close();
                }
            }else if (temp.equals("update")){
                int id=Integer.parseInt(request.getParameter("id"));
                String name=request.getParameter("cateName");
                String desc=request.getParameter("cateDesc");
                ProTypeBean typeBean=new ProTypeBean();
                typeBean.setTypeid(id);
                typeBean.setTypename(name);
                typeBean.setTypedesc(desc);
                int i=new ProTypeDao().update(typeBean);
                if (i>0){
                    pw.println("<script>alert('修改成功！');location.href='/ptype?action=list';</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('修改失败！');location.href='/ptype?action=list';</script>");
                    pw.flush();
                    pw.close();
                }
            }else if (temp.equals("add")){
                String name=request.getParameter("cateName");
                String desc=request.getParameter("cateDesc");
                ProTypeBean typeBean=new ProTypeBean();
                typeBean.setTypename(name);
                typeBean.setTypedesc(desc);
                int i=new ProTypeDao().addUser(typeBean);
                if (i>0){
                    pw.println("<script>alert('添加成功！');location.href='/ptype?action=list';</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('添加失败！');location.href='/Admin/adminAddCate.jsp';</script>");
                    pw.flush();
                    pw.close();
                }
            }
        }else {
            response.sendRedirect("/Admin/sorry.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
