package dao;

import java.util.List;

import domain.Bucket;
import shared.AbstractCRUD;

public interface BucketDao extends AbstractCRUD<Bucket> {
	List<Bucket> getAllByUserId(Integer userId);
}
