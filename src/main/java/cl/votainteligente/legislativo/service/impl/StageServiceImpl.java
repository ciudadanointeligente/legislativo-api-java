package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Stage;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.StageService;

import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl extends EntityManagerService implements StageService {

	@Override
	public Stage newStage(Stage stage) throws ServiceException {
		getEntityManager().persist(stage);
		return stage;
	}

}
