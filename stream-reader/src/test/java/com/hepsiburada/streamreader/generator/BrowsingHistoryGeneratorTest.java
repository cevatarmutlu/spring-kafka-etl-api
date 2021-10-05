package com.hepsiburada.streamreader.generator;


import com.hepsiburada.streamreader.model.BrowsingHistory;
import com.hepsiburada.streamreader.model.Event;
import com.hepsiburada.streamreader.model.submodel.EventContext;
import com.hepsiburada.streamreader.model.submodel.EventProperties;
import com.hepsiburada.streamreader.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
public class BrowsingHistoryGeneratorTest {

    @Mock
    private ProductService productService;

    private int userId = 1;
    private int productId = 10;
    private int categoryId = 3;

    @Before
    public void setUp() {

        List<?> category_id_list = List.of("categoryid-" + categoryId);
        Mockito.doReturn(category_id_list)
                        .when(productService).getCategoryId("product-" + productId);

    }

    @Test
    public void generator_test() {

        Event event = new Event("eventid", "messageid", "user-" + userId, new EventProperties("product-" + productId), new EventContext("mobile"), 1111L);

        BrowsingHistoryGenerator generator = new BrowsingHistoryGenerator(event, productService);
        BrowsingHistory history = generator.generate();

        Assert.assertEquals(history.getCategoryId(), categoryId);
        Assert.assertEquals(history.getProductId(), productId);
        Assert.assertEquals(history.getUserId(), userId);
        Assert.assertEquals(history.getTimestamp(), new Timestamp(event.getTimestamp()));

    }

}
