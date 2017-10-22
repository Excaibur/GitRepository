package com.eshop.servlet;

import com.eshop.dao.LevelWordDao;
import com.eshop.model.LevelWordBean;
import com.eshop.model.MemberBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "LevelWordServlet")
public class LevelWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp = request.getParameter("action");
        MemberBean mBean = (MemberBean) request.getSession().getAttribute("mBean");
        PrintWriter pw = response.getWriter();
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
            request.setAttribute("num", new LevelWordDao().countPage());
            request.setAttribute("endpage", new LevelWordDao().pageNum());
            request.getRequestDispatcher("leaveword.jsp").forward(request, response);
        } else if (temp.equals("add")) {
            LevelWordBean lwBean = new LevelWordBean();
            String wordTitle = request.getParameter("wordTitle");
            String content = request.getParameter("content");
            lwBean.setMemid(mBean.getMemid());
            lwBean.setWtitle(wordTitle);
            lwBean.setWcontent(content);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lwBean.setWdate(new Date());
            int i = new LevelWordDao().addLevelWord(lwBean);
            if (i > 0) {
                pw.println("<script>alert('留言提交成功!');location.href='/lword?action=list';</script>");
                pw.flush();
                pw.close();
            } else {
                pw.println("<script>alert('留言提交失败!');location.href='/lword?action=list';</script>");
                pw.flush();
                pw.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
