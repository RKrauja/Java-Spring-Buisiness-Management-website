package com.reiniskr.registrationloginspring.web;

import com.reiniskr.registrationloginspring.model.Product;
import com.reiniskr.registrationloginspring.model.User;
import com.reiniskr.registrationloginspring.repository.ProductRepository;
import com.reiniskr.registrationloginspring.repository.UserRepository;
import com.reiniskr.registrationloginspring.service.ProductService;
import com.reiniskr.registrationloginspring.service.ProductServiceImpl;
import com.reiniskr.registrationloginspring.web.dto.ProductDto;
import com.reiniskr.registrationloginspring.web.dto.UserLoginDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import static java.lang.Math.round;

@Controller
public class MainController {

    boolean UserLoggedIn = false;

    private User currentUser = new User();

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Value("${PVN-price}")
    double PVN;


    private ProductService productService;

    public MainController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @Autowired
    UserRepository userRepository;


    @ModelAttribute("user")
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @GetMapping("/")
    public String home() {
        return "/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        System.out.println("Current user id:" + currentUser.getId() + currentUser.isAdmin());
        System.out.println(PVN);
        return "login";
    }

    @PostMapping("/login")
    public String loginUserAccount(@ModelAttribute("user") UserLoginDto loginDto, HttpServletResponse response) {
        System.out.println("Entered email: " + loginDto.getEmail());
        System.out.println("Entered password: " + loginDto.getPassword());
        User existingUser = userRepository.findByEmail(loginDto.getEmail());
        if (existingUser != null) {
            if (existingUser.getPassword().equals(loginDto.getPassword())) {
                UserLoggedIn = true;
                currentUser = existingUser;
                return "redirect:/dashboard";
            }
        }
        Cookie cookie = new Cookie("username", "toms");

        return "redirect:/login?error";
    }

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        ;
        if (currentUser.getId() == null)
            return "redirect:/login";
        List<Product> products = productRepository.findAll();

        model.addAttribute("products", addPVNtoProducts(products));
        model.addAttribute("user", currentUser);
        return "Dashboard";
    }

    public Map<Product, Double> addPVNtoProducts(List<Product> products) {
        Map<Product, Double> ProductPriceWithPVNmap = new HashMap<>();
        for (Product prod : products) {
            double PVNprice = round(prod.getPrice() * prod.getStoredAmount() * (1 + PVN));
            ProductPriceWithPVNmap.put(prod, PVNprice);
        }
        return ProductPriceWithPVNmap;
    }

    @PostMapping("/dashboard")
    public String getFromDashboard(@ModelAttribute("product") ProductDto productDto) {
        return "login";
    }


    @GetMapping("/adminDashboard")
    public String showAdminDashboard(Model model) {
        if (!currentUser.isAdmin() || currentUser.getId() == null)
            return "redirect:/login";
        List<Product> products = productRepository.findAll();

        model.addAttribute("products", addPVNtoProducts(products));
        model.addAttribute("newProd", new ProductDto());


        return "AdminDashboard";

    }

    @PostMapping("/adminDashboard")
    public String loginUserAccount() {
        if (currentUser.getId() == null)
            return "redirect:/login";
//        System.out.println("Product name: "+ productDto.getName());
//        productRepository.save(productDto);
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/editProd/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productRepository.findById((long) id);
        Product test = product.get();
        System.out.println("Optional produkts:" + product);
        System.out.println("Produkts:" + test);
        model.addAttribute("CurrentProd", test);
        return "editProductForm";
    }

    @PostMapping("/editProd/{id}")
    public String SubmitEdit(@ModelAttribute("CurrentProd") Product updatedProduct) {
        if (!currentUser.isAdmin() || currentUser.getId() == null)
            return "redirect:/login";
        Optional<Product> ExistingProductOptional = productRepository.findById(updatedProduct.getId());
        Product ExistingProduct = ExistingProductOptional.get();

        ExistingProduct.setName(updatedProduct.getName());
        ExistingProduct.setPrice(updatedProduct.getPrice());
        ExistingProduct.setStoredAmount(updatedProduct.getStoredAmount());

        productRepository.save(ExistingProduct);
//        System.out.println("Updated product name:" + product.getName());
        return "redirect:/dashboard";

    }

    @GetMapping("deleteProd/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (!currentUser.isAdmin() || currentUser.getId() == null)
            return "redirect:/login";
        productRepository.deleteById(id);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model) {
        if (!currentUser.isAdmin() || currentUser.getId() == null)
            return "redirect:/login";
        model.addAttribute("newProd", new ProductDto());
        return "addNewProduct";
    }

    @PostMapping("/addNewProduct")
    public String addnewProductSubmit(@ModelAttribute("newProd") ProductDto productDto) {
        if (!currentUser.isAdmin() || currentUser.getId() == null)
            return "redirect:/login";
        productService.save(productDto);
        return "redirect:/adminDashboard";
    }
}