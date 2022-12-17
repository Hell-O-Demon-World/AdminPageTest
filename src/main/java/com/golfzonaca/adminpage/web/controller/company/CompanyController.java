package com.golfzonaca.adminpage.web.controller.company;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.company.CompanySearchCond;
import com.golfzonaca.adminpage.service.company.CompanyService;
import com.golfzonaca.adminpage.service.company.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @GetMapping //업체 등록 조회
    public String companies(@ModelAttribute("companySearch") CompanySearchCond companySearch, Model model) {
        List<Company> companies = companyService.findCompanies(companySearch.getCompanyName());
        model.addAttribute("companies", companies);
        return "/company/companies";
    }

    @GetMapping("/{companyId}")
    public String company(@PathVariable Long companyId, Model model) {
        Company company = companyService.findById(companyId);
        model.addAttribute("company", company);
        return "/company/company";
    }

    @GetMapping("/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("company", new CompanyDto());
        return "/company/addForm";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute CompanyDto companyDto, RedirectAttributes redirectAttributes) {
        String pw = bCryptPasswordEncoder.encode(companyDto.getCompanyPw());
        companyDto.setCompanyPw(pw);
        Company savedCompany = companyService.save(companyDto);
        redirectAttributes.addAttribute("companyId", savedCompany.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/companies/{companyId}";
    }

    @GetMapping("/delete/{companyId}")
    public String delete(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return "redirect:/companies";
    }
}