package com.eshop.servlet;

import com.eshop.dao.MemLvDao;
import com.eshop.dao.MemberDao;
import com.eshop.model.MemLvBean;
import com.eshop.model.MemberBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MemberServlet")
public class MemberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp=request.getParameter("action");
        PrintWriter pw=response.getWriter();
        if (temp.equals("add")){
            MemberBean mBean=new MemberBean();
            String memberName=request.getParameter("memberName");
            String loginName=request.getParameter("loginName");
            String reLoginPwd=request.getParameter("reLoginPwd");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");
            String zip=request.getParameter("zip");
            String email=request.getParameter("email");
            mBean.setMemname(memberName);
            mBean.setMemlogin(loginName);
            mBean.setMempass(reLoginPwd);
            mBean.setLvid(1);
            mBean.setMemphone(phone);
            mBean.setMemaddress(address);
            mBean.setMemzip(zip);
            mBean.setEmail(email);
            mBean.setRegdate(new Date());
            int i=new MemberDao().addMember(mBean);
            if(i>0){
                pw.println("<script>alert('注册成功!');location.href='default.jsp';</script>");
                pw.flush();
                pw.close();
            }else {
                pw.println("<script>alert('注册失败!');location.href='reg.jsp';</script>");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("update")){
            int id=Integer.parseInt(request.getParameter("id"));
            MemberBean mBean=new MemberBean();
            String memberName=request.getParameter("memberName");
            String loginName=request.getParameter("loginName");
            String reLoginPwd=request.getParameter("reLoginPwd");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");
            String zip=request.getParameter("zip");
            String email=request.getParameter("email");
            mBean.setMemid(id);
            mBean.setMemname(memberName);
            mBean.setMemlogin(loginName);
            mBean.setMempass(reLoginPwd);
            mBean.setMemphone(phone);
            mBean.setMemaddress(address);
            mBean.setMemzip(zip);
            mBean.setEmail(email);
            mBean.setRegdate(new Date());
            int i=new MemberDao().update(mBean);
            if (i>0){
                pw.println("<script>alert('修改成功！');location.href='/default.jsp';</script>");
                pw.flush();
                pw.close();
            }else {
                pw.println("<script>alert('修改失败！');location.href='/modiReg.jsp';</script>");
                pw.flush();
                pw.close();
            }
        }
        int status=5;
        try {
            status= (int) request.getSession().getAttribute("status");
        }catch (NullPointerException e){
            response.sendRedirect("/Admin/sorry.jsp");
        }
        if (status==0||status==3) {
            if (temp.equals("list")){
                String curpage = request.getParameter("curpage");
                int page = 1;
                if (curpage != null) {
                    page = Integer.parseInt(curpage);
                    if (page <= 0) {
                        page = 1;
                    } else if (page >= new MemberDao().pageNum()) {
                        page = new MemberDao().pageNum();
                    }
                }
                List<MemberBean> list = new MemberDao().queryAll(page);
                request.setAttribute("list", list);
                request.setAttribute("page",page);
                request.setAttribute("endpage",new MemberDao().pageNum());
                request.getRequestDispatcher("/Admin/adminMember.jsp").forward(request, response);
            }else if (temp.equals("selId")){
                int id=Integer.parseInt(request.getParameter("id"));
                MemberBean mBean=new MemberDao().queryById(id);
                List<MemLvBean> lvlist=new MemLvDao().queryAll();
                request.setAttribute("mBean",mBean);
                request.setAttribute("lvlist",lvlist);
                request.getRequestDispatcher("/Admin/MemberInfo.jsp").forward(request,response);
            }else if (temp.equals("del")){
                int id=Integer.parseInt(request.getParameter("id"));
                int i=new MemberDao().delById(id);
                if (i>0){
                    pw.println("<script>alert('删除成功！');location.href='/mem?action=list';</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('删除失败！');history.go(-1);</script>");
                    pw.flush();
                    pw.close();
                }
            }else if (temp.equals("updateLv")){
                int id=Integer.parseInt(request.getParameter("id"));
                MemberBean mBean=new MemberBean();
                String memberlevel=request.getParameter("memberlevel");
                mBean.setMemid(id);
                mBean.setLvid(Integer.parseInt(memberlevel));
                int i=new MemberDao().updateLv(mBean);
                if (i>0){
                    pw.println("修改成功!");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("修改失败！");
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
