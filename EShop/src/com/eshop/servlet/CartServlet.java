package com.eshop.servlet;

import com.eshop.dao.CartDao;
import com.eshop.dao.ProductDao;
import com.eshop.model.CartBean;
import com.eshop.model.ProductBean;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String temp=request.getParameter("action");
        PrintWriter pw=response.getWriter();
        int i=0;
        if (temp.equals("add")){
            int proid=Integer.parseInt(request.getParameter("proid"));
            int mid=Integer.parseInt(request.getParameter("mid"));
            CartBean cartBean=new CartDao().queryById(mid,proid);
            double precent=new CartDao().queryLv(mid);
            if (cartBean.getPronum()>0){
                i=new CartDao().update(mid,proid,cartBean.getPronum()+1,precent);
            }else {
                ProductBean pro = new ProductDao().queryById(proid);
                i = new CartDao().addCart(mid, pro,precent);
            }
            if (i>0){
                pw.println("成功添加进购物车");
                pw.flush();
                pw.close();
            }else {
                pw.println("添加购物车失败");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("list")){
            int mid=Integer.parseInt(request.getParameter("id"));
            List<CartBean> List=new CartDao().queryAll(mid);
            double totalMoney=new CartDao().totalMoney(List);
            request.setAttribute("prolist",List);
            request.setAttribute("totalmoney",totalMoney);
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }else if (temp.equals("update")){
            int proid=Integer.parseInt(request.getParameter("proid"));
            int mid=Integer.parseInt(request.getParameter("mid"));
            int num=Integer.parseInt(request.getParameter("num"));
            double precent=new CartDao().queryLv(mid);
            i=new CartDao().update(mid,proid,num,precent);
            if (i>0){
                CartBean cart=new CartDao().queryById(mid,proid);
                List<CartBean> List=new CartDao().queryAll(mid);
                double totalMoney=new CartDao().totalMoney(List);
                cart.setTotalmoney(totalMoney);
                JSON date=JSONObject.fromObject(cart);
                pw.println(date);
                pw.flush();
                pw.close();
            }else {
                pw.println("服务器为响应");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("del")){
            int cid=Integer.parseInt(request.getParameter("cid"));
            i=new CartDao().delById(cid);
            if (i>0){
                pw.println("删除成功");
                pw.flush();
                pw.close();
            }else {
                pw.println("删除失败");
                pw.flush();
                pw.close();
            }
        }else if (temp.equals("delAll")){
            int mid=Integer.parseInt(request.getParameter("mid"));
            i=new CartDao().delAll(mid);
            if (i>0){
                pw.println("<script>alert('成功清空购物车');location='/chk?action=chk&name=cart'</script>");
                pw.flush();
                pw.close();
            }else {
                pw.println("<script>alert('清空购物车失败');location='/chk?action=chk&name=cart'</script>");
                pw.flush();
                pw.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
