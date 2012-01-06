package cl.votainteligente.legislativo.service.bill;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Stage;

public interface StageService {
	Stage newStage(Stage stage) throws ServiceException;
}
