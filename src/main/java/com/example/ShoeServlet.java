package com.example;

import entity.Product;
import entity.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/sneaker")
public class ShoeServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("productId");

        // Load product info only if ID was submitted
        if (idParam != null && !idParam.isBlank()) {
            try {
                int id = Integer.parseInt(idParam);
                Optional<Product> opt = productDAO.get(id);

                if (opt != null && opt.isPresent()) {
                    Product p = opt.get();

                    request.setAttribute("productId", p.getID());
                    request.setAttribute("productName", p.getName());
                    request.setAttribute("productDescription", p.getDescription());
                    request.setAttribute("unitPrice", p.getPrice()); // overrides default
                } else {
                    request.setAttribute("error", "No product found with ID " + id);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Product ID.");
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/sneaker.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("productId");
        if (idParam != null && !idParam.isBlank()) {
            try {
                int id = Integer.parseInt(idParam);
                ProductDAO dao = new ProductDAO();
                Optional<Product> productOpt = dao.get(id);

                if (productOpt != null && productOpt.isPresent()) {
                    Product p = productOpt.get();

                    // Set product attributes for JSP
                    request.setAttribute("productId", p.getID());
                    request.setAttribute("productName", p.getName());
                    request.setAttribute("productDescription", p.getDescription());
                    request.setAttribute("productColor", p.getColor());
                    request.setAttribute("productSize", p.getSize());
                    request.setAttribute("unitPrice", p.getPrice());
                } else {
                    request.setAttribute("error", "No product found with ID: " + id);
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Product ID.");
            }
        }

        String qtyParam = request.getParameter("quantity");
        String unitPriceParam = request.getParameter("unitPrice");

        int quantity = 1;
        String error = null;
        double discount = 0.0;

        try {
            quantity = Integer.parseInt(qtyParam);
            if (quantity < 1) {
                error = "Quantity must be at least 1.";
            }
        } catch (Exception e) {
            error = "Invalid quantity.";
        }

        double unitPrice = 0.00;  // default price fallback

        try {
            // Use DB price when available
            unitPrice = Double.parseDouble(unitPriceParam);
        } catch (Exception ignored) { }

        if (error == null) {
            if (quantity >= 10) discount = 0.10;
            else if (quantity >= 5) discount = 0.05;

            double subtotal = unitPrice * quantity;
            double total = subtotal * (1.0 - discount);

            request.setAttribute("unitPrice", String.format("%.2f", unitPrice));
            request.setAttribute("quantity", quantity);
            request.setAttribute("discountPercent", (int) (discount * 100));
            request.setAttribute("totalPrice", String.format("%.2f", total));

        } else {
            request.setAttribute("error", error);
            request.setAttribute("quantity", qtyParam);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/sneaker.jsp");
        rd.forward(request, response);
    }
}
