package com.eshop.servlet;

import com.eshop.dao.CartDao;
import com.eshop.dao.MemLvDao;
import com.eshop.dao.OrderDao;
import com.eshop.dao.ProTypeDao;
import com.eshop.model.MemLvBean;
import com.eshop.model.OrderBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
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
        if (status==0||status==2) {
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
                List<OrderBean> list=new OrderDao().getOrderAll(page);
                request.setAttribute("list",list);
                request.setAttribute("page", page);
                request.setAttribute("endpage", new OrderDao().pageNum());
                request.getRequestDispatcher("/Admin/adminOrder.jsp").forward(request,response);
            }else if (temp.equals("selId")){
                Long oid=Long.parseLong(request.getParameter("oid"));
                OrderBean order=new OrderDao().getOrderInfoById(oid);
                MemLvBean lvBean =new MemLvDao().queryByMemId(order.getMemid());
                order.setLvname(lvBean.getLvname());
                order.setPrecent(lvBean.getLvpercent());
                request.setAttribute("order",order);
                request.getRequestDispatcher("/Admin/OrderInfo.jsp").forward(request,response);
            }else if (temp.equals("del")){
                Long oid=Long.parseLong(request.getParameter("oid"));
                int i=new OrderDao().delOrderById(oid);
                int page=Integer.parseInt(request.getParameter("curpage"));
                if (i>0){
                    pw.println("<script>alert('删除成功');location.href='/order?action=list&curpage="+page+"'</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('删除失败');location.href='/order?action=list&curpage="+page+"'</script>");
                    pw.flush();
                    pw.close();
                }
            }else if (temp.equals("update")){
                Long oid=Long.parseLong(request.getParameter("oid"));
                int i=new OrderDao().updateOrderStatus(oid);
                int page=Integer.parseInt(request.getParameter("curpage"));
                if (i>0){
                    pw.println("<script>location.href='/order?action=list&curpage="+page+"'</script>");
                    pw.flush();
                    pw.close();
                }else {
                    pw.println("<script>alert('订单更新失败！');location.href='/order?action=list&curpage="+page+"'</script>");
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
