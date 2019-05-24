package com.boco.eoms.otherwise.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.dao.ITawRmTestcardDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmTestcardDaoTest extends BaseDaoTestCase {
    private String tawRmTestcardId = new String("1");
    private ITawRmTestcardDao dao = null;

    public void setTawRmTestcardDao(ITawRmTestcardDao dao) {
        this.dao = dao;
    }

    public void testAddTawRmTestcard() throws Exception {
        TawRmTestcard tawRmTestcard = new TawRmTestcard();

        // set required fields

        java.lang.String ascriptionPlace = "YsKqTbEfLnTvKfRlWcUgAyToRlBpOl";
       // tawRmTestcard.setAscriptionPlace(ascriptionPlace);        

        java.lang.String visitPlace = "UmArNlWmPaHkKoBmPzOaKkOzPaJkLj";
       // tawRmTestcard.setVisitPlace(visitPlace);        

        java.lang.String supplyer = "VdBeHzDrBzEjTqTaGdAyIrScKnDqEeUvWjByDiSxYfEmKoItFu";
        tawRmTestcard.setSupplyer(supplyer);        

        java.lang.String iccid = "RoToHkQnKxSpSmWyWkLpGrIhDoYsGz";
        tawRmTestcard.setIccid(iccid);        

        java.lang.String msisdn = "QzNkPeXxSrImBoClBkNrZdTjQqHjOq";
        tawRmTestcard.setMsisdn(msisdn);        

        java.lang.String imsi = "VyYlVaFsHmYgFiCvInJrBdVpIpNcXr";
        tawRmTestcard.setImsi(imsi);        

        java.lang.String pin = "VoXyHuZhGvNfGfIpJrGuFmQxLbNoUc";
        tawRmTestcard.setPin(pin);        

        java.lang.String puk = "ExIoWfEiGqQzEkIpSlErNgPsDfPcOd";
        tawRmTestcard.setPuk(puk);        

        java.lang.String operation = "KhEnBuNpXdVvXpXdQiPhUmPxMiPgXn";
        tawRmTestcard.setOperation(operation);        

        java.lang.String openAccountDate = "JnDjGcThPtWoEqOyOxGcMiBdKxEcQy";
        tawRmTestcard.setOpenAccountDate(openAccountDate);        

        java.lang.String logoutDate = "DwJxWzHyNkYjKqScTuBmFkAaBkNxJc";
        tawRmTestcard.setLogoutDate(logoutDate);        

        java.lang.String takeOverDate = "DpKtVrTeWzTkWwNcNyLsWzYgUdItUz";
        tawRmTestcard.setTakeOverDate(takeOverDate);        

        java.lang.String state = "CzEyCwJmEyYsKfQgHlMbNkOoFmNbBy";
        tawRmTestcard.setState(state);        

        java.lang.String oldNumber = "XpAxGfEoOxEvGpHpGbFgRnGlVsTyKz";
        tawRmTestcard.setOldNumber(oldNumber);        

        dao.saveTawRmTestcard(tawRmTestcard);

        // verify a primary key was assigned
        assertNotNull(tawRmTestcard.getId());

        // verify set fields are same after save
        //assertEquals(ascriptionPlace, tawRmTestcard.getAscriptionPlace());
        //assertEquals(visitPlace, tawRmTestcard.getVisitPlace());
        assertEquals(supplyer, tawRmTestcard.getSupplyer());
        assertEquals(iccid, tawRmTestcard.getIccid());
        assertEquals(msisdn, tawRmTestcard.getMsisdn());
        assertEquals(imsi, tawRmTestcard.getImsi());
        assertEquals(pin, tawRmTestcard.getPin());
        assertEquals(puk, tawRmTestcard.getPuk());
        assertEquals(operation, tawRmTestcard.getOperation());
        assertEquals(openAccountDate, tawRmTestcard.getOpenAccountDate());
        assertEquals(logoutDate, tawRmTestcard.getLogoutDate());
        assertEquals(takeOverDate, tawRmTestcard.getTakeOverDate());
        assertEquals(state, tawRmTestcard.getState());
        assertEquals(oldNumber, tawRmTestcard.getOldNumber());
    }

    public void testGetTawRmTestcard() throws Exception {
        TawRmTestcard tawRmTestcard = dao.getTawRmTestcard(tawRmTestcardId);
        assertNotNull(tawRmTestcard);
    }

    public void testGetTawRmTestcards() throws Exception {
        TawRmTestcard tawRmTestcard = new TawRmTestcard();

        List results = dao.getTawRmTestcards(tawRmTestcard);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawRmTestcard() throws Exception {
        TawRmTestcard tawRmTestcard = dao.getTawRmTestcard(tawRmTestcardId);

        // update required fields
        java.lang.String ascriptionPlace = "QmGoCgXcWsBgEgZpTlOyXuVoCyKmCq";
       // tawRmTestcard.setAscriptionPlace(ascriptionPlace);        
        java.lang.String visitPlace = "VuIzFwJhDvPpWlSpFaQhQaAaJcNyOk";
       // tawRmTestcard.setVisitPlace(visitPlace);        
        java.lang.String supplyer = "KzIyOzNbZbPxViSoSwGmLgCwPoEhMqRbXtMqBrAmPvToScUbUj";
        tawRmTestcard.setSupplyer(supplyer);        
        java.lang.String iccid = "TkSaDpGaJlScDoHqQhIhNjWhGdMiYv";
        tawRmTestcard.setIccid(iccid);        
        java.lang.String msisdn = "XyHhShNdDtKbKeJtLvHdQoBhHqTzLi";
        tawRmTestcard.setMsisdn(msisdn);        
        java.lang.String imsi = "QaFcDyJdVxTpHgKoKzPdFiViDyKeVf";
        tawRmTestcard.setImsi(imsi);        
        java.lang.String pin = "YtSkGqGbIeCrBbNmHfFwNtJyVmSbWx";
        tawRmTestcard.setPin(pin);        
        java.lang.String puk = "FfWcWaJkOzPfNlCrLhVbVsXfOnYhVt";
        tawRmTestcard.setPuk(puk);        
        java.lang.String operation = "EsFxCkYtEkMzXaQeQmTkTvJnJnVuZv";
        tawRmTestcard.setOperation(operation);        
        java.lang.String openAccountDate = "AsPkMvMiWmQbUcXpNyBbMgTfHeFqNm";
        tawRmTestcard.setOpenAccountDate(openAccountDate);        
        java.lang.String logoutDate = "WmPiYiXrXkLjBsMjEcKePpYnTdGrVc";
        tawRmTestcard.setLogoutDate(logoutDate);        
        java.lang.String takeOverDate = "LqKfKfEaWaOnThNnEyPzLmSdGzEsTb";
        tawRmTestcard.setTakeOverDate(takeOverDate);        
        java.lang.String state = "GwEaJnRvUiSlRvNuQkOvEeUcZjRsYt";
        tawRmTestcard.setState(state);        
        java.lang.String oldNumber = "KcSsSsRzIqUjLoGmCaDmFmXwZnTiQl";
        tawRmTestcard.setOldNumber(oldNumber);        

        dao.saveTawRmTestcard(tawRmTestcard);

        //assertEquals(ascriptionPlace, tawRmTestcard.getAscriptionPlace());
        //assertEquals(visitPlace, tawRmTestcard.getVisitPlace());
        assertEquals(supplyer, tawRmTestcard.getSupplyer());
        assertEquals(iccid, tawRmTestcard.getIccid());
        assertEquals(msisdn, tawRmTestcard.getMsisdn());
        assertEquals(imsi, tawRmTestcard.getImsi());
        assertEquals(pin, tawRmTestcard.getPin());
        assertEquals(puk, tawRmTestcard.getPuk());
        assertEquals(operation, tawRmTestcard.getOperation());
        assertEquals(openAccountDate, tawRmTestcard.getOpenAccountDate());
        assertEquals(logoutDate, tawRmTestcard.getLogoutDate());
        assertEquals(takeOverDate, tawRmTestcard.getTakeOverDate());
        assertEquals(state, tawRmTestcard.getState());
        assertEquals(oldNumber, tawRmTestcard.getOldNumber());
    }

    public void testRemoveTawRmTestcard() throws Exception {
        String removeId = new String("3");
        dao.removeTawRmTestcard(removeId);
        try {
            dao.getTawRmTestcard(removeId);
            fail("tawRmTestcard found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
