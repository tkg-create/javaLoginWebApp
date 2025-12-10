package com.example;

import entity.Product;
import entity.ProductDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        // use your existing DAO
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Get all customers from the DB via DAO
        List<Product> products = productDAO.getAll();
        request.setAttribute("products", products);

        // Forward to JSP
        RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Simple “create” operation
        String Name = request.getParameter("Name");
        String Description = request.getParameter("Description");
        String Color = request.getParameter("Color");
        String Size = request.getParameter("Size");
        String Price = request.getParameter("Price");

        if (Name != null && Description != null && Color != null && Size != null && Price != null
                && !Name.isBlank() && !Description.isBlank() && !Color.isBlank() && !Size.isBlank() && !Price.isBlank()) {

            double priceValue = Double.parseDouble(Price);

            Product product = new Product(0, Name, Description, Color, Size, priceValue);
            product.setName(Name);
            product.setDescription(Description);
            product.setColor(Color);
            product.setSize(Size);
            product.setPrice(priceValue);

            productDAO.insert(product);    // uses your existing DAO
        }

        // Redirect to avoid form resubmission
        response.sendRedirect(request.getContextPath() + "/products");
    }
}

