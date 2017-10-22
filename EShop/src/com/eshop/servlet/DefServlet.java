package com.eshop.servlet;

import com.eshop.dao.DefDao;
import com.eshop.dao.MemLvDao;
import com.eshop.dao.ProTypeDao;
import com.eshop.dao.ProductDao;
import com.eshop.model.MemLvBean;
import com.eshop.model.ProTypeBean;
import com.eshop.model.ProductBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DefServlet")
public class DefServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp=request.getParameter("action");
        HttpSession session=request.getSession();
        if (temp.equals("load")) {
            List<ProTypeBean> type = new ProTypeDao().query();
            List<ProductBean> spros = new DefDao().querySpro(1);
            List<ProductBean> pros = new DefDao().queryPro(1);
            session.setAttribute("type", type);
            session.setAttribute("spros", spros);
            session.setAttribute("pros", pros);
            request.getRequestDispatcher("default.jsp").forward(request, response);
        }else if (temp.equals("like")){
            String name=request.getParameter("qKey");
            int typeid=Integer.parseInt(request.getParameter("category"));
            if (name==null||name.equals("商品关键字")){
                name="";
            }
            int count=new DefDao().countLikePage(name,typeid);
            int num=new DefDao().pageLikeNum(name,typeid);
            String curpage = request.getParameter("curpage");
            int page = 1;
            if (curpage != null) {
                page = Integer.parseInt(curpage);
            }
//            int page=Integer.parseInt( request.getParameter("curpage"));
            List<ProductBean> list=new DefDao().queryLike(page,name,typeid);
            List<ProTypeBean> type = new ProTypeDao().query();
            request.setAttribute("list",list);
            request.setAttribute("name",name);
            request.setAttribute("typeid",typeid);
            request.setAttribute("curpage",page);
            request.setAttribute("count",count);
            request.setAttribute("num",num);
            request.getRequestDispatcher("search.jsp").forward(request,response);
        }else if (temp.equals("selById")){
            int id=Integer.parseInt(request.getParameter("id"));
            ProductBean pBean=new ProductDao().queryById(id);
            request.setAttribute("pBean",pBean);
            request.getRequestDispatcher("merInfo.jsp").forward(request,response);
        }
        else if (temp.equals("memlv")){
            int id=Integer.parseInt(request.getParameter("lvid"));
            MemLvBean lvBean=new MemLvDao().queryById(id);
            request.setAttribute("lv",lvBean);
            request.getRequestDispatcher("checkOrder.jsp").forward(request,response);
        }else if (temp.equals("more")){
            String pro = request.getParameter("pro");
            String curpage = request.getParameter("curpage");
            int count=new DefDao().pageNum(pro);
            int page = 1;
            if (curpage != null) {
                page = Integer.parseInt(curpage);
                if (page <= 0) {
                    page = 1;
                } else if (page >= count) {
                    page = count;
                }
            }
            request.setAttribute("curpage",page);
            request.setAttribute("count",count);
            if (pro.equals("pro")){
                int num=new DefDao().countPage();
                request.setAttribute("num",num);
                List<ProductBean> pros = new DefDao().queryPro(page);
                request.setAttribute("pros", pros);
                request.getRequestDispatcher("merchandise.jsp").forward(request, response);
            }else if (pro.equals("spro")){
                int num=new DefDao().countSproPage();
                request.setAttribute("num",num);
                List<ProductBean> spro = new DefDao().querySpro(page);
                request.setAttribute("pros", spro);
                request.getRequestDispatcher("sMerchandise.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
