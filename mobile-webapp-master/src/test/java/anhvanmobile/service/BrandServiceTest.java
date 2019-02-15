package anhvanmobile.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import anhvanmobile.exception.DuplicateProductNameException;
import anhvanmobile.model.Brand;
import anhvanmobile.service.brand.BrandService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BrandServiceTest {

	@Autowired
	private BrandService brandService;

	@Test
	public void testA_BrandServiceNotNull() {
		assertNotNull(brandService);
	}

	@Test(expected = Exception.class)
	public void testB_InsertBrandWithNameNull() {
		Brand brand = new Brand(null);
		brandService.create(brand);
	}

	@Test
	public void testC_InsertBrandWithBrandNameContainOneCharacter() {
		Brand brand = new Brand("a"); // 1
		assertNotNull(brandService.create(brand));
	}

	@Test
	public void testD_InsertBrandWithBrandNameContainFortyFiveCharacters() {
		Brand brand = new Brand("abcdefghyjklmnovsqptwzabcdefghyjklmnovsqptwza"); // 45
		assertNotNull(brandService.create(brand));
	}

	@Test(expected = Exception.class)
	public void testE_InsertBrandWithBrandNameContainFortySixCharacters() {
		Brand brand = new Brand("abcdefghyjklmnovsqptwzabcdefghyjklmnovsqptwzax"); // 46
		assertNotNull(brandService.create(brand));
	}

	@Test
	public void testF_InsertBrand() {
		Brand brand = new Brand("LG");
		assertNotNull(brandService.create(brand));
	}

	@Test(expected=DuplicateProductNameException.class)
	public void testG_DuplicateBrand() {
		Brand brand = new Brand("LG");
		brandService.create(brand);
	}

	@Test
	public void testH_FindBrandById() {
		Brand brand = brandService.findById(1);
		assertNotNull(brand);
	}

	@Test
	public void testI_FindBrandByName() {
		Brand brand = brandService.findBrandByName("LG");
		assertNotNull(brand);
	}

	@Test
	public void testJ_FindAll() {
		List<Brand> brands = brandService.findAll();
		assertTrue(brands.size() > 0);
	}

	@Test
	public void testK_UpdateBrandName() {
		Brand brand = brandService.findBrandByName("LG");
		brand.setName("Nokia");
		assertNotNull(brandService.update(brand));
	}

	@Test(expected = DuplicateProductNameException.class)
	public void testL_UpdateBrandNameDuplicateBrandName() {
		Brand brand = brandService.findBrandByName("a");
		brand.setName("Nokia");
		brandService.update(brand);
	}
	
	@Test
	public void testM_DeleteBrandByBrandName() {
		assertTrue(brandService.remove(brandService.findBrandByName("a")));
		assertTrue(brandService.remove(brandService.findBrandByName("abcdefghyjklmnovsqptwzabcdefghyjklmnovsqptwza")));
		assertTrue(brandService.remove(brandService.findBrandByName("Nokia")));
	}
	
	@Test(expected=Exception.class)
	public void testN_DeleteBrandFail() {
		brandService.remove(brandService.findBrandByName("a"));
	}

	@Test
	public void testO_DeleteBrandHasManyProduct() {
		assertTrue(brandService.remove(brandService.findBrandByName("Apple")));
	}
}
