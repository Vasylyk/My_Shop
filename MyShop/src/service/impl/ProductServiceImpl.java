package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import domain.Product;

import service.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{
	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductServiceImpl.class);
    private static ProductService productService;
    private ProductDao productDao;
    private ProductServiceImpl (){
        try {
            productDao = new ProductDaoImpl();
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        } catch (InstantiationException e) {
            logger.error(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
    }

    public static ProductService getProductService(){
        if (productService==null){
            productService=new ProductServiceImpl();
        }
        return productService;
    }

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product read(Integer id) {
        return productDao.read(id);
    }

    @Override
    public Product update(Integer id, Product product) {
        return productDao.update(id, product);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDao.readAll();
    }

	@Override
	public Map<Integer, Product> readAllMap() {
		return  readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
	}
}
