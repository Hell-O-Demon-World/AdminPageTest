package com.golfzonaca.adminpage.web.controller;

import com.golfzonaca.adminpage.domain.Location;
import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import com.golfzonaca.adminpage.service.AddressService;
import com.golfzonaca.adminpage.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final AddressService addressService;

    @GetMapping //업체 등록 조회
    public String companies(@ModelAttribute("companySearch") CompanySearchCond companySearch, Model model) {
        List<Company> companies = companyService.findCompanies(companySearch);
        model.addAttribute("companies", companies);
        return "company/companies";
    }

    @GetMapping("/{companyId}")
    public String company(@PathVariable Long companyId, Model model) {
        Company company = companyService.findById(companyId).get();
        Location location = addressService.findByAddressId(company.getAddressId()).get();
        model.addAttribute("company", company);
        model.addAttribute("location", location);
        return "/company/company";
    }

    @GetMapping("/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("location", new Location());
        return "/company/addForm";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute Company company, Location location, RedirectAttributes redirectAttributes) {
        Location savedLocation = addressService.save(location);
        company.setAddressId(savedLocation.getId());
        Company savedCompany = companyService.save(company);
        redirectAttributes.addAttribute("companyId", savedCompany.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/companies/{companyId}";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        addressService.delete(companyService.findById(id).get().getAddressId());
        companyService.delete(id);
        return "redirect:/companies";
    }
}