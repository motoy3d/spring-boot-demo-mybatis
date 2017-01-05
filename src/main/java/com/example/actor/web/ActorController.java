package com.example.actor.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.actor.mapper.PrefectureMapper;
import com.example.actor.model.Actor;
import com.example.actor.model.Prefecture;
import com.example.actor.service.ActorService;
import com.example.actor.web.form.ActorForm;

@Controller
public class ActorController {
	final static Logger logger = LoggerFactory.getLogger(ActorController.class);

	@Autowired
	ActorService actorService;

	@Autowired
	PrefectureMapper prefectureMapper;

	@Autowired
	MessageSource msg;

	@Autowired
	HttpSession session;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		sdf.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@GetMapping(value = "/actor")
	public String index(Model model) {
		session.setAttribute("userName", "motoi2");

		List<Actor> list = actorService.findAll();

		if (CollectionUtils.isEmpty(list)) {

			String message = msg.getMessage("actor.list.empty", null, Locale.JAPAN);

			model.addAttribute("emptyMessage", message);
		}

//		model = null;

		model.addAttribute("list", list);

		return "Actor/index";
	}

	@GetMapping(value = "/actor/{id}")
	public ModelAndView detail(@PathVariable Integer id) {
		logger.debug("Actor + detail");

		ModelAndView mv = new ModelAndView();

		mv.setViewName("Actor/detail");

		Actor actor = actorService.findOne(id);

		mv.addObject("actor", actor);

		return mv;
	}

	@GetMapping(value = "/actor/search")
	public ModelAndView search(@RequestParam String keyword) {
		logger.debug("Actor + search");

		ModelAndView mv = new ModelAndView();

		mv.setViewName("Actor/index");

		if (StringUtils.isNotEmpty(keyword)) {

			List<Actor> list = actorService.findActors(keyword);

			if (CollectionUtils.isEmpty(list)) {

				String message = msg.getMessage("actor.list.empty", null, Locale.JAPAN);

				mv.addObject("emptyMessage", message);
			}

			mv.addObject("list", list);
		}

		return mv;
	}

	@GetMapping(value = "/actor/create")
	public String create(ActorForm form, Model model) {
		logger.debug("Actor + create");

		List<Prefecture> pref = prefectureMapper.findAll();

		model.addAttribute("pref", pref);

//		modelDump(model, "create");

		return "Actor/create";
	}

	@PostMapping(value = "/actor/save")
	public String save(@Validated @ModelAttribute ActorForm form, BindingResult result, Model model) {
		logger.debug("Actor + save");

		if (result.hasErrors()) {

			String message = msg.getMessage("actor.validation.error", null, Locale.JAPAN);

			model.addAttribute("errorMessage", message);

			return create(form, model);
		}

		Actor actor = actorService.save(form);

		return "redirect:/actor/" + actor.getId().toString();
	}

	@GetMapping(value = "/actor/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes attributes, Model model) {
		logger.debug("Actor + delete");

		actorService.delete(id);

		attributes.addFlashAttribute("deleteMessage", "delete ID:" + id);

		return "redirect:/actor";
	}

	/**
	 * for debug.
	 */
	private void modelDump(Model model, String m) {
		logger.debug(" ");
		logger.debug("Model:{}", m);

		Map<String, Object> mm = model.asMap();

		for (Entry<String, Object> entry : mm.entrySet()) {

			logger.debug("key:{}, value:{}", entry.getKey(), entry.getValue().toString());

		}
	}

}
