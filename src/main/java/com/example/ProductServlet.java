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
import java.util.Optional;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null || action.isBlank() || action.equals("read")) {
            // Show product list
            List<Product> products = productDAO.getAll();
            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("/product_read.jsp");
            rd.forward(request, response);
            return;
        }

        if ("edit".equals(action)) {
            // Load single product and forward to update page (prefilled form)
            String idStr = request.getParameter("id");
            try {
                int id = Integer.parseInt(idStr);
                Optional<Product> opt = productDAO.get(id);
                Product product = (opt == null) ? null : opt.orElse(null);
                if (product != null) {
                    request.setAttribute("product", product);
                } else {
                    request.setAttribute("error", "Product with ID " + id + " not found.");
                }
            } catch (NumberFormatException ex) {
                request.setAttribute("error", "Invalid product id.");
            }
            RequestDispatcher rd = request.getRequestDispatcher("/product_update.jsp");
            rd.forward(request, response);
            return;
        }

        // Unknown GET action -> redirect to list
        response.sendRedirect(request.getContextPath() + "/products?action=read");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                handleCreate(request);
                break;
            case "update":
                handleUpdate(request);
                break;
            case "delete":
                handleDelete(request);
                break;
            default:
                // if no action, treat as create (backwards compatible) or redirect
                handleCreate(request);
                break;
        }

        // After any POST operation, redirect to product list to avoid resubmission
        response.sendRedirect(request.getContextPath() + "/products?action=read");
    }

    private void handleCreate(HttpServletRequest request) {
        String Name = request.getParameter("Name");
        String Description = request.getParameter("Description");
        String Color = request.getParameter("Color");
        String Size = request.getParameter("Size");
        String Price = request.getParameter("Price");

        if (Name != null && Description != null && Color != null && Size != null && Price != null
                && !Name.isBlank() && !Description.isBlank() && !Color.isBlank() && !Size.isBlank() && !Price.isBlank()) {
            try {
                double priceValue = Double.parseDouble(Price);
                Product product = new Product(0, Name, Description, Color, Size, priceValue);
                productDAO.insert(product);
            } catch (NumberFormatException ex) {
                System.err.println("Price parse error: " + ex.getMessage());
            }
        }
    }

    private void handleUpdate(HttpServletRequest request) {
        String idStr = request.getParameter("ID");
        String Name = request.getParameter("Name");
        String Description = request.getParameter("Description");
        String Color = request.getParameter("Color");
        String Size = request.getParameter("Size");
        String Price = request.getParameter("Price");

        try {
            int id = Integer.parseInt(idStr);
            double priceValue = Double.parseDouble(Price);
            Product product = new Product(id, Name, Description, Color, Size, priceValue);
            productDAO.update(product);
        } catch (NumberFormatException ex) {
            System.err.println("Update parse error: " + ex.getMessage());
        }
    }

    private void handleDelete(HttpServletRequest request) {
        String idStr = request.getParameter("ID");
        try {
            int id = Integer.parseInt(idStr);
            Optional<Product> opt = productDAO.get(id);
            Product product = (opt == null) ? null : opt.orElse(null);
            if (product != null) {
                productDAO.delete(product);
            } else {
                System.err.println("Product to delete not found: " + id);
            }
        } catch (NumberFormatException ex) {
            System.err.println("Delete parse error: " + ex.getMessage());
        }
    }
}
