package com.example.customer.controller;

import com.example.library.dto.CategoryDto;
import com.example.library.dto.ProductDto;
import com.example.library.model.Category;
import com.example.library.model.Customer;
import com.example.library.model.Product;

import com.example.library.model.ShoppingCart;
import com.example.library.serivce.CategoryService;
import com.example.library.serivce.CustomerService;
import com.example.library.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/products")
    public String products(Model model , Principal principal, HttpSession session){

        if(principal !=null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItems", cart.getTotalItems());
            session.setAttribute("totalPrice", cart.getTotalPrices());
        }
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categories", categories);
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getAllProducts();
        List<Product> listViewProducts = productService.listViewProducts();
//        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/product/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model,Principal principal, HttpSession session){


        if(principal !=null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItems", cart.getTotalItems());
            session.setAttribute("totalPrice", cart.getTotalPrices());
        }
        Product product = productService.getProductById(id);
        Long categoryId = product.getCategory().getId();
        List<Product>  products = productService.getRelatedProducts(categoryId);
        model.addAttribute("product", product);
        model.addAttribute("products", products);


        return "product-detail";
    }

    @GetMapping("/products-in-category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId ,Model model,Principal principal, HttpSession session){


        if(principal !=null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItems", cart.getTotalItems());
            session.setAttribute("totalPrice", cart.getTotalPrices());
        }
        Category category = categoryService.findById(categoryId);
        System.out.println(category);
        List<Category> categories = categoryService.findAllByActivated();
//        List<CategoryDto> categories = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getProductsInCategory(categoryId);
        model.addAttribute("category",category);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/high-price")
    public String filterHighPrice(Model model , Principal principal, HttpSession session){

        if(principal !=null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItems", cart.getTotalItems());
            session.setAttribute("totalPrice", cart.getTotalPrices());
        }


        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterHighPrice();
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "shop";
    }


    @GetMapping("/low-price")
    public String filterLowPrice(Model model,Principal principal, HttpSession session){
        if(principal !=null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItems", cart.getTotalItems());
            session.setAttribute("totalPrice", cart.getTotalPrices());
        }
        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterLowPrice();
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "shop";
    }


    @GetMapping("/search-result/{pageNo}")
    public String searchProducts(@PathVariable("pageNo")int pageNo,
                                 @RequestParam("keyword") String keyword,
                                 Model model,
                                 Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<ProductDto> products = productService.searchProducts(pageNo, keyword);
        model.addAttribute("title", "Search Result");
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        return "shop";
    }
}

