package com.cart.servlet;

import com.cart.model.CartItem;
import com.cart.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/cart.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "view";
        }
        HttpSession session = request.getSession();
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        if (action.equals("add")) {
            String productCode =
                    request.getParameter("productCode");
            Product product =
                    getProduct(productCode);
            if (product != null) {
                CartItem existingItem = null;
                for (CartItem item : cart) {
                    if (item.getProduct()
                            .getCode()
                            .equals(productCode)) {
                        existingItem = item;
                        break;
                    }
                }
                if (existingItem != null) {
                    existingItem.setQuantity(
                            existingItem.getQuantity() + 1
                    );
                } else {
                    CartItem item =
                            new CartItem(product, 1);
                    cart.add(item);
                }
            }
        }
        else if (action.equals("update")) {
            String productCode =
                    request.getParameter("productCode");
            String quantityString =
                    request.getParameter("quantity");
            try {
                int quantity =
                        Integer.parseInt(quantityString);
                if (quantity <= 0) {
                    cart.removeIf(
                            item -> item.getProduct()
                                    .getCode()
                                    .equals(productCode)
                    );
                } else {
                    for (CartItem item : cart) {
                        if (item.getProduct()
                                .getCode()
                                .equals(productCode)) {
                            item.setQuantity(quantity);
                            break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                // Ignore invalid input
            }
        }

        else if (action.equals("remove")) {
            String productCode =
                    request.getParameter("productCode");
            cart.removeIf(
                    item -> item.getProduct()
                            .getCode()
                            .equals(productCode)
            );
        }
        session.setAttribute("cart", cart);

        response.sendRedirect(
                request.getContextPath() + "/cart"
        );
    }

    private Product getProduct(String code) {
        return switch (code) {
            case "8601" ->
                    new Product(
                            "8601",
                            "86 (the band) - True Life Songs and Pictures",
                            14.95
                    );
            case "pf01" ->
                    new Product(
                            "pf01",
                            "Paddlefoot - The first CD",
                            12.95
                    );
            case "pf02" ->
                    new Product(
                            "pf02",
                            "Paddlefoot - The second CD",
                            14.95
                    );
            case "jr01" ->
                    new Product(
                            "jr01",
                            "Joe Rut - Genuine Wood Grained Finish",
                            14.95
                    );
            default -> null;
        };
    }
}