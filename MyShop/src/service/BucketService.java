package service;

import java.util.List;

import domain.Bucket;
import shared.AbstractCRUD;

public interface BucketService extends AbstractCRUD<Bucket> {
	List<Bucket> getAllByUserId(Integer userId);
}
