package com.test.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Product;
import com.test.dao.ApplicationDao;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/search")
public class SearchServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//collect search string from the form
		String searchString = req.getParameter("search");

                
		//call DAO layer and get all products for search criteria
                ApplicationDao dao=new ApplicationDao();
		List<Product> products=dao.searchProducts(searchString);
		
		//write the products data back to the client browser
		String page=getHTMLString(req.getServletContext().getRealPath("/html/search.html"), products);
                resp.getWriter().write(page);
	}
	
	/**
	 * this methods reads the HTML template as a String, replaces placeholders
	 * with the values and returns the entire page as a String
	 * @param filePath
	 * @param products
	 * @return
	 * @throws IOException
	 */
	public String getHTMLString(String filePath, List<Product> products) {
            StringBuffer buffer=new StringBuffer();
            BufferedReader reader=null;
                try {
                        reader = new BufferedReader(new FileReader(filePath));
                        String line="";
                    while((line=reader.readLine())!=null){
                        buffer.append(line);
                    }
                    reader.close();
                    String page=buffer.toString();
                    page=MessageFormat.format(page, products.get(0).getProductImgPath(),products.get(1).getProductImgPath(),products.get(2).getProductImgPath(),
                                                    products.get(0).getProductName(),products.get(1).getProductName(),products.get(2).getProductName(),0);
                    return page;

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            return "can't read items from DB or file!";
        }
                
	
}
