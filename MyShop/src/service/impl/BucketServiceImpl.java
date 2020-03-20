package service.impl;

import dao.BucketDao;
import dao.impl.BucketDaoImpl;
import domain.Bucket;
import service.BucketService;

import java.sql.SQLException;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class BucketServiceImpl implements BucketService {
	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(BucketServiceImpl.class);
    private static BucketService bucketServise;
    private BucketDao bucketDao;
    private BucketServiceImpl(){
        try {
            bucketDao = new BucketDaoImpl();
        } catch (InstantiationException e) {
            logger.error(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static BucketService getBucketService(){
        if (bucketServise==null){
            bucketServise=new BucketServiceImpl();
        }
        return bucketServise;
    }

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket read(Integer id) {
        return bucketDao.read(id);
    }

    @Override
    public Bucket update(Integer id, Bucket bucket) {
        return bucketDao.update(id, bucket);
    }

    @Override
    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }

	@Override
	public List<Bucket> getAllByUserId(Integer userId) {
		return bucketDao.getAllByUserId(userId);
	}
}
