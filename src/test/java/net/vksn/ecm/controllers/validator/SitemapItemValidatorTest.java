package net.vksn.ecm.controllers.validator;

import net.vksn.bedrock.model.Entity;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SitemapItemValidatorTest {
	private Validator validator;
	
	@Before
	public void setup() {
		this.validator = new SitemapItemValidator();
	}

	@Test
	public void testSupports_SitemapItem() {
		Assert.assertTrue(this.validator.supports(SitemapItem.class));
	}
	
	@Test
	public void testSupports_Object() {
		Assert.assertFalse(this.validator.supports(Object.class));
	}
	
	@Test
	public void testSupports_Entity() {
		Assert.assertFalse(this.validator.supports(Entity.class));
	}
	
	@Test
	public void testValidate_SitemapAndParentNull() {
		SitemapItem item = new SitemapItem();
		item.setDecorationName("DecorationName");
		item.setSitemap(null);
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNotNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
	}
	
	@Test
	public void testValidate_SitemapNotNullAndParentNull() {
		SitemapItem item = new SitemapItem();
		item.setName("name");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertFalse(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
	}
	
	@Test
	public void testValidate_SitemapNullAndParentNotNull() {
		SitemapItem item = new SitemapItem();
		item.setName("name");
		item.setDecorationName("DecorationName");
		item.setSitemap(null);
		item.setParent(new SitemapItem());
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertFalse(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
	}
	
	
	@Test
	public void testValidate_NameNull() {
		SitemapItem item = new SitemapItem();
		item.setName(null);
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_NameIsWhiteSpace() {
		SitemapItem item = new SitemapItem();
		item.setName(" ");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_NameContainsSpaceInMiddle() {
		SitemapItem item = new SitemapItem();
		item.setName("na me");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_NameContainsSpaceInStart() {
		SitemapItem item = new SitemapItem();
		item.setName(" name");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_NameContainsSpaceInEnd() {
		SitemapItem item = new SitemapItem();
		item.setName("name ");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_NameContainsDotInStart() {
		SitemapItem item = new SitemapItem();
		item.setName(".name");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_NameContainsDotInMiddle() {
		SitemapItem item = new SitemapItem();
		item.setName("na.me");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_NameContainsDotInEnd() {
		SitemapItem item = new SitemapItem();
		item.setName("name.");
		item.setDecorationName("DecorationName");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidate_DecorationNameNull() {
		SitemapItem item = new SitemapItem();
		item.setName("name");
		item.setDecorationName(null);
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNull(errors.getFieldError("name"));
		Assert.assertNotNull(errors.getFieldError("decorationName"));
	}
	
	@Test
	public void testValidate_DecorationNameWhiteSpace() {
		SitemapItem item = new SitemapItem();
		item.setName("name");
		item.setDecorationName(" ");
		item.setSitemap(new Sitemap());
		item.setParent(null);
		Errors errors = new BindException(item, "sitemapItem");
		validator.validate(item, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertNull(errors.getFieldError("sitemap"));
		Assert.assertNull(errors.getFieldError("parent"));
		Assert.assertNull(errors.getFieldError("name"));
		Assert.assertNotNull(errors.getFieldError("decorationName"));
	}
}
