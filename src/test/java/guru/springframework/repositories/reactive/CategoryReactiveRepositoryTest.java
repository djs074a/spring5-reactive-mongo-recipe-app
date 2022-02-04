package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    public static final String MEXICAN = "Mexican";
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() {
        Category cat = new Category();
        cat.setDescription(MEXICAN);

        categoryReactiveRepository.save(cat).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1), count);
    }

    @Test
    public void findByDescription() {
        Category cat = new Category();
        cat.setDescription(MEXICAN);

        categoryReactiveRepository.save(cat).block();

        Category fetchedCat = categoryReactiveRepository.findByDescription(MEXICAN).block();

        assertNotNull(fetchedCat.getId());
    }
}