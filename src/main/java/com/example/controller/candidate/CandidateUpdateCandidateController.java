package com.example.controller.candidate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Candidate;
import com.example.repository.CandidateRepository;
import com.example.util.URLMapper;
import com.example.util.ValidateScripting;
import com.example.util.ViewMapper;

@Controller
public class CandidateUpdateCandidateController {

	static final String UPDATE_CANDIDATE_MODEL = "updateCandidate";

	@Autowired
	CandidateRepository candidateRepository;

	@RequestMapping(value = URLMapper.CANDIDATE_UPDATE_PROFILE_URL, method = RequestMethod.GET)
	public String loadUpdateJobPage(Model model, @RequestParam(value = "cId") String candidateId) {
		Candidate candidate = candidateRepository.findOne(Long.parseLong(candidateId));

		model.addAttribute(UPDATE_CANDIDATE_MODEL, candidate);
		model.addAttribute("candidate_update_profile_url", URLMapper.CANDIDATE_UPDATE_PROFILE_URL);

		return ViewMapper.CANDIDATE_UPDATE_PROFILE;
	}

	@RequestMapping(value = URLMapper.CANDIDATE_UPDATE_PROFILE_URL, method = RequestMethod.POST)
	public String postJob(@Valid @ModelAttribute(UPDATE_CANDIDATE_MODEL) Candidate candidate, BindingResult result, Model model,HttpServletRequest request) {

		// validate the job entry
		if (result.hasErrors()) {
			model.addAttribute("candidate_update_profile_url", URLMapper.CANDIDATE_UPDATE_PROFILE_URL);
			return ViewMapper.CANDIDATE_UPDATE_PROFILE;
		}

		try {
			Candidate newCandidate = candidateRepository.findOne(candidate.getId());
			if (null == newCandidate) {
				return ViewMapper.CANDIDATE_UPDATE_PROFILE;
			}
			String name= ValidateScripting.validate(candidate.getName());
			String univName =  ValidateScripting.validate(candidate.getUnivName());
			String email = ValidateScripting.validate(candidate.getEmail());
			String linkedInUrl = ValidateScripting.validate(candidate.getLinkedInUrl());
			String degree = ValidateScripting.validate(candidate.getDegree());


			newCandidate.setName(name);
			newCandidate.setUnivName(univName);
			newCandidate.setEmail(email);
			newCandidate.setLinkedInUrl(linkedInUrl);
			newCandidate.setDegree(degree);

			// updating job
			candidateRepository.save(newCandidate);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "redirect:" + URLMapper.CANDIDATE_UPDATE_PROFILE_URL;
	}

}
