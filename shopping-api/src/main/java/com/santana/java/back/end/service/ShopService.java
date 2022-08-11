package com.santana.java.back.end.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santana.java.back.end.dto.ShopDTO;
import com.santana.java.back.end.dto.ShopReportDTO;
import com.santana.java.back.end.model.Shop;
import com.santana.java.back.end.repository.ReportRepository;
import com.santana.java.back.end.repository.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	public List<ShopDTO> getAll() {
		List<Shop> shops = shopRepository.findAll();
		
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}

	public List<ShopDTO> getByUser(String userIdentifier) {
		List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
		
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByDate(ShopDTO shopDTO) {
		List<Shop> shops = shopRepository.findAllByDateGreaterThanEquals(shopDTO.getDate());
		
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}
	
	public ShopDTO findById(long productId) {
		Optional<Shop> shop = shopRepository.findById(productId);
		
		if (shop.isPresent()) {
			return ShopDTO.convert(shop.get());
		}
		
		return null;
	}
	
	public ShopDTO save(ShopDTO shopDTO) {
		shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));
		
		Shop shop = Shop.convert(shopDTO);
		shop.setDate(new Date());
		shop = shopRepository.save(shop);
		
		return ShopDTO.convert(shop);
	}
	
	public List<ShopDTO> getShopsByFilter(Date dataInicio, Date dataFim, Float valorMinimo) {
		
		List<Shop> shops = reportRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
		
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	};
	
	public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
		
		return reportRepository.getReportByDate(dataInicio, dataFim);
	}
}
