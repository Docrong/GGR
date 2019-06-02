package com.boco.eoms.duty.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.duty.model.TawRmReplace;
import com.boco.eoms.duty.dao.ITawRmReplaceDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawRmReplaceDaoTest extends BaseDaoTestCase {
    private String tawRmReplaceId = new String("1");
    private ITawRmReplaceDao dao = null;

    public void setTawRmReplaceDao(ITawRmReplaceDao dao) {
        this.dao = dao;
    }

    public void testAddTawRmReplace() throws Exception {
        TawRmReplace tawRmReplace = new TawRmReplace();

        // set required fields

        java.lang.String dutydate = "XyBuRjQtArNuTrXjOgMcCiRvVvCqCq";
        tawRmReplace.setDutydate(dutydate);        

        java.lang.String flag = "BmTtGdMaHjEkGpJpYaDkPbDoMjXcYk";
        tawRmReplace.setFlag(flag);        

        java.lang.String hander = "ZfCgBvGrLwUsRmFaHeBpShLjDxYwAq";
        tawRmReplace.setHander(hander);        

        java.lang.String inputdate = "EwNwYoRpDnJrZuGtOgYnHlWwSmCcRb";
        tawRmReplace.setInputdate(inputdate);        

        java.lang.String reason = "DmToSjEeKrTkFyJjGzKgRaGgHrKfJjOtAbNlUuHgJcNoHsMwRiIyAmQpXtAdUvJjTiIiEzWfKkJpJnZpLuGnOiYeNgLeHyYrRvIvTqYbPoZuQoVfXfJzThNrNxSqMmIzPmYuNiKkKkLrBeNeOiRaZxSsFhZfNoPiYzOnExCkQxUrZpAzZgIyAuIcQpHjOzEbNcWnFhTlCkFgWvJaEwQfStKhQnSbCwJoItGnGeMdBgDcPaCeYzYyUoDbEkRpNkFjTnKpNjFzHnWqMsNnSgDoCjFqXuVtIzLnTnMtObPrGbHw";
        tawRmReplace.setReason(reason);        

        java.lang.String receiver = "EuKcNpCdZaCtYaMdFrWxRfBjHoEfMy";
        tawRmReplace.setReceiver(receiver);        

        java.lang.String remark = "UfGgVeFxNoUhYgXgFxNoSgAtTmJeSsGwRyLpMrXtAdYgLuFlXqXtQbOnUqOgQbOcNsYcAuWsMkChCwScPiKmQaRvZrOvDiGiCcXsPyIoNiIxSwJpDaPjKiAvAeCcBtLhEvKwZyVaJkZdBtKlQeAaTtKyTnChJrHaXyKvOlQmYlMbYtPiLuYxNkVfZcHyRdUwIlEcVnRhFgMnHgSlFlGnOjYdJrWhEfZlCuUcBrDcQtIfTcMjGqSnZwOnRfQvQdNyLeCgExFiAjOyIaNxJnLmShPzCsEgVdCuNrRzVhCgSpXe";
        tawRmReplace.setRemark(remark);        

        java.lang.String roomId = "MnSgKrOsLhDfDnLsDcQiVsEmCnCzUr";
        tawRmReplace.setRoomId(roomId);        

        java.lang.String workserial = "MsFpSlRjFiJwWuJrMaHvGzAyYdTvKu";
        tawRmReplace.setWorkserial(workserial);        

        dao.saveTawRmReplace(tawRmReplace);

        // verify a primary key was assigned
        assertNotNull(tawRmReplace.getId());

        // verify set fields are same after save
        assertEquals(dutydate, tawRmReplace.getDutydate());
        assertEquals(flag, tawRmReplace.getFlag());
        assertEquals(hander, tawRmReplace.getHander());
        assertEquals(inputdate, tawRmReplace.getInputdate());
        assertEquals(reason, tawRmReplace.getReason());
        assertEquals(receiver, tawRmReplace.getReceiver());
        assertEquals(remark, tawRmReplace.getRemark());
        assertEquals(roomId, tawRmReplace.getRoomId());
        assertEquals(workserial, tawRmReplace.getWorkserial());
    }

    public void testGetTawRmReplace() throws Exception {
        TawRmReplace tawRmReplace = dao.getTawRmReplace(tawRmReplaceId);
        assertNotNull(tawRmReplace);
    }

    public void testGetTawRmReplaces() throws Exception {
        TawRmReplace tawRmReplace = new TawRmReplace();

        List results = dao.getTawRmReplaces(tawRmReplace);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawRmReplace() throws Exception {
        TawRmReplace tawRmReplace = dao.getTawRmReplace(tawRmReplaceId);

        // update required fields
        java.lang.String dutydate = "QuBxOgTbFwRoSzQfQpMnPgUnWeXqMg";
        tawRmReplace.setDutydate(dutydate);        
        java.lang.String flag = "MaTnUtJtSeJcZkCnHeSfAvFfNoNrNg";
        tawRmReplace.setFlag(flag);        
        java.lang.String hander = "XwJnYbCnGkLnTnJlMdVySyQaUcZnCp";
        tawRmReplace.setHander(hander);        
        java.lang.String inputdate = "FdBrPfMlTnXtGxIgPwEpVgFhNeGfEo";
        tawRmReplace.setInputdate(inputdate);        
        java.lang.String reason = "IzYeZlEcTiKcHlSwWpVyXiXdJmPtAvBwPjAvQuQdClCySaVrLjFmShTjLkUtFeFiEnDoFrSyUfFqQmEoKePuKxBuIwMtHtQhVnVaGbBaLkQjUjPaPfPsUkDdRzJjAcDqKwYzPwEdMkDaWxWnLwNxZlHzAyLtLnPyZfMfFcQuClYjCmVoYgCnSrSdDwHuEhQjCvVsEfQiEdVtGnTtHeCaKcVxWdMxGwRcEbUeKxTwSkAsYgTfBdFhAaIcAtOtRrBpHbOsZxWsHmTbZdQkRxIvJzAsLyXsStIzJsDtJiJcAgXf";
        tawRmReplace.setReason(reason);        
        java.lang.String receiver = "DgZvZzCqSePoSkAuEcFlEeVkHvTvNv";
        tawRmReplace.setReceiver(receiver);        
        java.lang.String remark = "TrDzNoCqJuMsVkNeDwBuFaXcEtLoJrEkIuYcLhOwKoKwUyYiWbJvAwAnYtWuGnWhRuCyMiSaZzQqPsImHjDuWiFwNzAiOcQgNcNwUhJfWcMmFjZyHmWbEsPfLbXxVvXbDxMcGnRiNdBjPdQtXfEbLxIuIyOpAyQlQuVoEiCdZjIeBpReFmNbQpGtTwMcEwYxCwLeWtDrGnMdVsAlKjKvImYfZaVqYwAjObZkXnOyPkIoZyUrYvOwOvYlYzJnWwClNoUvHePqIsFdNyXbCuVrThKlTbBtGoSoMuGiCqWlRdCy";
        tawRmReplace.setRemark(remark);        
        java.lang.String roomId = "TdZkUsSkTcPsFeOxAwMsBcCcEqXlDr";
        tawRmReplace.setRoomId(roomId);        
        java.lang.String workserial = "OeWuSqMdQvMeCeOpDkDnQbSkHiHwRn";
        tawRmReplace.setWorkserial(workserial);        

        dao.saveTawRmReplace(tawRmReplace);

        assertEquals(dutydate, tawRmReplace.getDutydate());
        assertEquals(flag, tawRmReplace.getFlag());
        assertEquals(hander, tawRmReplace.getHander());
        assertEquals(inputdate, tawRmReplace.getInputdate());
        assertEquals(reason, tawRmReplace.getReason());
        assertEquals(receiver, tawRmReplace.getReceiver());
        assertEquals(remark, tawRmReplace.getRemark());
        assertEquals(roomId, tawRmReplace.getRoomId());
        assertEquals(workserial, tawRmReplace.getWorkserial());
    }

    public void testRemoveTawRmReplace() throws Exception {
        String removeId = new String("3");
        dao.removeTawRmReplace(removeId);
        try {
            dao.getTawRmReplace(removeId);
            fail("tawRmReplace found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
