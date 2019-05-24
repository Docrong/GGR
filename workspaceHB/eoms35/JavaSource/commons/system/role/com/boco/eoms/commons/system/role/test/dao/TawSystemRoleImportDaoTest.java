package com.boco.eoms.commons.system.role.test.dao;

import java.util.Date;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.role.dao.ITawSystemRoleImportDao;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImport;

public class TawSystemRoleImportDaoTest extends BaseDaoTestCase {
	private String tawSystemRoleImportId = new String("1");

	private ITawSystemRoleImportDao dao = null;

	public void setTawSystemRoleImportDao(ITawSystemRoleImportDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemRoleImport() throws Exception {
		TawSystemRoleImport tawSystemRoleImport = new TawSystemRoleImport();

		// set required fields

		java.lang.String roleId = "CnJfIlVtJxPhZhYlOaSmDdVtQxRdOyScTxSoGuHhDiRbFyIvCh";
		tawSystemRoleImport.setRoleId(roleId);

		java.lang.String subRoleId = "QlWkJbEzZkXwTaLtUtFwTtIdIdTbFiZpHzGzTjHsPaQoEqYlIx";
		tawSystemRoleImport.setSubRoleId(subRoleId);

		java.lang.String version = "BwCkJzTsHvVmByUnKsTyGzEeUyUiYbJuEyCtAfXzBrNiOhHtBx";
		tawSystemRoleImport.setVersion(version);

		Date versionAt = new Date();
		tawSystemRoleImport.setVersionAt(versionAt);

		dao.saveTawSystemRoleImport(tawSystemRoleImport);

		// verify a primary key was assigned
		assertNotNull(tawSystemRoleImport.getId());

		// verify set fields are same after save
		assertEquals(roleId, tawSystemRoleImport.getRoleId());
		assertEquals(subRoleId, tawSystemRoleImport.getSubRoleId());
		assertEquals(version, tawSystemRoleImport.getVersion());
		assertEquals(versionAt, tawSystemRoleImport.getVersionAt());
	}

	public void testGetTawSystemRoleImport() throws Exception {
		TawSystemRoleImport tawSystemRoleImport = dao
				.getTawSystemRoleImport(tawSystemRoleImportId);
		assertNotNull(tawSystemRoleImport);
	}

	public void testGetTawSystemRoleImports() throws Exception {
		TawSystemRoleImport tawSystemRoleImport = new TawSystemRoleImport();

		List results = dao.getTawSystemRoleImports(tawSystemRoleImport);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemRoleImport() throws Exception {
		TawSystemRoleImport tawSystemRoleImport = dao
				.getTawSystemRoleImport(tawSystemRoleImportId);

		// update required fields
		java.lang.String roleId = "MoPgBvJoMoYvYbClOvIzHmEmTzRjWrAxNkHnHzLkIcUzClHbYm";
		tawSystemRoleImport.setRoleId(roleId);
		java.lang.String subRoleId = "ZcEtLiRnVoQxWoTuZwLfOoEjScQsPcNxBvEgVyAzOjNcOiZsFu";
		tawSystemRoleImport.setSubRoleId(subRoleId);
		java.lang.String version = "AaWuCfOhDaViQmJvJeZgUlUcJcBjRyUmNsEhXbJxJbWcElMyXl";
		tawSystemRoleImport.setVersion(version);
		Date versionAt = new Date();
		tawSystemRoleImport.setVersionAt(versionAt);

		dao.saveTawSystemRoleImport(tawSystemRoleImport);

		assertEquals(roleId, tawSystemRoleImport.getRoleId());
		assertEquals(subRoleId, tawSystemRoleImport.getSubRoleId());
		assertEquals(version, tawSystemRoleImport.getVersion());
		assertEquals(versionAt, tawSystemRoleImport.getVersionAt());
	}

	public void testRemoveTawSystemRoleImport() throws Exception {
		String removeId = new String("3");
		dao.removeTawSystemRoleImport(removeId);
		try {
			dao.getTawSystemRoleImport(removeId);
			fail("tawSystemRoleImport found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
