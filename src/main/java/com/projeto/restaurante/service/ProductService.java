package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.ProductDto;
import com.projeto.restaurante.dto.ProductReturnDto;
import com.projeto.restaurante.exceptions.EmptyProductsListExceptions;
import com.projeto.restaurante.exceptions.ProductAlreadyRegisteredException;
import com.projeto.restaurante.exceptions.UnregisteredGroupException;
import com.projeto.restaurante.identities.Group;
import com.projeto.restaurante.identities.Product;
import com.projeto.restaurante.repository.GroupRepository;
import com.projeto.restaurante.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final GroupRepository groupRepository;
    private final ModelMapper mapper;

    public ProductReturnDto create(ProductDto productDto){
        Optional<Product> productOptional = productRepository.findBynameProduct(productDto.getNameProduct());
        if(productOptional.isPresent()){
            throw new ProductAlreadyRegisteredException();
        }
        Optional<Group> groupOptional = groupRepository.findById(productDto.getGroup());
        if(groupOptional.isEmpty()){
            throw new UnregisteredGroupException();
        }

        Product product = mapper.map(productDto, Product.class);
        product.setNameProduct(TextConverter.stringConverter(productDto.getNameProduct()));
        product.setRegistratrionDate(new Date());
        productRepository.save(product);
        return mapper.map(product, ProductReturnDto.class);
    }

    public List<ProductReturnDto> findAll(){
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()){
            throw new EmptyProductsListExceptions();
        }
        return productList.
                stream().
                map(ProductReturnDto::new).
                collect(Collectors.toList());
    }
}
