package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.CompanyDto;
import com.projeto.restaurante.exceptions.CompanyAlreadyRegisteredException;
import com.projeto.restaurante.exceptions.UnregisteredCompanyException;
import com.projeto.restaurante.identities.Company;
import com.projeto.restaurante.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    public CompanyDto create(CompanyDto companyDto){
        long empresa = companyRepository.count();
        if(empresa > 0){
            throw new CompanyAlreadyRegisteredException();
        }

        Company company = mapper.map(companyDto, Company.class);
        Company companyConverter = stringConverter(company);
        companyRepository.save(companyConverter);
        return mapper.map(companyConverter, CompanyDto.class);
    }

    public CompanyDto findCompany(){
        Optional<Company> companyOptional = companyRepository.findFirstByOrderByIdAsc();
        if(companyOptional.isEmpty()){
            throw new UnregisteredCompanyException();
        }
        return mapper.map(companyOptional, CompanyDto.class);
    }

    public void update(String cnpj, CompanyDto companyDto){
        Optional<Company> companyOptional = companyRepository.findByCnpj(cnpj);
        if(companyOptional.isEmpty()){
            throw new UnregisteredCompanyException();
        }
        Company company = mapper.map(companyDto, Company.class);
        Company companyConverter = stringConverter(company);
        companyConverter.setId(companyOptional.get().getId());
        companyRepository.save(companyConverter);
    }

    private Company stringConverter(Company company){
        Company companyConverter = new Company();
        companyConverter.setName(TextConverter.stringConverter(company.getName()));
        companyConverter.setAdress(TextConverter.stringConverter(company.getAdress()));
        companyConverter.setNumber(company.getNumber());
        companyConverter.setComplement(TextConverter.stringConverter(company.getComplement()));
        companyConverter.setNeighborhood(TextConverter.stringConverter(company.getNeighborhood()));
        companyConverter.setCity(TextConverter.stringConverter(company.getCity()));
        companyConverter.setUf(company.getUf().toUpperCase());
        companyConverter.setZipcode(company.getZipcode());
        companyConverter.setCnpj(company.getCnpj());
        companyConverter.setEmail(company.getEmail().toLowerCase());
        companyConverter.setPhone(company.getPhone());
        return companyConverter;
    }
}
