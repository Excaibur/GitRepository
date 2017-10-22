package com.eshop.servlet;

import com.eshop.dao.ProTypeDao;
import com.eshop.dao.ProductDao;
import com.eshop.model.ProTypeBean;
import com.eshop.model.ProductBean;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp = request.getParameter("action");
        int status = (int) request.getSession().getAttribute("status");
        PrintWriter pw = response.getWriter();
        if (status == 0 || status == 1) {
            if (temp.equals("list")) {
                String curpage = request.getParameter("curpage");
                int page = 1;
                if (curpage != null) {
                    page = Integer.parseInt(curpage);
                    if (page <= 0) {
                        page = 1;
                    } else if (page >= new ProductDao().pageNum()) {
                        page = new ProductDao().pageNum();
                    }
                }
                List<ProductBean> list = new ProductDao().queryAll(page);
                request.setAttribute("list", list);
                request.setAttribute("page", page);
                request.setAttribute("endpage", new ProductDao().pageNum());
                request.getRequestDispatcher("/Admin/adminMer.jsp").forward(request, response);
            } else if (temp.equals("selId")) {
                int id = Integer.parseInt(request.getParameter("id"));
                ProductBean proBean = new ProductDao().queryById(id);
                request.setAttribute("proBean", proBean);
                String fg = request.getParameter("fg");
                if (fg.equals("view")) {
                    request.getRequestDispatcher("/Admin/adminViewMer.jsp").forward(request, response);
                } else if (fg.equals("update")) {
                    List<ProTypeBean> type = new ProTypeDao().query();
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("/Admin/adminModiMer.jsp").forward(request, response);
                }
            } else if (temp.equals("del")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int i = new ProductDao().delById(id);
                if (i > 0) {
                    pw.println("<script>alert('删除成功！');location.href='/pro?action=list';</script>");
                    pw.flush();
                    pw.close();
                } else {
                    pw.println("<script>alert('删除失败！');history.go(-1);</script>");
                    pw.flush();
                    pw.close();
                }
            } else if (temp.equals("add")) {
                // 新建一个SmartUpload对象
                SmartUpload su = new SmartUpload();
                // 上传初始化
                ServletConfig config = this.getServletConfig();
                su.initialize(config, request, response);
                // 设定上传限制
                // 1.限制每个上传文件的最大长度。
                // su.setMaxFileSize(10000);
                // 2.限制总上传数据的长度。
                // su.setTotalMaxFileSize(20000);
                // 3.设定允许上传的文件（通过扩展名限制）,仅允许jpg,bmp,png,jpeg,gif文件。
                su.setAllowedFilesList("jpg,bmp,png,jpeg,gif");
                String path = "";
                try {
                    // 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。
                    su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
                    // 上传文件
                    su.upload();
                    // 将上传文件全部保存到指定目录，需要先在在Web应用的根目录下，创建一个upload目录
                    /*int count = su.save("/Picture");
                    System.out.println(count+"个文件上传成功！<br>");*/
                    // 另存到以WEB应用程序的根目录为文件根目录的目录下
                    File smartFile = su.getFiles().getFile(0);
//                    System.out.println("name:"+smartFile.getFileName());
                    path = "/Picture/" + new Date().getTime() + smartFile.getFileName() + "";
                    smartFile.saveAs(path, su.SAVE_VIRTUAL);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (SmartUploadException e) {
                    e.printStackTrace();
                }
                // 利用Request对象获取参数之值
                String category = su.getRequest().getParameter("category");
                String merName = su.getRequest().getParameter("merName");
                String merModel = su.getRequest().getParameter("merModel");
                String price = su.getRequest().getParameter("price");
                String sprice = su.getRequest().getParameter("sprice");
                String merDesc = su.getRequest().getParameter("merDesc");
                String manufacturer = su.getRequest().getParameter("manufacturer");
                String leaveFactoryDate = su.getRequest().getParameter("leaveFactoryDate");
                ProductBean pBean = new ProductBean();
                if (category != null && !category.equals("")) {
                    int typeid = Integer.parseInt(category);
                    pBean.setTypeid(typeid);
                }
                pBean.setProname(merName);
                pBean.setPromodel(merModel);
                if (price != null && !price.equals("")) {
                    double proPrice = Double.parseDouble(price);
                    pBean.setPrice(proPrice);
                }
                if (sprice != null && !sprice.equals("")) {
                    double proSprice = Double.parseDouble(sprice);
                    pBean.setSprice(proSprice);
                }
                pBean.setProdesc(merDesc);
                pBean.setProfactory(manufacturer);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (leaveFactoryDate != null && !leaveFactoryDate.equals("")) {
                    Date facDate = null;
                    try {
                        facDate = sdf.parse(leaveFactoryDate);
                        pBean.setFacdate(facDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                pBean.setPropic(path);
                int i = new ProductDao().addProduct(pBean);
                if (i > 0) {
                    pw.println("<script>alert('添加成功！');location.href='/pro?action=list';</script>");
                    pw.flush();
                    pw.close();
                } else {
                    pw.println("<script>alert('添加失败！');location.href='/pro?action=list';</script>");
                    pw.flush();
                    pw.close();
                }
                // 逐一提取上传文件信息，同时可保存文件。
                /*for (int i=0;i<su.getFiles().getCount();i++) {
                    com.jspsmart.upload.File file = su.getFiles().getFile(i);
                    // 若文件不存在则继续
                    if (file.isMissing()) continue;
                    // 显示当前文件信息
                    *//*out.println("<TABLE BORDER=1>");
                    out.println("<TR><TD>表单项名（FieldName）</TD><TD>"
                            + file.getFieldName() + "</TD></TR>");
                    out.println("<TR><TD>文件长度（Size）</TD><TD>" +
                            file.getSize() + "</TD></TR>");
                    out.println("<TR><TD>文件名（FileName）</TD><TD>"
                            + file.getFileName() + "</TD></TR>");
                    out.println("<TR><TD>文件扩展名（FileExt）</TD><TD>"
                            + file.getFileExt() + "</TD></TR>");
                    out.println("<TR><TD>文件全名（FilePathName）</TD><TD>"
                            + file.getFilePathName() + "</TD></TR>");
                    out.println("</TABLE><BR>");*//*
                }*/
                // 将文件另存
                // file.saveAs("/upload/" + myFile.getFileName());
                // 另存到以WEB应用程序的根目录为文件根目录的目录下
/*                File smartFile = su.getFiles().getFile(0);
                try {
                    smartFile.saveAs("/Picture/" + smartFile.getFileName(),su.SAVE_VIRTUAL);
                } catch (SmartUploadException e) {
                    e.printStackTrace();
                }*/
                // 另存到操作系统的根目录为文件根目录的目录下
                // file.saveAs("c:\\temp\\" + myFile.getFileName(),su.SAVE_PHYSICAL);
            } else if (temp.equals("query")) {
                List<ProTypeBean> type = new ProTypeDao().query();
                request.setAttribute("type", type);
                request.getRequestDispatcher("/Admin/adminAddMer.jsp").forward(request, response);
            } else if (temp.equals("update")) {
                // 新建一个SmartUpload对象
                SmartUpload su = new SmartUpload();
                // 上传初始化
                ServletConfig config = this.getServletConfig();
                su.initialize(config, request, response);
                su.setAllowedFilesList("jpg,bmp,png,jpeg,gif");
                String path = "";
                try {
                    // 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。
                    su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
                    // 上传文件
                    su.upload();
                    // 另存到以WEB应用程序的根目录为文件根目录的目录下
                    File smartFile = su.getFiles().getFile(0);
                    if (smartFile.isMissing()){
                        path=su.getRequest().getParameter("pic");
                    }else {
                        path = "/Picture/" + new Date().getTime() + smartFile.getFileName() + "";
                        smartFile.saveAs(path, su.SAVE_VIRTUAL);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (SmartUploadException e) {
                    e.printStackTrace();
                }
                // 利用Request对象获取参数之值
                String category = su.getRequest().getParameter("category");
                int id = Integer.parseInt(request.getParameter("id"));
                String merName = su.getRequest().getParameter("merName");
                String merModel = su.getRequest().getParameter("merModel");
                String price = su.getRequest().getParameter("price");
                String sprice = su.getRequest().getParameter("sprice");
                String merDesc = su.getRequest().getParameter("merDesc");
                String manufacturer = su.getRequest().getParameter("manufacturer");
                String leaveFactoryDate = su.getRequest().getParameter("leaveFactoryDate");
                ProductBean pBean = new ProductBean();
                if (category != null && !category.equals("")) {
                    int typeid = Integer.parseInt(category);
                    pBean.setTypeid(typeid);
                }
                pBean.setProid(id);
                pBean.setProname(merName);
                pBean.setPromodel(merModel);
                if (price != null && !price.equals("")) {
                    double proPrice = Double.parseDouble(price);
                    pBean.setPrice(proPrice);
                }
                if (sprice != null && !sprice.equals("")) {
                    double proSprice = Double.parseDouble(sprice);
                    pBean.setSprice(proSprice);
                }
                pBean.setProdesc(merDesc);
                pBean.setProfactory(manufacturer);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (leaveFactoryDate != null && !leaveFactoryDate.equals("")) {
                    Date facDate = null;
                    try {
                        facDate = sdf.parse(leaveFactoryDate);
                        pBean.setFacdate(facDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                pBean.setPropic(path);
                int i = new ProductDao().update(pBean);
                if (i > 0) {
                    pw.println("<script>alert('修改成功！');location.href='/pro?action=list';</script>");
                    pw.flush();
                    pw.close();
                } else {
                    pw.println("<script>alert('修改失败！');location.href='/Admin/adminModiMer.jsp';</script>");
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
