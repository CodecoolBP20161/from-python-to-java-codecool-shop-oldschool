import com.codecool.shop.dao.implementation.ProductCategoryDaoTest;
import com.codecool.shop.dao.implementation.ProductDaoTest;
import com.codecool.shop.dao.implementation.SupplierDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ProductCategoryDaoTest.class,
        ProductDaoTest.class,
        SupplierDaoTest.class
})

public class AllTests {
}  