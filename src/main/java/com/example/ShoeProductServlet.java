package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ShoeProductServlet", value = "/shoe-product")
public class ShoeProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String unitPriceStr = request.getParameter("unitPrice");
        String quantityStr = request.getParameter("quantity");

        double unitPrice = Double.parseDouble(unitPriceStr);
        int quantity = Integer.parseInt(quantityStr);

        double subtotal = unitPrice * quantity;

        double discountPercent = 0.0;
        if (quantity >= 10) {
            discountPercent = 10.0;      // 10+ items → 10%
        } else if (quantity >= 5) {
            discountPercent = 5.0;       // 5–9 items → 5%
        }

        double discountAmount = subtotal * (discountPercent / 100.0);
        double total = subtotal - discountAmount;

        // put values on request so JSP can show them
        request.setAttribute("productName", productName);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("quantity", quantity);
        request.setAttribute("discountPercent", discountPercent);
        request.setAttribute("discountAmount", discountAmount);
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("total", total);

        // forward to result JSP
        request.getRequestDispatcher("/shoeResult.jsp")
                .forward(request, response);
    }
}
