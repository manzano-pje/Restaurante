package com.projeto.restaurante.service;

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
        companyRepository.save(company);
        return mapper.map(company, CompanyDto.class);
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
        company.setId(companyOptional.get().getId());
        companyRepository.save(company);

    }
}
