package com.projects.teashop.tea.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projects.teashop.tea.constans.ErrorMessage;
import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.DTO.TeaRequest;
import com.projects.teashop.tea.domain.status.tea_type;
import com.projects.teashop.tea.repository.TeaRepository;
import com.projects.teashop.tea.service.blueprint.SalesService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SalesServiceImpl implements SalesService {
	private final TeaRepository teaRepo;
	
	
	@Override
	public Page<Tea> findAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 14, Sort.by("Id"));
		return teaRepo.findAll(pageable);
	}
	
	@Override
	public Slice<Tea> findByType(int pageNumber, tea_type type) {
		Pageable pageable = PageRequest.of(pageNumber, 14, Sort.by("Id"));
		return teaRepo.findBySort(pageable, type);
	}

	@Override
	public Tea findById(Long Id) {
		return teaRepo.findById(Id).orElseThrow(
				() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						ErrorMessage.TEA_NOT_FOUND
						)
				);
	}

	@Override
	public Tea addTea(Tea tea) {
		teaRepo.save(tea);
		return tea;
	}

	@Override
	public Tea changeTea(Long Id, Tea tea) {
		findById(Id).setName(tea.getName());
		findById(Id).setRegion(tea.getRegion());
		findById(Id).setYear(tea.getYear());
		findById(Id).setEffect(tea.getEffect());
		findById(Id).setTaste(tea.getTaste());
		findById(Id).setIsAvailable(tea.getIsAvailable());
		findById(Id).setAmount(tea.getAmount());
		findById(Id).setPricePerGram(tea.getPricePerGram());
		findById(Id).setGrams(tea.getGrams());
		findById(Id).setLeaf_structure(tea.getLeaf_structure());
		return findById(Id);
	}

	@Override
	@Transactional 
	public String deleteTea(Long Id) {
		teaRepo.deleteById(Id);
		return Id.toString() + " is deleted";
	}

//	@Override
//	public Page<Tea> searchTea(int pageNumber, TeaRequest request) {
//		Pageable pageable = PageRequest.of(pageNumber, 14, Sort.by("Id"));
//		return teaRepo.searchRequest(
//				pageable, 
//				request.getName(), 
////				request.getTea_sort(), 
//				request.getRegion(),
//				request.getYear());
//	}

}
