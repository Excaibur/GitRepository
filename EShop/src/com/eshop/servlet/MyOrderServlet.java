package com.eshop.servlet;

import com.eshop.dao.CartDao;
import com.eshop.dao.OrderDao;
import com.eshop.model.CartBean;
import com.eshop.model.MemberBean;
import com.eshop.model.OrderBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(name = "MyOrderServlet")
public class MyOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp=request.getParameter("action");
        HttpSession session=request.getSession();
        PrintWriter pw=response.getWriter();
        MemberBean memberBean= (MemberBean) session.getAttribute("mBean");
        if (temp.equals("add")&&memberBean!=null){
            String memName =request.getParameter("memName");
            String phone =request.getParameter("phone");
            String zip =request.getParameter("zip");
            String address =request.getParameter("address");
            OrderBean orderBean=new OrderBean();
            OrderDao od=new OrderDao();
            orderBean.setOrderid(od.getOrderId(memberBean));
            orderBean.setMemid(memberBean.getMemid());
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
                orderBean.setOrderdate(new Date());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            orderBean.setOrdername(memName);
            orderBean.setOrderphone(phone);
            orderBean.setOrderzip(zip);
            orderBean.setOrderaddress(address);
            orderBean.setOrderstatus(0);
            List<CartBean> cartList=new CartDao().queryAll(memberBean.getMemid());
            double total=od.addOrder(orderBean,cartList);
            if (total>=1){
                request.setAttribute("oid",orderBean.getOrderid());
                request.setAttribute("total",total);
                request.setAttribute("date",orderBean.getOrderdate().toLocaleString());
                request.getRequestDispatcher("submitOrder.jsp").forward(request,response);
            }else {
                pw.println("<script>alert('订单生成失败');location='/chk?action=chk&name=cart'</script>");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("list")&&memberBean!=null){
            int mid=memberBean.getMemid();
            List<OrderBean> list=new OrderDao().getOrderById(mid);
            request.setAttribute("list",list);
            request.getRequestDispatcher("Order.jsp").forward(request,response);
        }else if (temp.equals("selId")&&memberBean!=null){
            int mid=memberBean.getMemid();
            Long oid=Long.parseLong(request.getParameter("oid"));
            OrderBean order=new OrderDao().getOrderInfoById(oid);
            double precent=new CartDao().queryLv(mid);
            order.setPrecent(precent);
            request.setAttribute("order",order);
            request.getRequestDispatcher("OrderInfo.jsp").forward(request,response);
        }else if (temp.equals("del")&&memberBean!=null){
            Long oid=Long.parseLong(request.getParameter("oid"));
            int i=new OrderDao().delOrderById(oid);
            if (i>0){
                pw.println("<script>alert('删除成功');location='/chk?action=chk&name=order'</script>");
                pw.flush();
                pw.close();
            }else {
                pw.println("<script>alert('删除失败');location='/chk?action=chk&name=order'</script>");
                pw.flush();
                pw.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
