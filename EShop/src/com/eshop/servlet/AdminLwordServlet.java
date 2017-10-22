package com.eshop.servlet;

import com.eshop.dao.LevelWordDao;
import com.eshop.model.LevelWordBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdminLwordServlet")
public class AdminLwordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp = request.getParameter("action");
        int status = (int) request.getSession().getAttribute("status");
        PrintWriter pw = response.getWriter();
        if (status == 0 || status == 3) {
            if (temp.equals("list")) {
                String curpage = request.getParameter("curpage");
                int page = 1;
                if (curpage != null) {
                    page = Integer.parseInt(curpage);
                    if (page <= 0) {
                        page = 1;
                    } else if (page >= new LevelWordDao().pageNum()) {
                        page = new LevelWordDao().pageNum();
                    }
                }
                List<LevelWordBean> list = new LevelWordDao().queryAll(page);
                request.setAttribute("list", list);
                request.setAttribute("page", page);
                request.setAttribute("endpage", new LevelWordDao().pageNum());
                request.getRequestDispatcher("/Admin/adminWord.jsp").forward(request, response);
            }else if (temp.endsWith("selId")) {
                int id = Integer.parseInt(request.getParameter("id"));
                LevelWordBean lwBean = new LevelWordDao().queryById(id);
                request.setAttribute("lwBean", lwBean);
                request.getRequestDispatcher("/Admin/WordInfo.jsp").forward(request, response);
            }else if (temp.equals("del")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int i = new LevelWordDao().delById(id);
                String curpage = request.getParameter("curpage");
                if (i > 0) {
                    pw.println("<script>alert('删除成功！');location.href='/alword?action=list&curpage="+curpage+"';</script>");
                    pw.flush();
                    pw.close();
                } else {
                    pw.println("<script>alert('删除失败！');location.href='/alword?action=list&curpage="+curpage+"';</script>");
                    pw.flush();
                    pw.close();
                }
            }else if (temp.equals("upload")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String answerContent = request.getParameter("answerContent");
                int i = new LevelWordDao().uploadById(id,answerContent);
                if (i > 0) {
                    pw.println("<script>location.href='/alword?action=selId&id="+id+"';</script>");
                    pw.flush();
                    pw.close();
                } else {
                    pw.println("<script>location.href='/alword?action=selId&id="+id+"';</script>");
                    pw.flush();
                    pw.close();
                }
            }
        }else {
            response.sendRedirect("/Admin/sorry.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
