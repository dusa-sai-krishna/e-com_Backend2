package com.saiDeveloper.E_commerce2_Backend.service;

import java.lang.reflect.*;
import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//extend mockito
@ExtendWith(MockitoExtension.class)
class ProductServiceImplementationTest {
    @InjectMocks
    ProductServiceImplementation productService;
    @Mock
    ProductRepo repo;

    @Test
    void findProductById() throws ProductException {
        //data preparatio
           Long id = 1L;
           Product product = new Product();
           product.setId(id);
       //mocking
           when(repo.findById(id)).thenReturn(Optional.of(product));
       //test
           Product result = productService.findProductById(id);

        //assertions
           assertEquals(product, result);


    }

    @Test
    void deleteProduct() throws ProductException {
    Long id = 1L;

    //mocking the call
       doNothing().when(repo).deleteById(id);

    //it verifies whether the repo.deleteById(1) is called once.
    verify(repo,times(1)).deleteById(id);
    }

    @Test
    void testPrivateClass_quantityValidationTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ProductServiceImplementation.class.getDeclaredMethod("quantityValidatiion",int.class);
        //make method acessible here
        method.setAccessible(true);

        int quantity = 1;
        boolean result = (boolean) method.invoke(productService, quantity);
        assertTrue(result);
    }



    @Test
    void testPrivateClass_quantityValidationFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ProductServiceImplementation.class.getDeclaredMethod("quantityValidatiion",int.class);
        //make method acessible here
        method.setAccessible(true);

        int quantity = 0;
        boolean result = (boolean) method.invoke(productService, quantity);
        assertFalse(result);
    }


}