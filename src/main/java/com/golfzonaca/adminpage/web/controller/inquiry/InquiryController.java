package com.golfzonaca.adminpage.web.controller.inquiry;

import com.golfzonaca.adminpage.domain.Answer;
import com.golfzonaca.adminpage.service.answer.AnswerService;
import com.golfzonaca.adminpage.service.inquiry.InquiryService;
import com.golfzonaca.adminpage.service.inquiry.dto.InquiryData;
import com.golfzonaca.adminpage.service.inquiry.dto.InquiryDetails;
import com.golfzonaca.adminpage.web.controller.inquiry.dto.AnswerData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;
    private final AnswerService answerService;

    @GetMapping("/qna")
    public String inquiries(Model model) {
        Map<Integer, InquiryData> inquiryData = inquiryService.findAll();
        model.addAttribute("inquiries", inquiryData);
        return "qna/inquiries";
    }

    @GetMapping("/qna/{inquiryId}")
    public String inquiries(@PathVariable Long inquiryId, Model model) {
        InquiryDetails inquiryDetails = inquiryService.findDetails(inquiryId);
        model.addAttribute("inquiryDetails", inquiryDetails);
        model.addAttribute("answer", new Answer());
        return "qna/inquiry";
    }

    @PostMapping("/qna/{inquiryId}/answer")
    public String answer(@ModelAttribute AnswerData answerData, @PathVariable Long inquiryId, RedirectAttributes redirectAttributes) {
        Answer answer = answerService.save(inquiryId, answerData.getAnswer());
        redirectAttributes.addAttribute("inquiryId", inquiryId);
        return "redirect:/qna/{inquiryId}";
    }
}
