package com.fauzan.DIY_COMMERCE.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.fauzan.DIY_COMMERCE.DTO.ProductDTO;
import com.fauzan.DIY_COMMERCE.DTO.VariationDTO;
import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.ProductVariation;
import com.fauzan.DIY_COMMERCE.entity.Seller;
import com.fauzan.DIY_COMMERCE.exception.ProductNotFoundException;
import com.fauzan.DIY_COMMERCE.repository.ProductRepository;
import com.fauzan.DIY_COMMERCE.repository.ProductVariationRepository;
import com.fauzan.DIY_COMMERCE.repository.SellerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductVariationRepository variationRepo;

	@Autowired
	private SellerRepository sellerRepo;

	@Override
	public Product getProduct(String id) {
		Product product = unwrapProduct(productRepo.findById(id), id);

		return product;
	}

	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepo.findAll(pageable);
	}

	@Override
	public Product addProduct(Product product, String sellerId) {
		// if this line does not pass, then an exception will occur
		Seller seller = SellerServiceImplementation.unwrapSeller(sellerRepo.findById(sellerId), sellerId);

		// add this seller to the product
		product.setSeller(seller);

		// if the line above pass, then can proceed with new product addition
		return productRepo.save(product);
	}

	@Override
	public void deleteProduct(String productId) {
		Optional<Product> product = productRepo.findById(productId);

		if (product.isPresent()) {
			productRepo.deleteById(productId);
		} else {
			throw new ProductNotFoundException(productId);
		}
	}

	@Override
	public Product updateProductByFields(String id, Map<String, Object> fields) {
		Product product = unwrapProduct(productRepo.findById(id), id);

		// remove update-prohibited fields
		fields.remove("productId");
		fields.remove("dateCreated");
		fields.remove("lastModified");

		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findRequiredField(Product.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, product, value);
		});

		product.setLastModified(new Date());
		return productRepo.save(product);
	}

	@Override
	public Page<Product> getProductBySellerId(String sellerId, Pageable pageable) {
		SellerServiceImplementation.unwrapSeller(sellerRepo.findById(sellerId), sellerId);

		return productRepo.findProductBySellerId(sellerId, pageable);
	}

	@Override
	public ProductVariation addProductVariation(String productId, ProductVariation variation) {
		Product product = unwrapProduct(productRepo.findById(productId), productId);

		variation.setProduct(product);

		// update the product last modified date
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("lastModified", new Date());
		updateProductByFields(productId,fields);

		return variationRepo.save(variation);
	}

	@Override
	public ProductDTO getProductById(String id) {
		Product product = unwrapProduct(productRepo.findById(id), id);
		List<ProductVariation> variations = variationRepo.findByProductProductId(id);

		ProductDTO productDto = new ProductDTO();

		productDto.setProduct(product);
		productDto.setVariations(variations);

		return productDto;
	}

	@Override
	public ProductVariation patchVariationById(String productId, String variationId, Map<String,Object> fields) {
		
		// get or throw productNotFoundException
		unwrapProduct(productRepo.findById(productId), productId);
		
		// get the variation
		ProductVariation variation = unwrapProductVariation(variationRepo.findById(variationId), variationId);
		
		fields.remove("variationId");
		
		fields.forEach((key,value)-> {
			Field field = ReflectionUtils.findRequiredField(ProductVariation.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, variation, value);
		});
		
		Map<String,Object> patchField = new HashMap<String,Object>();
		updateProductByFields(productId, patchField);		
		return variationRepo.save(variation);
	}

	@Override
	public ProductVariation getVariation(String variationId) {
		ProductVariation variation = unwrapProductVariation(variationRepo.findById(variationId), variationId);
		return variation;
	}

	@Override
	public VariationDTO getVariationById(String variationId) {
		ProductVariation variation = unwrapProductVariation(variationRepo.findById(variationId), variationId);
		VariationDTO dto = new VariationDTO();
		dto.setProductVariation(variation);
		dto.setProduct(variation.getProduct());
		
		return dto;
	}

	@Override
	public void deleteAll() {
		productRepo.deleteAll();
	}

	static Product unwrapProduct(Optional<Product> product, String productId) {
		if (product.isPresent())
			return product.get();

		throw new ProductNotFoundException(productId);
	}
	
	static ProductVariation unwrapProductVariation(Optional<ProductVariation> variation, String variationId) {
		if(variation.isPresent()) {
			return variation.get();
		} else throw new ProductNotFoundException(variationId);
	}
	
	
	
	
}
