package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sneaker")
public class ProductServlet extends HttpServlet {

    private static final double UNIT_PRICE = 120.00;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/sneaker.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String qtyParam = request.getParameter("quantity");
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

        if (error == null) {
            if (quantity >= 10) {
                discount = 0.10;
            } else if (quantity >= 5) {
                discount = 0.05;
            }

            double subtotal = UNIT_PRICE * quantity;
            double total = subtotal * (1.0 - discount);

            request.setAttribute("unitPrice", String.format("%.2f", UNIT_PRICE));
            request.setAttribute("quantity", quantity);
            request.setAttribute("discountPercent", (int) (discount * 100));
            request.setAttribute("totalPrice", String.format("%.2f", total));
        } else {
            request.setAttribute("error", error);
            request.setAttribute("quantity", qtyParam);
        }

        request.getRequestDispatcher("/sneaker.jsp").forward(request, response);
    }
}
