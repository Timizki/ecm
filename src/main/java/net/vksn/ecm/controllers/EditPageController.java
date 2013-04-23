package net.vksn.ecm.controllers;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.ecm.controllers.form.SitemapItemForm;
import net.vksn.ecm.controllers.validator.SitemapItemFormValidator;
import net.vksn.ecm.converter.SitemapItemEditor;
import net.vksn.ecm.helper.Organizator;
import net.vksn.ecm.model.TilesDefinition;
import net.vksn.ecm.service.DefinitionService;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditPageController extends AbstractPageController {

	@Autowired
	private SitemapItemService sitemapItemService;
	
	@Autowired
	private SitemapService sitemapService;

	@Autowired
	private DefinitionService defintionService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SitemapItemEditor sitemapItemEditor = new SitemapItemEditor();
		sitemapItemEditor.setSitemapItempService(sitemapItemService);
		binder.registerCustomEditor(SitemapItem.class, sitemapItemEditor);
	}

	private List<TilesDefinition> getDefinitions() {
		List<TilesDefinition> definitions = defintionService.getDefinitions();
		return definitions;
	}

	@RequestMapping(params = "mode=edit", method = RequestMethod.GET)
	public String showEditForm(@ModelAttribute SitemapItemForm form) {
		return form.getSitemapItem().getDecorationName();
	}

	@RequestMapping(params = "mode=addPage", method = RequestMethod.GET)
	public String showNewForm(@ModelAttribute SitemapItemForm form) {
		return form.getSitemapItem().getDecorationName();
	}

	@ModelAttribute
	public SitemapItemForm getForm(@RequestParam String mode, ModelMap model,
			HttpServletRequest request) throws EntityNotFoundException {
		SitemapItemForm form = null;
		form = new SitemapItemForm();
		int sitemapId = getSitemapId(request);
		String[] path = getPagePath(request);
		SitemapItem item = null;
		TreeSet<SitemapItem> orderedItems = new TreeSet<SitemapItem>();
		List<TilesDefinition> templates = getDefinitions();
		if ("edit".equals(mode)) {
			item = sitemapItemService.getItemByPath(sitemapId, path);
			orderedItems.addAll(sitemapItemService.getSiblings(item));
			form.setSiblings(orderedItems);
			Sitemap sitemap = sitemapService.getSitemap(sitemapId, false);
			form.setSitemap(sitemap);
		} 
		else {
			item = new SitemapItem();
			Sitemap sitemap = sitemapService.getSitemap(sitemapId, false);
			form.setSitemap(sitemap);
			item.setSitemap(sitemap);
			item.setDecorationName(templates.iterator().next().getName());
			
			orderedItems.addAll(sitemap.getSitemapItems());
			form.setSiblings(orderedItems);
			item.setPagePosition(orderedItems.size());
		}

		form.setSitemapItem(item);
		TreeSet<SitemapItem> positions = new TreeSet<SitemapItem>();
		positions.addAll(sitemapItemService.getAllSitemapItems(sitemapId));
		form.setSitemapItems(positions);
		form.setTemplates(templates);
		model.addAttribute("sitemapItemForm", form);
		return form;
	}

	@RequestMapping(method = RequestMethod.POST, params="action=position")
	public String updatePagePosition(@ModelAttribute("sitemapItemForm") SitemapItemForm form, BindingResult result) {
		TreeSet<SitemapItem> siblings = new TreeSet<SitemapItem>();
		if(form.getSitemapItem().getParent() == null) {
			siblings.addAll(form.getSitemapItem().getSitemap().getSitemapItems());
		}
		else {
			siblings.addAll(form.getSitemapItem().getParent().getChildrens());
		}
		new Organizator().updatePositions(siblings, form.getSitemapItem().getPagePosition(), form.getNewPagePosition(), form.getSitemapItem());
		form.getSitemapItem().getSitemap().setSitemapItems(siblings);
		form.setSitemapItems(siblings);
		form.setSiblings(siblings);
		return form.getSitemapItem().getDecorationName();
	}
	
	@RequestMapping(method = RequestMethod.POST, params="action=parent")
	public String updatePageParent(@ModelAttribute("sitemapItemForm") SitemapItemForm form, BindingResult result) {
		if(form.getParent() != null) {
			form.getSitemapItem().getSitemap().getSitemapItems().remove(form.getSitemapItem());
		}
		Set<SitemapItem> items = sitemapItemService.getSiblings(form
				.getParent());
		TreeSet<SitemapItem> orderedItems = new TreeSet<SitemapItem>();
		orderedItems.addAll(items);
		form.setSiblings(orderedItems);
		form.getSitemapItem().setParent(form.getParent());
		return form.getSitemapItem().getDecorationName();
	}
	
	@RequestMapping(method = RequestMethod.POST, params="action=decorationName")
	public String updateDecoration(@ModelAttribute("SitemapItemForm") SitemapItemForm form, BindingResult result) {
		return form.getSitemapItem().getDecorationName();
	}
	
	@RequestMapping(method = RequestMethod.POST, params="action=store")
	public ModelAndView storePage(
			@ModelAttribute("sitemapItemForm") SitemapItemForm form, BindingResult result, HttpServletRequest request)
			throws EntityNotFoundException {
		ModelAndView mv = null;
		new SitemapItemFormValidator().validate(form, result);

		if (result.hasErrors()) {
			mv = new ModelAndView(form.getSitemapItem().getDecorationName());
			return mv;
		}
		form.getSitemapItem().setPagePosition(form.getNewPagePosition());
		sitemapItemService.storeSitemapItem(form.getSitemapItem());

		return new ModelAndView("redirect:/" + form.getSitemapItem().getPathAsString() + ".html");
	}
	
}
