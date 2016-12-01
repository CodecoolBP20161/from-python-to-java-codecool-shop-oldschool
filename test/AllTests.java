import com.codecool.shop.dao.implementation.ProductCategoryDaoTest;
import com.codecool.shop.dao.implementation.ProductDaoTest;
import com.codecool.shop.dao.implementation.SupplierDaoTest;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.OrderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

//run all tests from one place
@Suite.SuiteClasses({
        ProductCategoryDaoTest.class,
        ProductDaoTest.class,
        SupplierDaoTest.class,
        OrderTest.class,
        LineItem.class
})

public class AllTests {
}  