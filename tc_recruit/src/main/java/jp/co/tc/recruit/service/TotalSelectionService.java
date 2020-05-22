package jp.co.tc.recruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tc.recruit.entity.TotalSelectionView;
import jp.co.tc.recruit.repository.TotalSelectionRepository;

@Service
public class TotalSelectionService {

	@Autowired
	TotalSelectionRepository ttlSlcRepo;

	public TotalSelectionView findByStatusMessageId(Integer statusMessageId) {
		return ttlSlcRepo.findByStatusMessageId(statusMessageId);
	}

	public List<TotalSelectionView> findAllByOrderByStatusMessageId(){
		return ttlSlcRepo.findAllByOrderByStatusMessageId();
	}

}
