package com.myblog.blog;

import com.myblog.blog.repositories.CategoryRepo;
import com.myblog.blog.services.CategoryService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CategoryServiceImplTest.class})
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepo categoryRepo;
    @InjectMocks
    private CategoryService categoryService;


    public void createCategoryTest() {

    }

//    @Test
//    @Order(1)
//    public void getCategoriesTest() {
//
//
//        when(categoryRepo.findAll()).thenReturn(mycategories);
//        categoryService.getCategories();
//    }
}
