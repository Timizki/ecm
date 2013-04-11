package net.vksn.ecm.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.ecm.comparator.NavigationItemComparator;
import net.vksn.ecm.controllers.form.SitemapItemForm;
import net.vksn.ecm.controllers.validator.SitemapItemFormValidator;
import net.vksn.ecm.converter.SitemapItemEditor;
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
		if(model.containsAttribute("sitemapItemForm")) {
			form = (SitemapItemForm) model.get("sitemapItemForm");
		}
		else {
			form = new SitemapItemForm();
			int sitemapId = getSitemapId(request);
			String[] path = getPagePath(request);
			SitemapItem item = null;
	
			List<TilesDefinition> templates = getDefinitions();
			if ("edit".equals(mode)) {
				item = sitemapItemService.getItemByPath(sitemapId, path);
				form.setSiblings(sitemapItemService.getSiblings(item));
				Sitemap sitemap = sitemapService.getSitemap(sitemapId, false);
				form.setSitemap(sitemap);
			} else {
				item = new SitemapItem();
				Sitemap sitemap = sitemapService.getSitemap(sitemapId, false);
				form.setSitemap(sitemap);
				item.setSitemap(sitemap);
				item.setDecorationName(templates.iterator().next().getName());
				form.setSiblings(sitemap.getSitemapItems());
			}
	
			form.setSitemapItem(item);
			form.setSitemapItems(sitemapItemService.getAllSitemapItems(sitemapId));
			form.setTemplates(templates);
			model.addAttribute("sitemapItemForm", form);
		}
		return form;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView storePage(
			@ModelAttribute("sitemapItemForm") SitemapItemForm form, BindingResult result, HttpServletRequest request)
			throws EntityNotFoundException {
		ModelAndView mv = null;
		new SitemapItemFormValidator().validate(form, result);

		String action = request.getParameter("action");

		if ("decorationName".equals(action)) {
			return new ModelAndView(form.getSitemapItem().getDecorationName());
		} else if ("parent".equals(action)) {
			Set<SitemapItem> items = sitemapItemService.getSiblings(form
					.getParent());
			form.setSiblings(items);
			form.getSitemapItem().setParent(form.getParent());
			mv = new ModelAndView(form.getSitemapItem().getDecorationName());
			return mv;
		} else if ("position".equals(action)) {
			adjustPositions(form);
			mv = new ModelAndView(form.getSitemapItem().getDecorationName());
			mv.addObject("sitemapItemForm", form);
			return mv;
		}
		if (result.hasErrors()) {
			mv = new ModelAndView(form.getSitemapItem().getDecorationName());
			return mv;
		}

		sitemapItemService.storeSitemapItem(form.getSitemapItem());

		return new ModelAndView("redirect:/" + form.getSitemapItem().getPathAsString() + ".html");
	}
	
	private void adjustPositions(SitemapItemForm form) throws EntityNotFoundException {
		

		SitemapItem parent = form.getSitemapItem().getParent();
		TreeSet<SitemapItem> siblings = new TreeSet<SitemapItem>(new NavigationItemComparator());
		if (parent != null) {
			siblings.addAll(parent.getChildrens());
		}
		else {
			Sitemap sitemap = sitemapService.getSitemap(form
					.getSitemapItem().getSitemap().getId(), false);
			siblings.addAll(sitemap.getSitemapItems());
		}
		// 0 => 6
		// 0 1 2 3 4 5 6 
		//   < < < < < <
		// 3 => 6
		// 0 1 2  <4<5<6
		// 6 => 3
		// 0 1 2 3 4>5>
		
		boolean moveDirectionToBigger = form.getNewPagePosition() > form.getSitemapItem().getPagePosition();
		int oldPosition = form.getSitemapItem().getPagePosition();
		for (Iterator<SitemapItem> i = siblings.iterator(); i.hasNext();) {
			SitemapItem item = i.next();
			if(item.getName().equals(form.getSitemapItem().getName())) {
				i.remove();
			}
			if(moveDirectionToBigger) {
				if (item.getPagePosition() > oldPosition) {
					item.setPagePosition(item.getPagePosition() - 1);
				}
			}
			else {
				if (item.getPagePosition() >= form.getNewPagePosition()) {
					item.setPagePosition(item.getPagePosition() + 1);
				}
			}
		}
		form.getSitemapItem().setPagePosition(form.getNewPagePosition());
		siblings.add(form.getSitemapItem());
		form.getSitemap().setSitemapItems(siblings);
	}

}
