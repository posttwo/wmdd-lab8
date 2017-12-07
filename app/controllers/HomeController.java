package controllers;

import play.mvc.*;

import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.swing.text.StyledEditorKit.ForegroundAction;

// Import model classes
import models.*;

// Import views
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    // declare a private FormFactory instance
    private FormFactory formFactory;
    
    // Inject an instance of FormFactory it into the controller via its constructor
    @Inject
    public HomeController(FormFactory f){
        this.formFactory = f;
    }
    public Result index() {
        List<Product> productList = Product.findAll(); 
        return ok(index.render(productList));
    }
    public Result customers(){
        List<Customer> customerList = Customer.findAll();
        return ok(customers.render(customerList));
    }

    public Result addProduct(){
        Form<Product> productForm = formFactory.form(Product.class);
        return ok(addProduct.render(productForm));
    }





    public Result addProductSubmit(){
        Form<Product> newProductForm = formFactory.form(Product.class).bindFromRequest();

        if(newProductForm.hasErrors()){
            return badRequest(addProduct.render(newProductForm));
        } else {
            Product newProduct = newProductForm.get();
            newProduct.save();
            flash("success", "Product "+ newProduct.getName() + " added");
            return redirect(controllers.routes.HomeController.index());

        }
    }


    public Result addCustomer(){
        Form<Customer> customerForm = formFactory.form(Customer.class);
        return ok(addCustomer.render(customerForm));
    }


    public Result addCustomerSubmit(){
        Form<Customer> newCustomerForm = formFactory.form(Customer.class).bindFromRequest();
        if(newCustomerForm.hasErrors()){
            return badRequest(addCustomer.render(newCustomerForm));
        } else {
            Customer newCustomer = newCustomerForm.get();
            newCustomer.save();
            flash("success", "Customer "+ newCustomer.getFName() + " added");
            return redirect(controllers.routes.HomeController.customers());

        }
    }


    public Result deleteProduct(Long id){
        Product.find.ref(id).delete();
        flash("success","Product has been deleted");
        return redirect(routes.HomeController.index());
    }


    public Result deleteCustomer(Long id){
        
        Customer.find.ref(id).delete();
        flash("success","Customer has been deleted");
        return redirect(routes.HomeController.customers());
    }

}
