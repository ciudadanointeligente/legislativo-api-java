package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.common.Constants;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.CommissionAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.model.DO.CommissionDO;
import cl.votainteligente.legislativo.model.DO.CommissionDetailedDO;
import cl.votainteligente.legislativo.service.ChamberService;
import cl.votainteligente.legislativo.service.CommissionService;
import cl.votainteligente.legislativo.service.CommissionTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("commission")
@Controller
public class CommissionController implements CommissionAPI {

	@Autowired
	CommissionService commissionService;
	@Autowired
	CommissionTypeService commissionTypeService;
	@Autowired
	ChamberService chamberService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.CommissionAPI#getAll(int, int)
	 */
	@RequestMapping(value = "commission/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<CommissionDO> getAll(
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Page<CommissionDO> resultPage = commissionService.getAllCommissions(page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.CommissionAPI#findByName(String, int, int)
	 */
	@RequestMapping(params={"name"}, value = "commission/any", method = RequestMethod.GET)
	@ResponseBody
	public Page<CommissionDO> findByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Page<CommissionDO> resultPage = commissionService.findCommissionsByName(name, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.CommissionAPI#getCommission(long, int, int)
	 */
	@RequestMapping(params={"id"},value = "commission/any", method = RequestMethod.GET)
	@ResponseBody
	public CommissionDetailedDO getCommission(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Commission commission = commissionService.getCommissionById(id);

			if (commission == null) {
				throw new ResourceNotFoundException();
			}

			return new CommissionDetailedDO(commission);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.CommissionAPI#findByType(long, int, int)
	 */
	@RequestMapping(value = "commission/type", method = RequestMethod.GET)
	@ResponseBody
	public Page<CommissionDO> findByType(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			CommissionType commissionType = commissionTypeService.getById(id);
			Page<CommissionDO> resultPage = commissionService.getCommissionsByType(commissionType, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.CommissionAPI#findByChamber(long, int, int)
	 */
	@RequestMapping(value = "commission/chamber", method = RequestMethod.GET)
	@ResponseBody
	public Page<CommissionDO> findByChamber(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Chamber chamber = chamberService.getById(id);
			Page<CommissionDO> resultPage = commissionService.getCommissionsByChamber(chamber, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

}
