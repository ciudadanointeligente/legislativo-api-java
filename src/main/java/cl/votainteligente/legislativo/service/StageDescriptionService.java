package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.StageDescription;

public interface StageDescriptionService {
	StageDescription newStageDescription(StageDescription stageDescription)
			throws ServiceException;

	Page<StageDescription> getAll(int pageNumber, int resultsPerPage)
			throws ServiceException;

	Page<StageDescription> getByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException;

	StageDescription getById(Long id) throws ServiceException;
}
